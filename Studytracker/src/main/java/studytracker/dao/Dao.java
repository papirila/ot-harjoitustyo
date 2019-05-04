package studytracker.dao;

import java.sql.*;
import java.util.*;

    /**
     * Rajapinta CourseDao ja UserDao luokille
     */
public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;

    //T saveOrUpdate(T object) throws SQLException;

    void delete(K key) throws SQLException;
}
