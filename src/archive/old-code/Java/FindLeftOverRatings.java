package org.example;

import java.util.*;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindLeftOverRatings {
    public static void find(Map<String, List<ClassInfo>> subjectClassesMap)
    {

        // Call the new method to filter and remove duplicates
        List<ClassInfo>
                filteredAndUniqueClasses =
                filterAndRemoveDuplicates(subjectClassesMap);

        /*
        // Print or use the filtered and unique classes as needed
        for (ClassInfo classInfo : filteredAndUniqueClasses) {
            System.out.println(classInfo);
        }
         */

        //Print professor names
        System.out.println("Professor names in filtered and unique classes:");
        for (ClassInfo uniqueClass : filteredAndUniqueClasses) {
            System.out.println(uniqueClass.getProfessorObject().getName());
        }
    }

    // Your existing methods here

    // New method to filter and remove duplicates
    private static List<ClassInfo> filterAndRemoveDuplicates(
            Map<String, List<ClassInfo>> subjectClassesMap)
    {
        List<ClassInfo> filteredAndUniqueClasses = new ArrayList<>();

        for (List<ClassInfo> classes : subjectClassesMap.values())
        {
            // Extract classes with 0 numOfRatings
            List<ClassInfo> classesWithZeroRatings = classes.stream()
                    .filter(classInfo -> classInfo.getProfessorObject() !=
                            null && classInfo.getProfessorObject()
                            .getNumOfRatings() == 0)
                    .collect(Collectors.toList());

            // Remove duplicates with the same professor names
            List<ClassInfo> uniqueClasses = removeDuplicateProfessors(
                    classesWithZeroRatings);

            filteredAndUniqueClasses.addAll(uniqueClasses);
        }

        return filteredAndUniqueClasses;
    }

    private static List<ClassInfo> removeDuplicateProfessors(
            List<ClassInfo> classes)
    {
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

    public static void quoter() {
        // Replace with the actual file path
        String filePath = "src/main/resources/professorNames.txt";
        List<String> names = readNamesFromFile(filePath);
        List<String> quotedNames = quoteNames(names);

        // Print the quoted names
        for (int i = 0; i < quotedNames.size(); i++) {
            System.out.print(quotedNames.get(i));
            if (i < quotedNames.size() - 1) {
                System.out.println(", ");
            }
        }
    }

    private static List<String> readNamesFromFile(String filePath)
    {
        List<String> names = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                // Add each line (name) to the list
                names.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }

    private static List<String> quoteNames(List<String> names) {
        List<String> quotedNames = new ArrayList<>();

        for (String name : names) {
            // Split the name by comma and remove the last part (last name)
            String[] nameParts = name.split(",");
            if (nameParts.length > 0) {
                // Add quotes around the first part (first name)
                String quotedName = "\"" + nameParts[0].trim() + "\"";
                quotedNames.add(quotedName);
            }
        }

        return quotedNames;
    }
}
