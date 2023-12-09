package org.example;

import java.util.*;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        // Make an array list to contain all the professors
        ArrayList<Professor> profArray = new ArrayList<>();
         /*
        Instantiate the JsonToJava object to convert all the JSON
        objects in the JSON array in the file to java objects
        */
        JsonToJava createProfObj = new JsonToJava();

        MapClasses mapperForFall = new MapClasses
                ("src/main/resources/backend_resources/" +
                        "fallCombinedClasses.json");
        MapClasses mapperForSpring = new MapClasses
                ("src/main/resources/backend_resources/" +
                        "springCombinedClasses.json");

        Combiner combiner = new Combiner();
        BayesianSorter bayesianSorter = new BayesianSorter();

        // Now, we have an array of professor objects, a hashmap of
        // subjects as keys and an array of classes as a value.
        createProfObj.convertProfJsonToJavaObj(profArray);
        ProfParser.parseProfessorNames(profArray);

        Map<String, List<ClassInfo>>
                classMapFall = mapperForFall.mapClasses();

        Map<String, List<ClassInfo>>
                classMapSpring = mapperForSpring.mapClasses();


        // Now, we combine the professor array and the classes to
        // put professor objects inside the classes
        classMapFall = combiner.combine(classMapFall, profArray);
        classMapSpring = combiner.combine(classMapSpring, profArray);


        // Example of what the user would input and get back

        //DataTest tester = new DataTest(classMapFall, classMapSpring, bayesianSorter);
        //tester.testDataOutput();

        // Testing the UI
        UI testUI = new UI(classMapFall, classMapSpring, bayesianSorter);
        testUI.runUI();
    }

}



