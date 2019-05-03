
package studytracker.dao;

import studytracker.databases.Database;
import studytracker.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao {

    private Database database;
    private Connection conn;

    public UserDao(Database database) throws SQLException {
        this.database = database;
    }

    @Override
    public Object findOne(Object key) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

    public User findUser(String name, String password) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE name = ? AND password = ?");
        stmt.setString(1, name);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

    @Override
    public List findAll() throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");
        List<User> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
            list.add(user);
            System.out.println(user);
        }

        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public boolean userExists(User user) throws SQLException {
        User user2 = findUser(user.getName(), user.getPassword());
        if (user.equals(user2)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
