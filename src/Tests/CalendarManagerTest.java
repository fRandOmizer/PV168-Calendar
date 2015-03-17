package Tests;

import Containers.Day;
import Containers.Note;
import Managers.CalendarManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class CalendarManagerTest {

    private CalendarManager calendarManager;

    @Before
    public void setUp() throws Exception {
        calendarManager = new CalendarManager();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testChangeMonth() throws Exception {
        Calendar c = Calendar.getInstance();
        Year year = Year.of(c.get(Calendar.YEAR));
        Month month = Month.of(c.get(Calendar.MONTH));
        List<Day> list = calendarManager.changeMonth(year, month);
        assertNull(list);

        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");
        calendarManager.addNoteToDay(day, note);

        list = calendarManager.changeMonth(year, month);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = calendarManager.changeMonth(Year.of(c.get(Calendar.YEAR) - 1), month);
        assertNull(list);
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