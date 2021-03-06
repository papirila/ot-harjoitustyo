
package studytracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import studytracker.databases.Database;
import studytracker.domain.User;


public class UserDaoTest {
    private Database database;
    private UserDao ud;
    private Connection conn;
    private User tommi;
    private User tommi2;
    private List<User> all;
    
    public UserDaoTest() {
    }
    
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        this.database = new Database("jdbc:sqlite:test.db");
        this.conn = database.getConnection();
        this.ud = new UserDao(database);
        this.tommi = (User) ud.findOne(5); 
        this.tommi2 = ud.findUser("tommi", "testeri");
        this.all = ud.findAll();
    }
    
    @Test
    public void findOneUserTest() {
        Assert.assertEquals("tommi", tommi.getName());
    }
    
    @Test
    public void findOneUserTest2() {
        Assert.assertEquals(5, tommi.getId());
    }
    
    @Test
    public void findOneUserTest3() {
        Assert.assertEquals("testeri", tommi.getPassword());
    }
    
    @Test
    public void findUserTest() throws SQLException {
        Assert.assertEquals("tommi", tommi2.getName());
    }
    
    @Test
    public void findUserTest2() throws InterruptedException {
        Assert.assertEquals(5, tommi2.getId());
    }
    
    @Test
    public void findUserTest3() {
        Assert.assertEquals("testeri", tommi2.getPassword());
    }
    
    @Test
    public void findAllTest() {
        assertEquals(5, all.size());
    }
    
    @Test
    public void userExistTest() throws SQLException {
        assertEquals(true, ud.userExists(tommi2));
    }
    
    @Test
    public void userExistTest2() throws SQLException {
        User matti = new User(3, "matti", "wii");
        assertEquals(false, ud.userExists(matti));
    }
    
    @Test 
    public void createTest() throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT max(id) FROM User");
        ResultSet rs = stmt.executeQuery();
        int id = rs.getInt(1) + 1;
        rs.close();
        stmt.close();
        conn.close();
        User user = new User(id, "name", "123");
        ud.create(user);
        User user2 = (User) ud.findOne(id);
        assertEquals(user.getName(), user2.getName());
    }
    
    /**
     * Poistetaan create testin tekemä käyttäjä jotta testit eivät mene pilalle
     */
    @Test
    public void fixausTest() throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT max(id) FROM User");
        ResultSet rs = stmt.executeQuery();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        
        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM User WHERE id = ?");
        stmt2.setInt(1, id);
        stmt2.executeUpdate();
        stmt.close();
        conn.close();
        assertEquals(true, true);
    }
}
