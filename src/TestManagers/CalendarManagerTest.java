package TestManagers;

import Containers.Day;
import Containers.Note;
import Managers.CalendarManager;
import Managers.DayManager;
import Managers.NoteManager;
import org.junit.Test;
import junit.framework.Assert;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalendarManagerTest {

    private Day testDay;
    private Day testDay2;
    private Note testNote;
    private Note testNote2;

    private DayManager dayManager;
    private NoteManager noteManager;
    private CalendarManager calendarManager;

    @org.junit.Before
    public void setUp() throws Exception {

        calendarManager = new CalendarManager();
        dayManager = new DayManager();
        noteManager = new NoteManager();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNullNoteToNullDay(){
        testDay = null;
        testNote = null;
        calendarManager.addNoteToDay(null, null);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNoteToNullDay(){
        testDay = null;
        testNote = new Note();
        testNote.setNote("Test","Test content",new Date(768,1,1));
        calendarManager.addNoteToDay(testDay, testNote);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testAddNullNoteToDay(){
        testDay = new Day();
        testDay.setDay(new Date(768,1,1),0,0);
        testNote = null;
        calendarManager.addNoteToDay(testDay, testNote);
    }

    @org.junit.Test
    public void testAddNoteToDay() throws Exception{
        testDay = new Day();
        testDay.setDay(new Date(768,1,1),0,0);
        testNote = new Note();
        testNote.setNote("Test","Test content",new Date(768,1,1));

        List<Note> listOfNotes=noteManager.findByDate(testNote.getDate());

        if(listOfNotes.size()>0)
        {
            for(Note note : listOfNotes)
            {
                noteManager.deleteNote(note);
            }
        }

        calendarManager.addNoteToDay(testDay, testNote);
        listOfNotes = noteManager.findByDate(testDay.getIDDate());

        assertTrue(listOfNotes.size()==1);
        assertTrue(listOfNotes.contains(testNote));
    }

    @org.junit.Test
    public void testEditNoteToDay() throws Exception {
        testDay = new Day();
        testDay.setDay(new Date(768,1,1),0,0);
        testNote = new Note();
        testNote.setNote("Test","Test content",new Date(768,1,1));

        List<Note> listOfNotes=noteManager.findByDate(testNote.getDate());

        if(listOfNotes.size()>0)
        {
            for(Note note : listOfNotes)
            {
                noteManager.deleteNote(note);
            }
        }

        calendarManager.addNoteToDay(testDay, testNote);
        listOfNotes = noteManager.findByDate(testDay.getIDDate());

        assertTrue(listOfNotes.size()==1);
        assertTrue(listOfNotes.contains(testNote));

        testNote.setNote("Test","Test content - changed",new Date(768,1,1));
        calendarManager.editNoteToDay(testDay, testNote);
        listOfNotes=noteManager.findByDate(testNote.getDate());

        assertTrue(listOfNotes.size()==1);
        assertTrue(listOfNotes.contains(testNote));
    }

    @org.junit.Test
    public void testDeleteNoteToDay() throws Exception {
        testDay = new Day();
        testDay.setDay(new Date(768,1,1),0,0);
        testNote = new Note();
        testNote.setNote("Test","Test content",new Date(768,1,1));

        List<Note> listOfNotes=noteManager.findByDate(testNote.getDate());

        if(listOfNotes.size()>0)
        {
            for(Note note : listOfNotes)
            {
                noteManager.deleteNote(note);
            }
        }

        calendarManager.addNoteToDay(testDay, testNote);
        listOfNotes = noteManager.findByDate(testDay.getIDDate());

        assertTrue(listOfNotes.size()==1);
        assertTrue(listOfNotes.contains(testNote));

        calendarManager.deleteNoteToDay(testDay, testNote);
        listOfNotes=noteManager.findByDate(testNote.getDate());

        assertTrue(!listOfNotes.contains(testNote));
        assertTrue(listOfNotes.size()==0);
    }
}