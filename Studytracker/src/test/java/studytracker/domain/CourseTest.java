package studytracker.domain;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class CourseTest {
  
    @Test
    public void coursenameIsRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals("Tilpa", course.getName());
        
    }
    
    @Test
    public void courseGradeIsRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals(5, course.getGrade());
   
    }
    @Test
    public void coursePointsAreRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals(10, course.getStudyPoints());
        
    }
    @Test
    public void courseIdIsRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals(1, course.getId());
        
    }
    @Test
    public void courseUserIdIsRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals(2, course.getUserId());
        
    }
    @Test
    public void courseBooleanIsRight() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals(false, course.getPassed());
    }
    
    @Test
    public void toStringIsright() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        assertEquals("1 Tilpa 10 5", course.toString());
    }
    
    @Test
    public void setGradeTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setGrade(4);
        assertEquals(4, course.getGrade());
    }
    
    @Test
    public void setNameTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setName("Tiltu");
        assertEquals("Tiltu", course.getName());
    }
    
    @Test
    public void setIdTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setId(3);
        assertEquals(3, course.getId());
    }
    
    @Test
    public void setStudyPointsTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setStudyPoints(5);
        assertEquals(5, course.getStudyPoints());
    }
   
    @Test 
    public void setUserIdTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setUserId(3);
        assertEquals(3, course.getUserId());
    }
    
    @Test
    public void setPassedTest() {
        Course course = new Course(1, 2, "Tilpa", 10, 5, false);
        course.setPassed(true);
        assertEquals(true, course.getPassed());
    }
    
}
