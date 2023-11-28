package org.example;

class ClassInfo {
    private String className;
    private String professorName;
    private Professor professor;
    private int crn;
    private String days;
    private String status;
    private int units;

    public ClassInfo(String className, String professorName,
                     int crn, String days, String status, int units) {
        this.className = className;
        this.professorName = professorName;
        this.crn = crn;
        this.days = days;
        this.status = status;
        this.units = units;
    }

    // Getters and setters for each attribute
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setProfessorObject(Professor professor) {
        this.professor = professor;
        this.professorName = professor.getName();
    }

    public Professor getProfessorObject() {
        return professor;
    }

    @Override
    public String toString() {
        if (professor != null) {
            return "Class: " + className + ", Professor: " + professor + ", Status: " +
                    status + ", Days: " + days + ", CRN: " + crn + ", Units: " + units;
        } else {
            return "Class: " + className + ", Professor: " + professorName + ", Status: " +
                    status + ", Days: " + days + ", CRN: " + crn + ", Units: " + units;
        }
    }
}
