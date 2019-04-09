

import domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class UserTest {
    
    
    @Before
    public void setUp() {
    }
    @Test
    public void userNameIsSetRight() {
        User user = new User(1, "Mary", "123");
        assertEquals("Mary", user.getName());
    }
    @Test
    public void userIdIsSetRight(){
        User user = new User(1, "Mary", "123");
        assertEquals(1, user.getId());
    }
    @Test 
    public void passwordIsSetRight(){
        User user = new User(1, "Mary", "123");
        assertEquals("123", user.getPassword());
    }
}
