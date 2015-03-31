package Tests;

import org.apache.commons.dbcp.BasicDataSource;
import Containers.Note;
import Managers.NoteManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class NoteManagerTest {

    private NoteManager noteManager;
    private DataSource dataSource;

    @Before
    public void setUp() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost:5432/PV168");
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUsername("PV168");
        bds.setPassword("PV168");
        this.dataSource = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE note ("
                    + "id SERIAL,"
                    + "subject TEXT,"
                    + "description TEXT,"
                    + "\"date\" DATE,"
                    + "is_done BOOLEAN)").executeUpdate();
        }
        noteManager = new NoteManager(this.dataSource);
    }

    @After
    public void tearDown() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            con.prepareStatement("DROP TABLE note").executeUpdate();
        }
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
        note.setSubject("Subject");
        noteManager.addNote(note);
        List<Note> list = noteManager.findByDate(new Date(1970,1,1));
        assertEquals(1, list.size());
        noteManager.deleteNote(note);
        list = noteManager.findByDate(new Date(1970,1,1));
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
        note.setSubject("Subject");
        noteManager.addNote(note);
        noteManager.deleteNote(note);
        List<Note> list = noteManager.findByDate(new Date(0));
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
        note.setSubject("Subject");
        note.setDate(date);
        noteManager.addNote(note);
        List<Note> list = noteManager.findByDate(new Date(1980,1,1));
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
        note.setSubject("Subject");
        Note note2 = new Note();
        note2.setSubject("test");
        noteManager.addNote(note);
        noteManager.addNote(note2);
        Note returnedNote = noteManager.findByID(5);
        assertNull(returnedNote);
        returnedNote = noteManager.findByID(note.getID());
        assertEquals(note.getDescription(), returnedNote.getDescription());
        assertEquals(note.getSubject(), returnedNote.getSubject());
        assertEquals(note.getDate(), returnedNote.getDate());
        returnedNote = noteManager.findByID(note2.getID());
        assertEquals(note2.getDescription(), returnedNote.getDescription());
        assertEquals(note2.getSubject(), returnedNote.getSubject());
        assertEquals(note.getDate(), returnedNote.getDate());
    }
}