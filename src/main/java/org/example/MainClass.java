package org.example;

import java.util.*;
import java.util.stream.Collectors;

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

        // Combine the two maps
        // Map<String, List<ClassInfo>> combinedMap = Combiner.combineMaps(classMapFall, classMapSpring);
        // Give the best class of each subject
        //Map<String, ClassInfo> baySortedMap = bayesianSorter.baySortSubjects(combinedMap);

        bayesianSorter.getRankedClasses(classMapFall, "FILM 100");
        bayesianSorter.getRankedClasses(classMapSpring, "COMP 284");



    }

}
