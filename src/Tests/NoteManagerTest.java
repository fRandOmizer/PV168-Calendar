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
            noteManager.addNote(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        noteManager.addNote(note);
        List<Note> list = noteManager.findByDate(new Date());
        assertEquals(1, list.size());
        noteManager.deleteNote(note);
        list = noteManager.findByDate(new Date());
        assertEquals(0, list.size());
    }

    @Test
    public void testEditNote() throws Exception {
        //??
    }

    @Test
    public void testDeleteNote() throws Exception {
        try {
            noteManager.deleteNote(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        noteManager.addNote(note);
        noteManager.deleteNote(note);
        List<Note> list = noteManager.findByDate(new Date());
        assertEquals(0, list.size());
    }

    @Test
    public void testFindByDate() throws Exception {
        try {
            noteManager.findByDate(null);
        }
        catch (Exception ex) {
            //OK
        }
        Date date = new Date(2000);
        Note note = new Note();
        note.setDate(date);
        noteManager.addNote(note);
        List<Note> list = noteManager.findByDate(new Date());
        assertEquals(0, list.size());
        list = noteManager.findByDate(date);
        assertEquals(1, list.size());
        noteManager.deleteNote(note);
    }

    @Test
    public void testFindByID() throws Exception {
        try {
            noteManager.findByID(null);
        }
        catch (Exception ex) {
            //OK
        }
        Note note = new Note();
        note.setID(10);
        Note note2 = new Note();
        note2.setID(20);
        note2.setSubject("test");
        noteManager.addNote(note);
        noteManager.addNote(note2);
        Note returnedNote = noteManager.findByID(2);
        assertNull(returnedNote);
        returnedNote = noteManager.findByID(10);
        assertEquals(note, returnedNote);
        returnedNote = noteManager.findByID(20);
        assertEquals(note2, returnedNote);
    }
}