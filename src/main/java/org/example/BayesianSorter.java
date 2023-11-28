package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BayesianSorter {

    // Sort the classes and give the best class for each subject
    public static Map<String, ClassInfo> baySortSubjects(Map<String, List<ClassInfo>> subjectClassesMap) {

        // Calculate the Bayesian probability for each professor within each subject

        return findBestClasses(subjectClassesMap);
    }

    // Prints the subject and the best class for that subject
    public void getBaySortResults(Map<String, ClassInfo> bestClasses) {
        // Print the best classes for each subject
        for (Map.Entry<String, ClassInfo> entry : bestClasses.entrySet()) {
            System.out.println("Subject: " + entry.getKey() + ", Best Class: " + entry.getValue());
        }
    }

    // Find a singular best class in the list of classes
    public void getBestClass(Map<String, List<ClassInfo>> combinedMap, String className) {

        System.out.println(bestClass(combinedMap, className));
    }

    public void getRankedClasses(Map<String, List<ClassInfo>> subjectClassesMap, String classNumber) {
        List<ClassInfo> rankedClasses = rankClasses(classNumber, subjectClassesMap);
        rankedClasses = removeDuplicateProfessors(rankedClasses);
        for (ClassInfo rankClass: rankedClasses) {
            System.out.println(rankClass);
        }
    }

    // Rank Classes according to the probability
    private List<ClassInfo> rankClasses(String classNumber, Map<String, List<ClassInfo>> subjectClassesMap) {

        List<ClassInfo> classes = getClassesForClassNumber(classNumber, subjectClassesMap);
        // Filter classes for the specified class number
        List<ClassInfo> classesWithSameNumber = classes.stream()
                .filter(classInfo -> classInfo.getClassName().equals(classNumber))
                .collect(Collectors.toList());

        // Sort the filtered classes based on Bayesian probability and difficulty
        classesWithSameNumber.sort(Comparator.comparingDouble(classInfo ->
                calculateWeightedRating(classInfo.getProfessorObject(), calculateMeanVote(classesWithSameNumber))));

        // Reverse the list to have the highest ranked class first
        Collections.reverse(classesWithSameNumber);

        return classesWithSameNumber;
    }

    // Remove duplicate professor names from a list of ClassInfo objects
    private List<ClassInfo> removeDuplicateProfessors(List<ClassInfo> classes) {
        Set<String> uniqueProfessorNames = new HashSet<>();
        List<ClassInfo> uniqueClasses = new ArrayList<>();

        for (ClassInfo classInfo : classes) {
            Professor professor = classInfo.getProfessorObject();
            if (professor != null) {
                String professorName = professor.getName();

                // Check if the professor name is unique
                if (uniqueProfessorNames.add(professorName)) {
                    uniqueClasses.add(classInfo);
                }
            }
        }

        return uniqueClasses;
    }

    // Helper method for finding the best class
    private ClassInfo bestClass(Map<String, List<ClassInfo>> combinedMap, String className) {

        ClassInfo bestClass;
        for (Map.Entry<String, List<ClassInfo>> entry : combinedMap.entrySet()) {

            List<ClassInfo> classes = entry.getValue();

            bestClass = findBestClassForClassNumber
                    (className, classes);

            if (bestClass != null) {
                return bestClass;
            }

        }

        return null;

    }

    // Helper Method in weighting the classes for the class number
    private static ClassInfo findBestClassForClassNumber
    (String classNumber, List<ClassInfo> classes)
    {
        // Filter classes for the specified class number
        List<ClassInfo> classesWithSameNumber = classes.stream()
                .filter(classInfo -> classInfo.getClassName().equals(classNumber))
                .collect(Collectors.toList());

        // Calculate mean vote (C) for the specified class number
        double meanVote = calculateMeanVote(classesWithSameNumber);

        // Find the best class within the specified class number based on Bayesian probability
        return findBestClass(classesWithSameNumber, meanVote);
    }


    private static Map<String, ClassInfo> findBestClasses(Map<String, List<ClassInfo>> subjectClassesMap) {
        Map<String, ClassInfo> bestClasses = new HashMap<>();

        for (Map.Entry<String, List<ClassInfo>> entry : subjectClassesMap.entrySet()) {
            String subject = entry.getKey();
            List<ClassInfo> classes = entry.getValue();

            // Calculate mean vote (C) for the subject
            double meanVote = calculateMeanVote(classes);

            // Find the best class within the subject based on Bayesian probability
            ClassInfo bestClass = findBestClass(classes, meanVote);
            bestClasses.put(subject, bestClass);
        }

        return bestClasses;
    }

    private static ClassInfo findBestClass
            (List<ClassInfo> classes, double meanVote)
    {
        ClassInfo bestClass = null;
        double maxBayesianProbability = Double.MIN_VALUE;

        for (ClassInfo classInfo : classes) {
            Professor professor = classInfo.getProfessorObject();
            if (professor != null && professor.getNumOfRatings() > 0) {
                double bayesianProbability = calculateWeightedRating(professor, meanVote);

                // Comparing difficulty and weighted rating
                double difficulty = professor.getDifficulty();
                double combinedScore = (0.8 * bayesianProbability + 0.2 * (5.0 - difficulty));

                if (bestClass == null || combinedScore > maxBayesianProbability) {
                    bestClass = classInfo;
                    maxBayesianProbability = combinedScore;
                }
            }
        }

        if (bestClass == null) {
            // If no class with valid professor information is found, return the first class in the list
            bestClass = classes.isEmpty() ? null : classes.get(0);
        }

        return bestClass;
    }

    private static double calculateWeightedRating(Professor professor, double meanVote) {
        if (professor == null || professor.getNumOfRatings() == 0) {
            return 0.0; // Handle cases where professor information is missing or no ratings
        }

        int v = professor.getNumOfRatings(); // The number of votes for the item
        int m = 3; // Minimum votes required to be listed

        // Weighted Rating (WR) formula
        double weightedRating = ((double) v / (v + m)) *
                professor.getRating() +
                ((double) m / (v + m)) * meanVote;

        return weightedRating;
    }

    private static double calculateMeanVote(List<ClassInfo> classes)
    {
        double sumVotes = 0.0;
        int count = 0;

        for (ClassInfo classInfo : classes)
        {
            Professor professor = classInfo.getProfessorObject();

            if (professor != null)
            {
                sumVotes += professor.getRating();
                count++;
            }
        }

        return count > 0 ? sumVotes / count : 0.0;
    }

    // Helper method to get a list of ClassInfo objects for a specific class number
    private List<ClassInfo> getClassesForClassNumber(String classNumber, Map<String, List<ClassInfo>> subjectClassesMap) {
        List<ClassInfo> classesForClassNumber = new ArrayList<>();

        for (List<ClassInfo> classes : subjectClassesMap.values()) {
            List<ClassInfo> filteredClasses = classes.stream()
                    .filter(classInfo -> classInfo.getClassName().equals(classNumber))
                    .collect(Collectors.toList());

            classesForClassNumber.addAll(filteredClasses);
        }

        return classesForClassNumber;
    }

}

