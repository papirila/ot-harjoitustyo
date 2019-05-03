package studytracker.domain;



import org.junit.Before;
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
    @Test 
    public void toStringIsRight() {
        User user = new User(1, "Mary", "123");
        assertEquals("Mary 123", user.toString());
    }
    @Test 
    public void hashCodeTest() {
        
    }
//    @Test
//    public void testEquals_Symmetric() {
//    Person x = new Person("Foo Bar");  // equals and hashCode check name field value
//    Person y = new Person("Foo Bar");
//    Assert.assertTrue(x.equals(y) && y.equals(x));
//    Assert.assertTrue(x.hashCode() == y.hashCode());
//    }
}
