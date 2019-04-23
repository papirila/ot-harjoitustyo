/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.UsersCourses;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pauliina
 */
public class UsersCoursesTest {
    
    
    @Before
    public void setUp() {
    }
    @Test
    public void idIsSetRight(){
        UsersCourses uc = new UsersCourses(1, true, 2, 3);
        assertEquals(1, uc.getId());
    }
    @Test
    public void passedBooleanIsSetRight(){
        UsersCourses uc = new UsersCourses(1, true, 2, 3);
        assertEquals(true, uc.isPassed());
    }
    @Test
    public void userIdIsSetRight(){
        UsersCourses uc = new UsersCourses(1, true, 2, 3);
        assertEquals(3, uc.getUserId());
    }
    @Test
    public void courseIdIsSetRight(){
        UsersCourses uc = new UsersCourses(1, true, 2, 3);
        assertEquals(2, uc.getCourseId());
    }
    
}
