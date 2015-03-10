package TestManagers;

import Containers.Day;
import Containers.Note;
import Managers.CalendarManager;
import Managers.DayManager;
import Managers.NoteManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CalendarManagerTest {

    private Day nullDay;
    private Day nullDateDay;
    private Note nullDestcriptionNote;
    private Note nullDDateNote;
    private DayManager dayManager;
    private NoteManager noteManager;
    private CalendarManager calendarManager;

    @org.junit.Before
    public void setUp() throws Exception {

        calendarManager = new CalendarManager();
        dayManager = new DayManager();
        noteManager = new NoteManager();

        nullDay = null;

        nullDateDay = new Day();
        nullDateDay.setIDDate(null);

        nullDestcriptionNote = new Note();
        nullDestcriptionNote.setDescription(null);

        nullDDateNote = new Note();
        nullDDateNote.setDate(null);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNullNoteToNullDay(){
        calendarManager.AddNoteToDay(nullDay,nullDDateNote);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNoteToNullDay(){
        calendarManager.AddNoteToDay(nullDay,new Note());
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNullNoteToDay(){
        calendarManager.AddNoteToDay(new Day(),nullDDateNote);
    }

    @org.junit.Test
    public void testAddNoteToDay() throws Exception{
        Day tempDay = new Day();
        Note tempNote = new Note();
        calendarManager.AddNoteToDay(tempDay,tempNote);
        List<Note> listNotes = noteManager.FindByDate(tempDay.getIDDate());
        if (listNotes.contains(tempNote))
        {
            assert (true);
        }
        else
        {
            assert (false);
        }
    }

    @org.junit.Test
    public void testEditNoteToDay() throws Exception {
        Day tempDay = new Day();
        Note tempNote = new Note();
        calendarManager.AddNoteToDay(tempDay,tempNote);
        tempNote.setDescription("haha");
        calendarManager.EditNoteToDay(tempDay,tempNote);
        Note temp2Note = noteManager.FindByID(tempNote.getID());
        if (temp2Note.equals(tempNote))
        {
            assert (true);
        }
        else
        {
            assert (false);
        }
    }

    @org.junit.Test
    public void testDeleteNoteToDay() throws Exception {
        Day tempDay = new Day();
        Note tempNote = new Note();
        calendarManager.AddNoteToDay(tempDay,tempNote);
        List<Note> listNotes = noteManager.FindByDate(tempDay.getIDDate());
        if (listNotes.contains(tempNote))
        {
            assert (false);
        }
        else
        {
            assert (true);
        }
    }
}