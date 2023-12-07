package org.example;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class DataOutput {
    Map<String, List<ClassInfo>> classMapFall;
    Map<String, List<ClassInfo>>classMapSpring;
    BayesianSorter bayesianSorter;

    DataOutput(Map<String, List<ClassInfo>> classMapFall,
               Map<String, List<ClassInfo>>classMapSpring,
               BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }

    public String printResult(String userInput)
    {
        String sem = userInput.substring(0, 1);
        String course = userInput.substring(1).trim();
        String bestClass;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if (sem.equals("F")) {
            course = capitalizeAndConcatenate(course);
            ClassInfo course1 = bayesianSorter.getBestClass(classMapFall, course);
            if (course1 == null) {
                bestClass = "No such course exists.";
            } else {
                bestClass = "<html>" +
                        "<table>" +
                        "<tr><td>Professor:</td><td>" +
                        course1.getProfessorName()  + "</td></tr>" +
                        "<tr><td>Rating:</td><td>" +
                        df.format(course1.getProfessorObject().getRating()) +
                        "</td></tr>" +
                        "<tr><td>Number Of Ratings:</td><td>" +
                        course1.getProfessorObject().getNumOfRatings() +
                        "</td></tr>" +
                        "<tr><td>Difficulty:</td><td>" +
                        df.format(course1.getProfessorObject().getDifficulty()) +
                        "</td></tr>" +
                        "<tr><td>CRN:</td><td>" +
                        course1.getCrn() + "</td></tr>" +
                        "<tr><td>Days:</td><td>" +
                        course1.getDays() + "</td></tr>" +
                        "<tr><td>Units:</td><td>" +
                        course1.getUnits() + "</td></tr>" +
                        "<tr><td>Status:</td><td>" +
                        course1.getStatus() + "</td></tr>" +
                        "<tr><td>Class:</td><td>" +
                        course1.getClassName() + "</td></tr>" +
                        "</table>" +
                        "</html>";
            }
        } else if (sem.equals("S")) {
            course = capitalizeAndConcatenate(course);
            ClassInfo course1 = bayesianSorter.getBestClass(classMapSpring, course);
            if (course1 == null) {
                bestClass = "No such course exists.";
            } else {
                bestClass = "<html>" +
                        "<table>" +
                        "<tr><td>Professor:</td><td>" +
                        course1.getProfessorName()  + "</td></tr>" +
                        "<tr><td>Rating:</td><td>" +
                        df.format(course1.getProfessorObject().getRating()) +
                        "</td></tr>" +
                        "<tr><td>Number Of Ratings:</td><td>" +
                        course1.getProfessorObject().getNumOfRatings() +
                        "</td></tr>" +
                        "<tr><td>Difficulty:</td><td>" +
                        df.format(course1.getProfessorObject().getDifficulty()) +
                        "</td></tr>" +
                        "<tr><td>CRN:</td><td>" +
                        course1.getCrn() + "</td></tr>" +
                        "<tr><td>Days:</td><td>" +
                        course1.getDays() + "</td></tr>" +
                        "<tr><td>Units:</td><td>" +
                        course1.getUnits() + "</td></tr>" +
                        "<tr><td>Status:</td><td>" +
                        course1.getStatus() + "</td></tr>" +
                        "<tr><td>Class:</td><td>" +
                        course1.getClassName() + "</td></tr>" +
                        "</table>" +
                        "</html>";
            }
        } else {
            bestClass = "I couldn't register the semester.";
        }

        return bestClass;
    }

    // Method to capitalize the whole word and concatenate with the rest of the string
    private static String capitalizeAndConcatenate(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            // If the string is null or empty, return the original string
            return inputString;
        } else {
            // Split the string into words
            String[] words = inputString.split("\\s+"); //
            // \\s+ matches one or more whitespace characters

            // Capitalize each word
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                result.append(words[i].toUpperCase());
                if (i < words.length - 1) {
                    result.append(" "); // Add a space between words
                }
            }

            return result.toString();
        }
    }
}