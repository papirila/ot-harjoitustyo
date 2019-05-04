package studytracker.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void userNameIsRight() {
        User user = new User(1, "Mary", "123");
        assertEquals("Mary", user.getName());
    }

    @Test
    public void userIdIsRight() {
        User user = new User(1, "Mary", "123");
        assertEquals(1, user.getId());
    }

    @Test
    public void passwordIsRight() {
        User user = new User(1, "Mary", "123");
        assertEquals("123", user.getPassword());
    }
    
    @Test
    public void setUserNameTest() {
        User user = new User(1, "Mary", "123");
        user.setName("Matilda");
        assertEquals("Matilda", user.getName());
    }

    @Test
    public void setUserIdTest() {
        User user = new User(1, "Mary", "123");
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void setPasswordTest() {
        User user = new User(1, "Mary", "123");
        user.setPassword("112");
        assertEquals("112", user.getPassword());
    }

    @Test
    public void toStringIsRight() {
        User user = new User(1, "Mary", "123");
        assertEquals("Mary 123", user.toString());
    }

    @Test
    public void testSymmetric() {
        User user1 = new User(1, "Mary", "123"); 
        User user2 = new User(1, "Mary", "123");
        Assert.assertTrue(user1.equals(user2) && user2.equals(user1));
        Assert.assertTrue(user1.hashCode() == user2.hashCode());
    }
    
    
}
