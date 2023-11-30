package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MapClasses {
    // Map the class objects in the json file to a hashmap of classes with the
    // Subject as the key and the JSON array of courses as an array of ClassInfo
    // objects

    private final String filePath;

    MapClasses (String filePath) {
        this.filePath = filePath;
    }

    // Clean-up code
    public Map<String, List<ClassInfo>> mapClasses() {
        Map<String, List<ClassInfo>> subjectMap = parseJson(filePath);
        updateDays(subjectMap);
        // Print the result
        // printResult(subjectMap);
        return subjectMap;
    }

    // Clean-up code
    private void updateDays(Map<String, List<ClassInfo>> subjectMap) {
        // Update days based on the preceding ClassInfo
        updateDaysBasedOnPreceding(subjectMap);

        onlineDays(subjectMap);

    }

    // Helper method for updating days
    /*
         - Purpose: This code was designed to look at the ClassInfo object
                    ahead in the list of ClassInfo(s) and if they have an
                    empty class name, CRN and status, their day will be
                    appended to the previous ClassInfo object.

         - Usage: The reason for doing this is to deal with labs. So, in
                  Web Schedule, I realized that some classes have labs, and
                  they really serve no use in being stored, and they have no
                  they can be deleted. However, I realized that their day also
                  counts in the total day of the course. So, I needed to find a
                  way to add it and this is how I did it.
    */
    private static void updateDaysBasedOnPreceding(
            Map<String, List<ClassInfo>> subjectMap)
    {
        // Iterates through the HashMap
        for (List<ClassInfo> classInfoList : subjectMap.values()) {
            Iterator<ClassInfo> iterator = classInfoList.iterator();
            ClassInfo precedingClassInfo = null;

            while (iterator.hasNext()) {
                ClassInfo classInfo = iterator.next();

                if (classInfo.getClassName().isEmpty() &&
                        classInfo.getStatus().isEmpty() &&
                        classInfo.getCrn() == 0)
                {
                    // Found the target classInfo object, remove it
                    iterator.remove();

                    // Update the preceding ClassInfo's days
                    if (precedingClassInfo != null)
                    {

                        String newDays = classInfo.getDays();
                        String existingDays = precedingClassInfo.getDays();

                        // Add new days only if all elements
                        // exist in the preceding ClassInfo's days
                        if (!anyDayExists(existingDays, newDays))
                        {

                            String cleanedDay = cleanDays(
                                    existingDays + " " + newDays);

                            String formattedDay = formatAndSortDays(
                                    cleanedDay);

                            precedingClassInfo.setDays(formattedDay);

                        }

                    }
                } else {
                    // Set the current classInfo as the
                    // preceding one for the next iteration
                    precedingClassInfo = classInfo;
                }
            }
        }
    }

    // Helper method to check if all elements of newDays exist in existingDays
    /*
            - Purpose: This code checks the strings of days to remove
                       duplicates like in "Th Th, M, W, W"

            - Usage: When I thought of combining the days of the labs in
                     the days of the course, I realized that sometimes,
                     the days overlap, so I needed a way to remove
                     duplicates, otherwise, it wouldn't look good in
                     the presentation of the class.
    */
    private static boolean anyDayExists(String existingDays, String newDays) {
        // Split by , to make sure to check each day individually
        // Otherwise, duplicate days can appear
        String[] newDaysParts = newDays.split(",\\s*");
        String[] finalParts = new String[0];
        for (String day : newDaysParts) {
            finalParts = day.split("\\s+");
        }
        // Split by spaces to keep "Th" as one day
        String[] existingDaysParts = existingDays.split("\\s+");

        for (String newDay : finalParts) {
            if (containsCaseInsensitive(existingDaysParts, newDay)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if an array contains a specific element (case-insensitive)
    private static boolean containsCaseInsensitive(String[] array, String element) {
        for (String arrayElement : array) {
            if (arrayElement.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to help clean the merged days
    public static String cleanDays(String days) {
        String[] dayPairs = days.split(",\\s*");
        StringBuilder result = new StringBuilder();

        for (String dayPair : dayPairs) {
            String[] individualDays = dayPair.split("\\s+");

            // Check for duplicates and remove one if necessary
            Set<String> uniqueDays = removeDuplicates(individualDays);

            // Append the cleaned days to the result
            result.append(String.join(" ", uniqueDays)).append(", ");
        }

        // Remove the trailing comma and space
        return result.substring(0, result.length() - 2);
    }

    // Helper method to remove duplicates
    private static Set<String> removeDuplicates(String[] days) {
        Set<String> uniqueDays = new HashSet<>(Arrays.asList(days));
        uniqueDays.remove(""); // Remove empty days if present
        return uniqueDays;
    }

    // Helper Method to sort the days back into their own place.
    private static String formatAndSortDays(String cleanedDay) {

        String[] daysArray = cleanedDay.split("\\s+");
        String[] newDaysArray = removeCommas(daysArray);
        Set<String> uniqueElements = new HashSet<>();
        int newIndex = 0;

        for (String element : newDaysArray) {
            if (uniqueElements.add(element)) {
                // If the set didn't contain the element,
                // update the array and move to the next index
                newDaysArray[newIndex++] = element;
            }
        }

        // If you want to resize the array to remove the trailing elements,
        // you can create a new array and copy the elements
        String[] newArray = Arrays.copyOf(newDaysArray, newIndex);
        sortDays(newArray);
        System.arraycopy(newArray, 0, newDaysArray, 0, newIndex);

        return String.join(", ", newArray);
    }

    // Remove commas in each string to not duplicate them
    private static String[] removeCommas(String[] inputArray) {
        // Remove commas in each string in the array
        return Arrays.stream(inputArray)
                .map(s -> s.replaceAll(",", ""))
                .toArray(String[]::new);
    }

    /*
        If there are no days, make sure to say it's an online class
        There's no need to check for other stuff because the sort and the
        cleanup of the data certifies that there are no ClassInfo objects
        that has an empty class days but no other empty stuff.

        Summary: No need to worry about labs because the cleanup certifies it.
     */

    private static void onlineDays(Map<String, List<ClassInfo>> subjectMap) {
        for (List<ClassInfo> classInfoList : subjectMap.values()) {
            for (ClassInfo classInfo : classInfoList) {
                if (classInfo.getDays().isEmpty()) {
                    classInfo.setDays("Online");
                }
            }
        }
    }

    // Method for sorting the days in order
    private static void sortDays(String[] formattedDay)
    {
        Arrays.sort(formattedDay, Comparator.comparingInt(
                MapClasses::getDayOrder));
    }

    //Helper method for sorting days by making them in order
    private static int getDayOrder(String day)
    {
        // Define the order of days: M -> T -> W -> Th -> F
        String order = "MTWThF";

        // Get the index of the day in the order string
        return order.indexOf(day);
    }

    // Method for parsing the json to map the json objects
    // into a HashMap of the subject as its key and the array
    // of classes as a List of ClassInfo objects
    private static Map<String, List<ClassInfo>> parseJson(String filePath)
    {
        Map<String, List<ClassInfo>> subjectMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(filePath));

            Iterator<Map.Entry<String, JsonNode>>
                    subjectsIterator = root.fields();

            while (subjectsIterator.hasNext())
            {
                Map.Entry<String, JsonNode>
                        subjectEntry = subjectsIterator.next();

                String subjectName = subjectEntry.getKey();
                JsonNode coursesNode = subjectEntry.getValue();

                if (coursesNode.isArray()) {
                    List<ClassInfo> classInfoList = new ArrayList<>();

                    Iterator<JsonNode>
                            coursesIterator = coursesNode.elements();

                    while (coursesIterator.hasNext())
                    {
                        JsonNode courseNode = coursesIterator.next();

                        ClassInfo classInfo = new ClassInfo(
                                courseNode.get("Course Title").asText(),
                                courseNode.get("Professor").asText(),
                                courseNode.get("CRN").asInt(),
                                courseNode.get("Class Days").asText(),
                                courseNode.get("Status").asText(),
                                courseNode.get("Units").asInt()
                        );

                        classInfoList.add(classInfo);
                    }

                    subjectMap.put(subjectName, classInfoList);
                } else {
                    System.err.println("Invalid JSON format for subject: "
                            + subjectName + ". Expected an array of courses.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return subjectMap;
    }

    // Method for printing the HashMap
    private void printResult(Map<String, List<ClassInfo>> subjectMap) {
        for (Map.Entry<String, List<ClassInfo>> entry :
                subjectMap.entrySet())
        {
            String subject = entry.getKey();
            List<ClassInfo> classInfoList = entry.getValue();

            System.out.println("Subject: " + subject);
            for (ClassInfo classInfo : classInfoList) {
                System.out.println(classInfo);
            }
            System.out.println();
        }
    }

}

