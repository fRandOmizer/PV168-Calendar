package Tests;

import Containers.Day;
import Managers.DayManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DayManagerTest {
    private DayManager dayManager;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost:5432/PV168");
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUsername("postgres");
        bds.setPassword("PV168");
        this.dataSource = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE day ("
                    + "numberOfFinishedNotes INTEGER,"
                    + "numberOfNotes INTEGER,"
                    + "\"date\" DATE)").executeUpdate();
        }
        dayManager = new DayManager(dataSource);
    }
    @After
    public void tearDown() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            con.prepareStatement("DROP TABLE day").executeUpdate();
            con.close();
        }
    }
    @Test
    public void testCreateDay() throws Exception {
        try {
            dayManager.createDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date(1970,1,1));
        Day day = dayManager.findByDay(new Date(1970,1,1));
        assertNotNull(day);
        dayManager.deleteDay(day);
        day = dayManager.findByDay(new Date(1970,1,1));
        assertNull(day);
    }

    @Test
    public void testEditDay() throws Exception {
        //?? co ma tato funkcia robit??
    }

    @Test
    public void testDeleteDay() throws Exception {
        try {
            dayManager.deleteDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date(1970,1,1));
        Day day = dayManager.findByDay(new Date(1970,1,1));
        dayManager.deleteDay(day);
        day = dayManager.findByDay(new Date(1970,1,1));
        assertNull(day);
    }

    @Test
    public void testFindByDay() throws Exception {
        try {
            dayManager.findByDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date(1970,1,1));
        Day day = dayManager.findByDay(new Date(1970,1,1));
        assertEquals(day.getIDDate(),new Date(1970,1,1));
    }
}