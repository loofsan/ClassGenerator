package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        // Make an array list to contain all the professors
        ArrayList<Professor> profArray = new ArrayList<>();
        // Instantiate professor parser
        ProfParser profParser = new ProfParser();
         /*
        Instantiate the JsonToJava object to convert all the JSON
        objects in the JSON array in the file to java objects
        */
        JsonToJava createProfObj = new JsonToJava();

        MapClasses mapperForFall = new MapClasses
                ("src/main/resources/fallCombinedClasses.json");
        MapClasses mapperForSpring = new MapClasses
                ("src/main/resources/springCombinedClasses.json");

        Combiner combiner = new Combiner();

        // Now, we have an array of professor objects, a hashmap of
        // subjects as keys and an array of classes as a value.
        createProfObj.convertProfJsonToJavaObj(profArray);
        ProfParser.parseProfessorNames(profArray);

        Map<String, List<ClassInfo>>
                classMapFall = mapperForFall.mapClasses();

        Map<String, List<ClassInfo>>
                classMapSpring = mapperForSpring.mapClasses();


        // Now, we combine them.
        combiner.combine(classMapSpring, profArray);
        combiner.combine(classMapFall, profArray);



    }

}
