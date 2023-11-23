package org.example;
import java.util.ArrayList;

public class ClassGenerator {
    public static void main(String[] args) {
        // Make an array list to contain all the professors
        ArrayList<Professor> profArray = new ArrayList<>();

        /*
            Instantiate the JsonToJava object to convert all the JSON
            objects in the JSON array in the file to java objects

         */
        JsonToJava createProfObj = new JsonToJava();
        createProfObj.convertProfJsonToJavaObj(profArray);

        System.out.println(profArray);

    }
}
