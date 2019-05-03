
package studytracker.dao;

import studytracker.databases.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studytracker.domain.Course;



public class CourseDao implements Dao {

    private Database database;
    private Connection conn;

    public CourseDao(Database database) throws SQLException {
        this.database = database;
    }

    @Override
    public Object findOne(Object key) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE id = ?");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        Course course = new Course(rs.getInt("id"), rs.getInt("userId"), rs.getString("name"), rs.getInt("studyPoints"), rs.getInt("grade"), rs.getBoolean("passed"));
        rs.close();
        stmt.close();
        conn.close();
        return course;
    }

    @Override
    public List findAll() throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course");
        List<Course> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Course course = new Course(rs.getInt("id"), rs.getInt("userId"), rs.getString("name"), rs.getInt("studyPoints"), rs.getInt("grade"), rs.getBoolean("passed"));
            list.add(course);
            System.out.println(course);
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public void delete(Object key) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course WHERE id = ?");
        stmt.setObject(1, key);
        stmt.executeUpdate();
        conn.close();
    }

}
