package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonCombiner {

    public void combine() {

        List<String> fallFileList = getFallStrings();
        String fallFilePath = "src/main/resources/fallCombinedClasses.json";
        combineJsons(fallFilePath, fallFileList);

        List<String> springFileList = getSpringStrings();
        String springFilePath = "src/main/resources/springCombinedClasses.json";
        combineJsons(springFilePath, springFileList);
    }

    private static List<String> getSpringStrings() {
        List<String> springFileList = new ArrayList<>();
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring2.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring3.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring4.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring5.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring6.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring7.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring8.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring9.json");
        springFileList.add("src/main/resources/WebScheduleSpring" +
                "/WebScheduleClassesSpring10.json");
        return springFileList;
    }

    private static List<String> getFallStrings() {
        List<String> fallFileList = new ArrayList<>();
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall2.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall3.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall4.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall5.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall6.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall7.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall8.json");
        fallFileList.add("src/main/resources/WebScheduleClassesFall" +
                "/WebScheduleClassesFall9.json");

        return fallFileList;
    }

    private void combineJsons(String outputFilePath, List<String> filePaths) {

        try {
            JSONObject combinedJson = combineJsonFiles(filePaths);
            writeJsonToFile(combinedJson, outputFilePath);
            System.out.println("Combined JSON written to: " + outputFilePath);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject combineJsonFiles(List<String> filePaths) throws IOException, JSONException {
        JSONObject combinedJson = new JSONObject();

        for (String filePath : filePaths) {
            JSONObject jsonObject = readJsonFile(filePath);

            // Merge the current JSON object into the combined JSON object
            for (String key : jsonObject.keySet()) {
                combinedJson.put(key, jsonObject.get(key));
            }
        }

        return combinedJson;
    }

    private static JSONObject readJsonFile(String filePath) throws IOException, JSONException {
        try (FileReader fileReader = new FileReader(filePath)) {
            StringBuilder content = new StringBuilder();
            int character;

            while ((character = fileReader.read()) != -1) {
                content.append((char) character);
            }

            return new JSONObject(content.toString());
        }
    }

    private static void writeJsonToFile(JSONObject jsonObject, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4)); // Indent with 4 spaces for better readability
        }
    }
}

System.out.println("""
                    Hello, which semester would you like to search the classes in?
                    Fall or Spring
                    Choose (F) for Fall and (S) for Spring""");

        sem = input.nextLine();

        if (sem.equals("F")) {
        System.out.println("Hello, which course would you like to find" +
        " out more about?\nWrite (Ex: 'GEOG 100')");
        course = input.nextLine();
        course = capitalizeAndConcatenate(course);

        String cour = bayesianSorter.getBestClass(classMapFall, course).toString();

        if (cour.equals("null")) {
        System.out.println("No such course exists. Could you" +
        "re-enter, please?");
        } else {
        System.out.println("The best class for that course according " +
        "to our probability is: \n");
        bayesianSorter.getBestClass(classMapFall, course);
        }

        System.out.println("""
                        Would you like to see other classes ranked in order?
                        Enter 'Y' for Yes and 'N' for No""");

        ans = input.nextLine();
        if (ans.equals("Y")) {
        bayesianSorter.getRankedClasses(classMapFall, course);
        } else if (ans.equals("N")) {
        System.out.println("Have a nice day!");
        } else {
        System.out.println("I couldn't register that. Could you " +
        "enter it again?\n" +
        "Enter 'N' to stop");
        }
        } else if (sem.equals("S")) {
        System.out.println("Hello, which course would you like to find" +
        " out more about?\nWrite (Ex: 'GEOG 100')");
        course = input.nextLine();
        course = capitalizeAndConcatenate(course);

        String cour = bayesianSorter.getBestClass(classMapSpring, course).toString();

        if (cour.equals("null")) {
        System.out.println("No such course exists. Could you" +
        "re-enter, please?");
        } else {
        System.out.println("The best class for that course according " +
        "to our probability is: \n");
        bayesianSorter.getBestClass(classMapSpring, course);
        }

        System.out.println("""
                        Would you like to see other classes ranked in order?
                        Enter 'Y' for Yes and 'N' for No""");

        ans = input.nextLine();
        if (ans.equals("Y")) {
        bayesianSorter.getRankedClasses(classMapSpring, course);
        } else if (ans.equals("N")) {
        System.out.println("Have a nice day!");
        } else {
        System.out.println("I couldn't register that. Could you " +
        "enter it again?\n" +
        "Enter 'N' to stop");
        }
        } else {
        System.out.println("I couldn't register the semester. Could you" +
        " enter it again?\n" +
        "Enter 'X' to stop");
        }
        }

        System.out.println("""
                    ----------------------------------------------------------------

                    """);

        input.close();

