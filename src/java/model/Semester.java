package model;


public class Semester {
    public static int semester_FALL2023 = 1;
    
    private int semesterId;
    private String semesteerName;

    public Semester() {
    }

    public Semester(int semesterId, String semesteerName) {
        this.semesterId = semesterId;
        this.semesteerName = semesteerName;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesteerName() {
        return semesteerName;
    }

    public void setSemesteerName(String semesteerName) {
        this.semesteerName = semesteerName;
    }
    
    
}
