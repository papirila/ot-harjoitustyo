package studytracker.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author papirila
 */
public class CourseTest {
    
    public CourseTest() {
        
    }
    
    @Before
    public void setUp() {
    }
    @Test
    public void coursenameIsSetRight() {
        Course course = new Course(1, "Tilpa", 10, 5);
        assertEquals("Tilpa", course.getName());
        
    }
    @Test
    public void courseGradeIsSetRight() {
        Course course = new Course(1, "Tilpa", 10, 5);
        assertEquals(5, course.getGrade());
   
    }
    @Test
    public void coursePointsAreSetRight() {
        Course course = new Course(1, "Tilpa", 10, 5);
        assertEquals(10, course.getStudyPoints());
        
    }
    @Test
    public void courseIdIsSetRight() {
        Course course = new Course(1, "Tilpa", 10, 5);
        assertEquals(1, course.getId());
        
    }
    
//    @Test
//    public void poistoTesti() throws ClassNotFoundException, SQLException {
//        Database db = new Database("jdbc:sqlite:test.db");
//        CourseDao cd = new CourseDao(db);
//        cd.delete(3);
//    }
    
    
}
