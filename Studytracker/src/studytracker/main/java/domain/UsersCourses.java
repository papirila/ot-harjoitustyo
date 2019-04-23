
package domain;


public class UsersCourses {
    
    private int id;
    private boolean passed;
    private int courseId;
    private int userId;

    public UsersCourses(int id, boolean passed, int courseId, int userId) {
        this.id = id;
        this.passed = passed;
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
    
    
    
}
