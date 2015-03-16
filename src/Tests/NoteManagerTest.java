package Tests;

import Managers.NoteManager;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void testEditNote() throws Exception {

    }

    @Test
    public void testDeleteNote() throws Exception {

    }

    @Test
    public void testFindByDate() throws Exception {

    }

    @Test
    public void testFindByID() throws Exception {

    }
}