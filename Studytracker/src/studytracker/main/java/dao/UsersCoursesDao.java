package dao;

import databases.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsersCoursesDao implements Dao {

    private Database database;
    private Connection conn;

    public UsersCoursesDao(Database database) throws SQLException {
        this.database = database;
        this.conn = database.getConnection();
    }

    @Override
    public Object findOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Object saveOrUpdate(Object object) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
