package Tests;

import Managers.CalendarDescriptionManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.junit.Assert.*;

public class CalendarDescriptionManagerTest {

    private CalendarDescriptionManager calendarDescriptionManager;
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
            conn.prepareStatement("CREATE TABLE calendardescription ("
                    + "description TEXT,"
                    + "\"date\" DATE)").executeUpdate();
        }
        calendarDescriptionManager = new CalendarDescriptionManager(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        try (Connection con = dataSource.getConnection()) {
            con.prepareStatement("DROP TABLE calendardescription").executeUpdate();
            con.close();
        }
    }

    @Test
    public void testCreateDescription() throws Exception {
        try {
            calendarDescriptionManager.createDescription(null);
        }
        catch(Exception ex) {
            //OK
        }
        assertNull(calendarDescriptionManager.getDescription());

        String text = "Description";
        calendarDescriptionManager.createDescription(text);
        assertEquals(text, calendarDescriptionManager.getDescription());
    }

    @Test
    public void testEditDescription() throws Exception {
        String text = "Description";
        calendarDescriptionManager.createDescription(text);

        String anotherText = "Another text";
        calendarDescriptionManager.editDescription(anotherText);
        assertEquals(anotherText, calendarDescriptionManager.getDescription());

        calendarDescriptionManager.editDescription(null);
        assertNull(calendarDescriptionManager.getDescription());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertNull(calendarDescriptionManager.getDescription());
        String text = "Description";
        calendarDescriptionManager.createDescription(text);
        assertEquals(text, calendarDescriptionManager.getDescription());
        calendarDescriptionManager.editDescription(null);
        assertNull(calendarDescriptionManager.getDescription());
    }
}