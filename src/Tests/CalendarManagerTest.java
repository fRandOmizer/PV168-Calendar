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
import java.util.Date;
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
        List<Day> list = calendarManager.ChangeMonth(year, month);
        assertNull(list);

        Day day = new Day();
        Note note = new Note();
        note.setDescription("Description");
        note.setSubject("Subject");
        calendarManager.AddNoteToDay(day,note);

        list = calendarManager.ChangeMonth(year, month);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = calendarManager.ChangeMonth(Year.of(c.get(Calendar.YEAR)-1), month);
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

        calendarManager.AddNoteToDay(null,null);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(0, list.size());

        calendarManager.AddNoteToDay(null,note);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(0, list.size());

        calendarManager.AddNoteToDay(day,null);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(0, list.size());

        calendarManager.AddNoteToDay(day,note);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(1, list.size());
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
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

        calendarManager.AddNoteToDay(day,note);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(1, list.size());
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
        assertEquals(1, notes.size());
        assertEquals(notes.get(0), note);

        note.setSubject("New Subject");
        calendarManager.EditNoteToDay(day, note);
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

        calendarManager.AddNoteToDay(day,note);
        list = calendarManager.ChangeMonth(year, month);
        assertEquals(1, list.size());
        assertEquals(list.get(0), day);

        List<Note> notes = calendarManager.getNotesForDay(day);
        assertEquals(1, notes.size());
        assertEquals(notes.get(0), note);

        calendarManager.DeleteNoteToDay(day, note);
        notes = calendarManager.getNotesForDay(day);
        assertEquals(0, notes.size());
    }
}