package org.example;
import org.openqa.selenium.json.Json;

import java.util.ArrayList;

public class ClassGenerator {
    public static void main(String[] args) {
        // Make an array list to contain all the professors
        ArrayList<Professor> profArray = new ArrayList<>();

        /*
            Instantiate the JsonToJava object to convert all the JSON
            objects in the JSON array in the file to java objects

         */
        //JsonToJava createProfObj = new JsonToJava();
        //createProfObj.convertProfJsonToJavaObj(profArray);

        /*
        ---
        Used this on Nov, 24, 2023
        to grab all the classes from web schedule
        ---

        WebScraper_Jsoup scraper = new WebScraper_Jsoup();
        scraper.getData();

        JsonCombiner cb = new JsonCombiner();
        cb.combine();
         */

    }
}
