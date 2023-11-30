package org.example;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class ClassParser {
    public void parseJson(String filePath) {

        try {
            // Read JSON file into a JSON object
            Path path = Paths.get(filePath);
            String jsonContent = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Modify the JSON content as needed
            modifyJson(jsonObject);


            // Write the modified JSON back to the file
            Files.write(path, jsonObject.toString(2).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void modifyJson(JSONObject jsonObject)
    {
        // Example modification: Remove the part
        // after en dash from "Course Title"
        for (String key : jsonObject.keySet()) {
            if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray jsonArray = jsonObject.getJSONArray(key);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String originalTitle = item.optString("Course Title", "");

                    int position = originalTitle.indexOf("–");

                    if (position != -1) {
                        item.put("Course Title",
                                originalTitle.substring(0, position).trim());
                    }
                }
            }
        }
    }

    void parseOutKeys(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(filePath));

            if (root.isObject()) {
                ObjectNode modifiedRoot = objectMapper.createObjectNode();

                // Iterate through subjects
                Iterator<Map.Entry<String, JsonNode>>
                        subjectsIterator = root.fields();

                while (subjectsIterator.hasNext())
                {
                    Map.Entry<String, JsonNode>
                            subjectEntry = subjectsIterator.next();

                    String subjectName = subjectEntry.getKey();
                    JsonNode coursesNode = subjectEntry.getValue();

                    if (coursesNode.isArray()) {
                        ArrayNode modifiedCourses =
                                objectMapper.createArrayNode();

                        // Iterate through courses for the current subject
                        Iterator<JsonNode> coursesIterator =
                                coursesNode.elements();

                        while (coursesIterator.hasNext())
                        {
                            JsonNode courseNode = coursesIterator.next();

                            if (courseNode.has("Status") &&
                                    courseNode.has("Course Title") &&
                                    courseNode.has("Class Days"))
                            {

                                if (courseNode.get("Status").asText()
                                        .isEmpty() &&
                                        courseNode.get("Course Title").asText()
                                                .isEmpty() &&
                                        courseNode.get("Class Days").asText()
                                                .isEmpty())
                                {
                                    // Skip courses with empty values for
                                    // "Status," "Course Title," and "Class Days"
                                    continue;
                                }

                            }

                            modifiedCourses.add(courseNode);
                        }

                        // Add the modified courses for the current subject to the new root
                        modifiedRoot.set(subjectName, modifiedCourses);
                    } else {
                        System.err.println("Invalid JSON format for subject: " +
                                subjectName + ". Expected an array of courses.");
                    }
                }

                // Write the modified data back to the same JSON file
                ObjectWriter writer =
                        objectMapper.writerWithDefaultPrettyPrinter();

                writer.writeValue(new File(filePath), modifiedRoot);

                System.out.println("Modification complete.");
            } else {
                System.err.println("Invalid JSON file format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
