package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Combine the hashmap of Subject and ClassInfos with the
// prof array
public class Combiner {

    // This is for printing a combined hashmap of
    // two hashmaps. It's unused code now.
    public void printCombined(Map<String, List<ClassInfo>> subjectMap) {

        // Print the updated subjectMap
        for (Map.Entry<String, List<ClassInfo>> entry : subjectMap.entrySet())
        {
            System.out.println("Subject: " + entry.getKey());
            for (ClassInfo classInfo : entry.getValue()) {
                System.out.println(classInfo);
            }
            System.out.println();
        }
    }

    // Combine the array of professors and the HashMap
    // of Subject and classes
    public Map<String, List<ClassInfo>> combine
            (Map<String, List<ClassInfo>> subjectMap,
             List<Professor> professors )
    {

        updateProfessorsInSubjectMap(subjectMap, professors);

        return subjectMap;
    }

    // This method is for combining two maps
    // It is unused code now
    public static <K, V> Map<K, V> combineMaps
            (Map<K, V> map1, Map<K, V> map2)
    {
        Map<K, V> combinedMap = new HashMap<>(map1); // Initialize with the elements of map1

        // Iterate over map2 and add or update entries in the combined map
        combinedMap.putAll(map2);

        return combinedMap;
    }

    // Replacing the professor name in the HashMap of classes
    // with the professor objects that have the same name
    private static void updateProfessorsInSubjectMap
            (Map<String, List<ClassInfo>> subjectMap,
             List<Professor> professors)
    {
        for (List<ClassInfo> classInfoList : subjectMap.values()) {
            for (ClassInfo classInfo : classInfoList) {
                String professorName = classInfo.getProfessorName();

                // Find the matching professor in the list
                Professor matchingProfessor = findProfessorByName(
                        professors, professorName);

                // Update the professor for the classInfo
                if (matchingProfessor != null) {
                    classInfo.setProfessorObject(matchingProfessor);
                } else {
                    // If no match is found, create a new Professor object
                    Professor newProfessor = new Professor(
                            professorName, 0.0F, 0, 0.0F);
                    classInfo.setProfessorObject(newProfessor);
                    professors.add(newProfessor);
                }

            }
        }
    }

    // Helper method for finding the professor
    public static Professor findProfessorByName(List<Professor> professors, String professorName) {
        for (Professor professor : professors) {
            if (professor.getName().equals(professorName)) {
                return professor;
            }
        }
        return null; // Professor not found
    }

}



