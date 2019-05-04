package studytracker.dao;

import studytracker.databases.Database;
import studytracker.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka vastaa Course-luokan tietokantatoiminnasta
 */
public class UserDao implements Dao {

    private Database database;
    private Connection conn;

    public UserDao(Database database) throws SQLException {
        this.database = database;
    }

    /**
     * Etsii tietokannan User-taulusta yhden käyttäjän id:n perusteella,
     * palauttaa null jos käyttäjää ei löydy.
     */
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

    /**
     * Etsii yhden käyttäjän tietokannan User-taulusta nimen ja salasanan
     * perusteella, palauttaa null jos käyttäjää ei löydy.
     */
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

    /**
     * Etsii kaikki käyttäjät taulusta User ja palauttaa ne listana.
     */
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

    /**
     * Tarkistaa onko käyttäjää olemassa, annetaan parametriksi käyttäjä, jota
     * etsitään. Palauttaa true jos käyttäjä löytyy, muulloin false.
     */
    public boolean userExists(User user) throws SQLException {
        User user2 = findUser(user.getName(), user.getPassword());
        if (user.equals(user2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Poistaisi käyttäjän, mutta tälle metodille ei ole tarvetta, minkä vuoksi
     * se heittää Exceptionin "Not supported yet."
     */
    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metodi luo uuden käyttäjän tietokantaan. Parametrina annetaan käyttäjä, joka halutaan
     * lisätä.
     */
    public void create(User user) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, password)"
                + "VALUES (?, ?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    

}
