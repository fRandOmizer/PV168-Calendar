package Tests;

import Containers.Note;
import Managers.NoteManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class NoteManagerTest {

    private NoteManager noteManager;

    @Before
    public void setUp() throws Exception {
        noteManager = new NoteManager();
    }

    @Test
    public void testAddNote() throws Exception {
        try {
            noteManager.AddNote(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        noteManager.AddNote(note);
        List<Note> list = noteManager.FindByDate(new Date());
        assertEquals(1, list.size());
        noteManager.DeleteNote(note);
        list = noteManager.FindByDate(new Date());
        assertEquals(0, list.size());
    }

    @Test
    public void testEditNote() throws Exception {
        //??
    }

    @Test
    public void testDeleteNote() throws Exception {
        try {
            noteManager.DeleteNote(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        noteManager.AddNote(note);
        noteManager.DeleteNote(note);
        List<Note> list = noteManager.FindByDate(new Date());
        assertEquals(0, list.size());
    }

    @Test
    public void testFindByDate() throws Exception {
        try {
            noteManager.FindByDate(null);
        }
        catch (Exception ex) {
            //OK
        }
        Date date = new Date(2000);
        Note note = new Note();
        note.setDate(date);
        noteManager.AddNote(note);
        List<Note> list = noteManager.FindByDate(new Date());
        assertEquals(0, list.size());
        list = noteManager.FindByDate(date);
        assertEquals(1, list.size());
        noteManager.DeleteNote(note);
    }

    @Test
    public void testFindByID() throws Exception {
        try {
            noteManager.FindByID(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        note.setID(10);
        Note note2 = new Note();
        note2.setID(20);
        note2.setSubject("test");
        noteManager.AddNote(note);
        noteManager.AddNote(note2);
        Note returnedNote = noteManager.FindByID(2);
        assertNull(returnedNote);
        returnedNote = noteManager.FindByID(10);
        assertEquals(note, returnedNote);
        returnedNote = noteManager.FindByID(20);
        assertEquals(note2, returnedNote);
    }
}