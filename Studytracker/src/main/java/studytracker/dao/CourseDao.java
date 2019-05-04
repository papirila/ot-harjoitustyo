package studytracker.dao;

import studytracker.databases.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studytracker.domain.Course;

/**
 * Luokka vastaa luokan Course tietokantatoiminnasta
 */
public class CourseDao implements Dao {

    private Database database;
    private Connection conn;

    public CourseDao(Database database) throws SQLException {
        this.database = database;
    }

    /**
     * Etsii tietokannan Course-taulusta yhden kurssin id:n perusteella,
     * palauttaa null jos kurssia ei löydy.
     */
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

    /**
     * Etsii tietokannan Course-taulusta kaikki kurssit, ja palauttaa ne
     * listana.
     */
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

    /**
     * Etsii tietokannan Course-taulusta kaikki yhden käyttäjän kurssit
     * käyttäjän id:llä (eli userId:llä). Palauttaa käyttäjän kurssit listana.
     */
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

    /**
     * Poistaa kurssin Course-taulusta kurssin id:llä.
     */
    public void delete(int id) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Course WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Luo uuden kurssin Course-tietokantatauluun.
     */
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

    /**
     * Palauttaa suurimman id:n arvon Course-tietokantataulun kurssien
     * id-arvoista.
     */
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

    /**
     * Palauttaa käyttäjän läpäistyt kurssit. Metodille annetaan parametriksi
     * käyttäjän id. Metodi käyttää hyväkseen luokan findAllWithUserId-metodia,
     * jolla läydetään kaikki käyttäjän kurssit, jotka filtteroidaan arvosanan
     * perusteella. Jos kurssin arvosana on yli 0, se lisätään läpäistyjen
     * kurssien listalle.
     */
    public List<Course> passedCourses(int id) throws SQLException {
        List<Course> list = findAllWithUserId(id);
        List<Course> filtered = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGrade() > 0) {
                filtered.add(list.get(i));
            }
        }
        return filtered;
    }

    /**
     * Palauttaa käyttäjän kurssit, joille hän on ilmoittautunut. Metodille
     * annetaan parametriksi käyttäjän id. Metodi käyttää hyväkseen luokan
     * findAllWithUserId-metodia, jolla läydetään kaikki käyttäjän kurssit,
     * jotka filtteroidaan arvosanan perusteella. Jos kurssin arvosana on -1, se
     * lisätään palautettavalle listalle.
     */
    public List<Course> chosenCourses(int id) throws SQLException {
        List<Course> list = findAllWithUserId(id);
        List<Course> filtered = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGrade() == -1) {
                filtered.add(list.get(i));
            }
        }
        return filtered;
    }

    /**
     * Metodille annetaan parametreiksi kurssin id ja kurssin uusi arvosana.
     * Metodi päivittää ilmoittauduttujen kurssien listalta kurssin id:n
     * perusteella läpäistyksi kurssiksi muuttamalla arvosanan nollasta
     * parametrina annetuksi arvosanaksi. Metodi myös päivittää kurssin
     * passed-totuusarvon todeksi (true), kun se oli ennen epätosi (false).
     */
    public void passCourse(int id, int grade) throws SQLException {
        conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Course SET grade = ?, passed = ? WHERE id = ? ");
        stmt.setInt(1, grade);
        stmt.setBoolean(2, true);
        stmt.setInt(3, id);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * Poistaisi kurssin parametrilla key, mutta tälle metodille ei ole
     * tarvetta, minkä vuoksi se heittää Exceptionin "Not supported yet."
     */
    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Laskee yhteen käyttäjän läpäistyjen kurssien opintopisteet. Metodille
     * annetaan parametrina käyttäjän id ja se käyttää hyväkseen
     * passedCourses-metodia, joka palauttaa käyttäjän läpäistyt kurssit..
     * Metodi palauttaa kokonaisopintopistemäärän.
     */
    public int allStudyPoints(int id) throws SQLException {
        List<Course> passedList = passedCourses(id);
        int points = 0;
        for (int i = 0; i < passedList.size(); i++) {
            int pointsFromOne = passedList.get(i).getStudyPoints();
            points += pointsFromOne;
        }
        return points;
    }

}
