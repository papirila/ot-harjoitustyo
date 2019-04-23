
package studytracker.domain;


public class Course {
    
    private int id;
    private String name;
    private int studyPoints;
    private int grade;

    public Course(int id, String name, int studyPoints, int grade) {
        this.id = id;
        this.name = name;
        this.studyPoints = studyPoints;
        this.grade = grade;
    }

    public Course(int id, String name, int studyPoints) {
        this.id = id;
        this.name = name;
        this.studyPoints = studyPoints;
    }
    
    

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
}
