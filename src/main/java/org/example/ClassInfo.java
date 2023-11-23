package org.example;

class ClassInfo {
    private String className;
    private String professor;
    private String crn;
    private String days;
    private String status;

    public ClassInfo(String className, String professor, String crn, String days, String status) {
        this.className = className;
        this.professor = professor;
        this.crn = crn;
        this.days = days;
        this.status = status;
    }

    // Getters and setters for each attribute
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
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

    @Override
    public String toString() {
        return "Class: " + className + ", Professor: " + professor + ", Status: " +
                status + ", Days: " + days + ", CRN: " + crn;
    }
}
