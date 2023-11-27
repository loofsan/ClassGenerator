package org.example;

import java.util.List;
import java.util.Map;

public class Combiner {
    public void combine(Map<String, List<ClassInfo>> subjectMap, List<Professor> professors ) {

        updateProfessorsInSubjectMap(subjectMap, professors);

        // Print the updated subjectMap
        for (Map.Entry<String, List<ClassInfo>> entry : subjectMap.entrySet())
        {
            System.out.println("Subject: " + entry.getKey());
            for (ClassInfo classInfo : entry.getValue()) {
                System.out.println(classInfo);
            }
            System.out.println();
        }
    }

    public static void updateProfessorsInSubjectMap
            (Map<String, List<ClassInfo>> subjectMap, List<Professor> professors)
    {
        for (List<ClassInfo> classInfoList : subjectMap.values()) {
            for (ClassInfo classInfo : classInfoList) {
                String professorName = classInfo.getProfessorName();

                // Find the matching professor in the list
                Professor matchingProfessor = findProfessorByName(professors, professorName);

                // Update the professor for the classInfo
                if (matchingProfessor != null) {
                    classInfo.setProfessorObject(matchingProfessor);
                } else {
                    // If no match is found, create a new Professor object
                    Professor newProfessor = new Professor(professorName, 0.0F, 0, 0.0F);
                    classInfo.setProfessorObject(newProfessor);
                    professors.add(newProfessor);
                }

            }
        }
    }

    public static Professor findProfessorByName(List<Professor> professors, String professorName) {
        for (Professor professor : professors) {
            if (professor.getName().equals(professorName)) {
                return professor;
            }
        }
        return null; // Professor not found
    }

}



