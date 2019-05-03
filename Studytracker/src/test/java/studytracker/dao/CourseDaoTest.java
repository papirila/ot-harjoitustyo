
package studytracker.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import studytracker.databases.Database;
import studytracker.domain.Course;


public class CourseDaoTest {
    private Database database;
    private CourseDao cd;
    private Connection conn;
    private Course tiltu;
    private Course tiltu3;
    private List<Course> all;
    
    public CourseDaoTest() {
    }
    
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.database = new Database("jdbc:sqlite:test.db");
        this.conn = database.getConnection();
        this.cd = new CourseDao(database);
        this.tiltu = (Course) cd.findOne(6); 
        this.all = cd.findAll();
    }
    
    @Test 
    public void findOneCourseTest() {
        Assert.assertEquals("tiltu", tiltu.getName());    
    }
    
    @Test
    public void findOneCourseTest2() {
        Assert.assertEquals(6, tiltu.getId());    
    }
    
    @Test
    public void findOneCourseTest3() {
        Assert.assertEquals(5, tiltu.getUserId());    
    }
    
//    @Test
//    public void findOneCourseTest4() {
//        Assert.assertEquals(true, tiltu.getPassed());    
//    }
    
    @Test
    public void findOneCourseTest5() {
        Assert.assertEquals(5, tiltu.getGrade());    
    }
    
    @Test
    public void findOneCourseTest6() {
        Assert.assertEquals(5, tiltu.getStudyPoints());    
    }
    
    @Test
    public void findAllTest() {
        assertEquals(6, all.size());
    }
    
    
    
}
