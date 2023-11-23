package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

// Make sure only the classes in the package can access this
class JsonToJava {
    //Establish File Path for the Json File in the resources folder through Maven
    static String jsonFilePath = "src/main/resources/Professors.json";
    JsonNode profNode = null;
    ObjectMapper objectMapper = null;
    void convertProfJsonToJavaObj(ArrayList<Professor> array) {

        try {
            objectMapper = new ObjectMapper();

            // Read JSON from a file into a JsonNode
            profNode = objectMapper.readTree(new File(jsonFilePath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert profNode != null;
        int i = 0;
        // Use for-each loop to retrieve each data we need from the Json Node and make it into
        // a professor object, Professor(...)
        for(final JsonNode objNode: profNode) {
            Professor profObject = new Professor(i++, objNode.get("Professor").textValue(),
                    objNode.get("Subject").textValue(),
                    objNode.get("Rating").floatValue(),
                    objNode.get("NumOfRating").intValue(),
                    objNode.get("Difficulty").floatValue());

            array.add(profObject);
        }

    }
}


