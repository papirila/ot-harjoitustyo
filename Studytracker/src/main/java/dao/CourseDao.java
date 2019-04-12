/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import databases.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pauliina
 */
public class CourseDao implements Dao {

    private Database database;
    private Connection conn;

    public CourseDao(Database database) throws SQLException {
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

    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
