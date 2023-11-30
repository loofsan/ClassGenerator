package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataTest {
    Map<String, List<ClassInfo>> classMapFall;
    Map<String, List<ClassInfo>>classMapSpring;
    BayesianSorter bayesianSorter;

    DataTest(Map<String, List<ClassInfo>> classMapFall,
             Map<String, List<ClassInfo>>classMapSpring,
             BayesianSorter bayesianSorter) {
        this.classMapFall = classMapFall;
        this.classMapSpring = classMapSpring;
        this.bayesianSorter = bayesianSorter;
    }
    public void testDataOutput()
    {

        Scanner input = new Scanner(System.in);

        // TEST FOR DATA
        System.out.println("---------------------------------" +
                "-------------------------------\n");
        String sem = "";
        String course;
        String ans = "";

        System.out.println("""
                Hello, which semester would you like to search the classes in?
                Fall or Spring
                Choose (F) for Fall and (S) for Spring""");

        while (!sem.equals("X")) {

            sem = input.nextLine();

            // Fall Questions
            if (sem.equals("F")) {
                System.out.println("Hello, which course would you like to find" +
                        " out more about?\nWrite (Ex: 'GEOG 100')");
                course = input.nextLine();
                course = capitalizeAndConcatenate(course);

                String course1 = bayesianSorter.getBestClass(classMapFall, course);
                while (!course.equals("X"))
                    if (course1.equals("null")) {
                        System.out.println("No such course exists. Could you" +
                                " re-enter, please?\nPress 'X' to stop");
                        course = input.nextLine();
                        course = capitalizeAndConcatenate(course);
                        if (course.equals("X")) {
                            sem = "X";
                        }
                    } else {
                        System.out.println("The best class for that course according " +
                                "to our probability is: \n");
                        System.out.println
                                (bayesianSorter.getBestClass(classMapFall, course));

                        System.out.println("""

                                Would you like to see other classes ranked in order?
                                Enter 'Y' for Yes and 'N' for No""");

                        while (!ans.equals("X")) {

                            ans = input.nextLine();
                            switch (ans) {
                                case "X" -> {
                                    sem = "X";
                                    course = "X";
                                }
                                case "Y" -> bayesianSorter.getRankedClasses
                                        (classMapFall, course);

                                case "N" -> System.out.println
                                        ("Have a nice day!");

                                default -> System.out.println
                                        ("I couldn't register that. Could you " +
                                                "enter it again?\n" +
                                                "Enter 'X' to stop");
                            }
                        }
                    }
            }
            // Spring Questions
            else if (sem.equals("S")) {

                System.out.println("Hello, which course would you like to find" +
                        " out more about?\nWrite (Ex: 'GEOG 100')");

                course = input.nextLine();
                course = capitalizeAndConcatenate(course);

                String course1 = bayesianSorter.getBestClass
                        (classMapSpring, course);

                while (!course.equals("X")) {

                    if (course1.equals("null")) {
                        System.out.println("No such course exists. Could you" +
                                " re-enter, please?\nPress 'X' to stop");
                        course = input.nextLine();
                        course = capitalizeAndConcatenate(course);
                        if (course.equals("X")) {
                            sem = "X";
                        }
                    } else {
                        System.out.println("The best class for " +
                                "that course according " +
                                "to our probability is: \n");
                        System.out.println
                                (bayesianSorter.getBestClass
                                        (classMapSpring, course));

                        System.out.println("""
                                                                    
                                Would you like to see other classes ranked in order?
                                Enter 'Y' for Yes and 'N' for No""");

                        while (!ans.equals("X")) {

                            ans = input.nextLine();
                            switch (ans) {
                                case "X" -> {
                                    sem = "X";
                                    course = "X";
                                }
                                case "Y" -> bayesianSorter.getRankedClasses
                                        (classMapSpring, course);

                                case "N" -> System.out.println
                                        ("Have a nice day!");

                                default -> System.out.println
                                        ("I couldn't register that. Could you " +
                                                "enter it again?\n" +
                                                "Enter 'X' to stop");
                            }
                        }
                    }
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
