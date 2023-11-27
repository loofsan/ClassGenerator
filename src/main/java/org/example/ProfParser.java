package org.example;

import java.util.List;

public class ProfParser {
    public static void parseProfessorNames(List<Professor> professors) {
        for (Professor professor : professors) {
            String[] nameParts = professor.getName().split(" ");

            if (nameParts.length >= 2) {
                String lastName = nameParts[nameParts.length - 1];
                String firstNameInitial = nameParts[0].substring(0, Math.min(1, nameParts[0].length()));
                professor.setName(lastName + ", " + firstNameInitial);
            }
        }
    }


}
