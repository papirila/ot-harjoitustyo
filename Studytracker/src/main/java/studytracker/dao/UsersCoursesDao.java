package studytracker.dao;

import studytracker.databases.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studytracker.domain.UsersCourses;

public class UsersCoursesDao implements Dao {

    private Database database;
    private Connection conn;

    public UsersCoursesDao(Database database) throws SQLException {
        this.database = database;
        this.conn = database.getConnection();
    }

    @Override
    public Object findOne(Object key) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UsersCourses WHERE id = ?");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        UsersCourses uc = new UsersCourses(rs.getInt("id"), rs.getBoolean("passed"), rs.getInt("courseId"), rs.getInt("userId"));
        rs.close();
        stmt.close();
        conn.close();
        return uc;
    }

    @Override
    public List findAll() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UsersCourses");
        List<UsersCourses> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            UsersCourses uc = new UsersCourses(rs.getInt("id"), rs.getBoolean("passed"), rs.getInt("courseId"), rs.getInt("userId"));
            list.add(uc);
            System.out.println(uc);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    @Override
    public void delete(Object key) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM UsersCourses WHERE id = ?");
        stmt.setObject(1, key);
        stmt.executeUpdate();
        conn.close();
    }
}
