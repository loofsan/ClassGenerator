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
