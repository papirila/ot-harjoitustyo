package studytracker.dao;

import studytracker.databases.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    public List<Course> findAll() throws SQLException {
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
    
    public List<Course> findAllWithUserId(int id) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE userId = ?");
        stmt.setInt(1, id);
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
        stmt.close();
        conn.close();
    }

    public void create(Course course) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course (userId, name, studypoints, grade, passed)"
                + "VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, course.getUserId());
        stmt.setString(2, course.getName());
        stmt.setInt(3, course.getStudyPoints());
        stmt.setInt(4, course.getGrade());
        stmt.setBoolean(5, course.getPassed());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public int getMaxId() throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT max(id) FROM Course");
        ResultSet rs = stmt.executeQuery();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        conn.close();
        return id; 
    }
    
    public List<Course> passedCourses(int id) throws SQLException {
        List<Course> list = findAllWithUserId(id);
        List<Course> filtered = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGrade() > 0){
                filtered.add(list.get(i));
            }
        }
        
        return filtered;
    }
    
    public List<Course> chosenCourses(int id) throws SQLException {
        List<Course> list = findAllWithUserId(id);
        List<Course> filtered = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGrade() == -1){
                filtered.add(list.get(i));
            }
        }
        
        return filtered;
    }

}
