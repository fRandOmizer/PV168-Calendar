package Tests;

import Containers.Day;
import Containers.Note;
import Managers.CalendarDescriptionManager;
import Managers.CalendarManager;
import Managers.DayManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.Console;
import java.sql.Connection;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class CalendarManagerTest {

    private CalendarManager calendarManager;
    private DataSource dataSourceNote;
    private DataSource dataSourceDay;
    private DataSource dataSourceDescription;
    @Before
    public void setUp() throws Exception {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost:5432/PV168");
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUsername("postgres");
        bds.setPassword("PV168");
        this.dataSourceNote = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE note ("
                    + "id SERIAL,"
                    + "subject TEXT,"
                    + "description TEXT,"
                    + "\"date\" DATE,"
                    + "is_done BOOLEAN)").executeUpdate();
        }

        bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost:5432/PV168");
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUsername("postgres");
        bds.setPassword("PV168");
        this.dataSourceDay = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE day ("
                    + "numberOfFinishedNotes INTEGER,"
                    + "numberOfNotes INTEGER,"
                    + "\"date\" DATE)").executeUpdate();
        }

        bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost:5432/PV168");
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUsername("postgres");
        bds.setPassword("PV168");
        this.dataSourceDescription = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE calendardescription ("
                    + "description TEXT,"
                    + "\"date\" DATE)").executeUpdate();
        }
        calendarManager = new CalendarManager(dataSourceDay,dataSourceDescription,dataSourceNote);
    }

    @After
    public void tearDown() throws Exception {
       try (Connection con = dataSourceDay.getConnection()) {
            con.prepareStatement("DROP TABLE day").executeUpdate();
           con.close();
        }
        try (Connection con = dataSourceDescription.getConnection()) {
            con.prepareStatement("DROP TABLE calendardescription").executeUpdate();
            con.close();
        }
        try (Connection con = dataSourceNote.getConnection()) {
            con.prepareStatement("DROP TABLE note").executeUpdate();
            con.close();
        }
    }

    @Test
    public void testChangeMonth() throws Exception {
        Calendar c = Calendar.getInstance();
        Year year = Year.of(c.get(Calendar.YEAR));
        Month month = Month.of(c.get(Calendar.MONTH));
        List<Day> list = calendarManager.changeMonth(year, month);
        assertEquals(0, list.size());

        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");
        calendarManager.addNoteToDay(day, note);

        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = calendarManager.changeMonth(Year.of(c.get(Calendar.YEAR) - 1), month);
        assertEquals(0, list.size());
    }

    @Test
    public void testAddNoteToDay() throws Exception {
        Calendar c = Calendar.getInstance();
        Year year = Year.of(c.get(Calendar.YEAR));
        Month month = Month.of(c.get(Calendar.MONTH));

        List<Day> list;
        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");

        calendarManager.addNoteToDay(null, null);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(0, list.size());

        calendarManager.addNoteToDay(null, note);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(0, list.size());

        calendarManager.addNoteToDay(day, null);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(0, list.size());

        calendarManager.addNoteToDay(day, note);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals(notes.get(0), note);
    }

    @Test
    public void testEditNoteToDay() throws Exception {
        Calendar c = Calendar.getInstance();
        Year year = Year.of(c.get(Calendar.YEAR));
        Month month = Month.of(c.get(Calendar.MONTH));

        List<Day> list;
        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");

        calendarManager.addNoteToDay(day, note);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals(notes.get(0), note);

        note.setSubject("New Subject");
        calendarManager.editNoteToDay(day, note);
        notes = calendarManager.getNotesForDay(day);
        assertEquals(1, notes.size());
        assertEquals(notes.get(0), note);
    }

    @Test
    public void testDeleteNoteToDay() throws Exception {
        Calendar c = Calendar.getInstance();
        Year year = Year.of(c.get(Calendar.YEAR));
        Month month = Month.of(c.get(Calendar.MONTH));

        List<Day> list;
        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");

        calendarManager.addNoteToDay(day, note);
        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(new Integer(1), new Integer(list.size()));
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
        assertNotNull(notes);
        assertEquals(new Integer(1), new Integer(notes.size()));
        assertEquals(notes.get(0), note);

        calendarManager.deleteNoteToDay(day, note);
        notes = calendarManager.getNotesForDay(day);
        assertNotNull(notes);
        assertEquals(new Integer(0), new Integer(notes.size()));
    }
}