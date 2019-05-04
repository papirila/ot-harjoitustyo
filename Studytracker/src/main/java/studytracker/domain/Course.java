package studytracker.domain;

public class Course {

    private int id;
    private int userId;
    private String name;
    private int studyPoints;
    private int grade;
    private boolean passed;

    /**
     * Yksittäistä kurssia kuvaava luokka
     */
    public Course(int id, int userId, String name, int studyPoints, int grade, boolean passed) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.studyPoints = studyPoints;
        this.grade = grade;
        this.passed = passed;
    }
    
    public Course(int id, int userId, String name, int studyPoints) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.studyPoints = studyPoints;
        this.grade = -1;
        this.passed = false;
    }
    
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public boolean getPassed() {
        return this.passed;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.studyPoints + " " + this.grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudyPoints(int studyPoints) {
        this.studyPoints = studyPoints;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

}
