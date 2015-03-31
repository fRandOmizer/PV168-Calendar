package Managers;

import Containers.Note;
import Exceptions.ServiceFailureException;
import Interfaces.NoteManagerInterface;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.sql.*;


/**
 * Created by Richard on 8. 3. 2015.
 */
public class NoteManager implements NoteManagerInterface {

    final static Logger logger = Logger.getLogger(NoteManager.class.getName());

    private final DataSource dataSource;

    public NoteManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addNote(Note note) {
        if(note == null) throw new IllegalArgumentException("Note is null");
        if(note.getDate() == null) throw new IllegalArgumentException("Note's date is null");
        if(note.getID() != null) throw new IllegalArgumentException("Note's id is already set");
        if(note.getDescription() == null) throw new IllegalArgumentException("Note's description is null");
        if(note.getSubject().equals("")) throw new IllegalArgumentException("Note's subject is not set");

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO note (subject,description,\"date\",is_done) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1, note.getSubject());
                st.setString(2, note.getDescription());
                st.setDate(3, note.getDate());
                st.setBoolean(4, note.isDone());
                int addedRows = st.executeUpdate();
                if(addedRows != 1) {
                    throw new ServiceFailureException("Internal Error: More rows inserted when trying to insert note " + note);
                }
                ResultSet keyRS = st.getGeneratedKeys();
                if (keyRS.next()) {
                    int newId = keyRS.getInt(1);
                    note.setID(newId);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public void editNote(Note note){
        if(note==null) throw new IllegalArgumentException("note pointer is null");
        if(note.getID()==null) throw new IllegalArgumentException("note with null id cannot be updated");
        if(note.getSubject() == null) throw new IllegalArgumentException("note subject is null");
        if(note.getDescription() == null) throw new IllegalArgumentException("note description is null");
        if(note.getDate() == null) throw new IllegalArgumentException("note date is null");
        if(note.getSubject().equals("")) throw new IllegalArgumentException("note subject is empty");

        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement st = conn.prepareStatement("UPDATE note SET subject=?,description=?,\"date\"=?,is_done=? WHERE id=?")) {
                st.setString(1, note.getSubject());
                st.setString(2, note.getDescription());
                st.setDate(3, note.getDate());
                st.setBoolean(4, note.isDone());
                st.setInt(5, note.getID());
                if(st.executeUpdate()!=1) {
                    throw new IllegalArgumentException("cannot update note "+note);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public void deleteNote(Note note){
        if(note==null) throw new IllegalArgumentException("note pointer is null");
        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement st = conn.prepareStatement("DELETE FROM note WHERE id=?")) {
                st.setLong(1,note.getID());
                if(st.executeUpdate()!=1) {
                    throw new ServiceFailureException("did not delete note with id ="+note.getID());
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public List<Note> findByDate(Date date){
//        logger.logger(Level.INFO, "finding all notes");
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("SELECT id,subject,description,\"date\",is_done FROM note WHERE \"date\" = ?")) {
                st.setDate(1, date);
                ResultSet rs = st.executeQuery();
                List<Note> result = new ArrayList<>();
                while (rs.next()) {
                    Note note = new Note();
                    note.setID(rs.getInt("id"));
                    note.setSubject(rs.getString("subject"));
                    note.setDescription(rs.getString("description"));
                    note.setDate(rs.getDate("date"));
                    note.setDone(rs.getBoolean("is_done"));
                    result.add(note);
                }
                return result;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }

    @Override
    public Note findByID(Integer ID){
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("SELECT id,subject,description,\"date\",is_done FROM note WHERE id = ?")) {
                st.setInt(1, ID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    Note note = new Note();
                    note.setID(rs.getInt("id"));
                    note.setSubject(rs.getString("subject"));
                    note.setDescription(rs.getString("description"));
                    note.setDate(rs.getDate("date"));
                    note.setDone(rs.getBoolean("is_done"));
                    if(rs.next()) {
                        Note nextNote = new Note();
                        nextNote.setID(rs.getInt("id"));
                        nextNote.setSubject(rs.getString("subject"));
                        nextNote.setDescription(rs.getString("description"));
                        nextNote.setDate(rs.getDate("date"));
                        nextNote.setDone(rs.getBoolean("is_done"));
                        throw new ServiceFailureException(
                                "Internal error: More entities with the same id found "
                                        + "(source id: " + ID + ", found " + note + " and " + nextNote);
                    }
                    return note;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }

}
