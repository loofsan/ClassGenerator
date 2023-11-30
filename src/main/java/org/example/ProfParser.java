package org.example;

import java.util.List;

// Helper class for changing professor names
/*
    - Purpose: This class was intended to convert professor names in the
               array of professors converted from JSON.

    - Usage: This was a necessary process because the professor names in
             the professor had complete full names which the professor
             names in the Class Info objects didn't have. So, I couldn't
             match the names of the professors to find the professor
             objects and put them in there. Thus, I changed the professor
             names in the professor array first before finding names and
             matching professor objects to put in the ClassInfo object.
*/
public class ProfParser {
    public static void parseProfessorNames(List<Professor> professors) {
        for (Professor professor : professors) {
            String[] nameParts = professor.getName().split(" ");

            if (nameParts.length >= 2)
            {
                String lastName = nameParts[nameParts.length - 1];

                String firstNameInitial = nameParts[0].substring(
                        0, Math.min(1, nameParts[0].length()));

                professor.setName(lastName + ", " + firstNameInitial);
            }
        }
    }


}
