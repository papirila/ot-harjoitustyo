package dao;

import databases.Database;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersCoursesDao {
    private Database database;
    private Connection conn;
    
    public UsersCoursesDao(Database database) throws SQLException {
        this.database = database;
        this.conn = database.getConnection();
    }
}
