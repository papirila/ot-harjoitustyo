package Databases;

import java.sql.Connection;
import java.sql.SQLException;

public class StudiesDao {
    private Database database;
    private Connection conn;
    
    public StudiesDao(Database database) throws SQLException {
        this.database = database;
        this.conn = database.getConnection();
    }
}
