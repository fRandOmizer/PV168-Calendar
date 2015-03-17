package Interfaces;

import Containers.Note;

import java.util.Date;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */
public interface NoteManagerInterface {
    public void addNote(Note note);
    public void editNote(Note note);
    public void deleteNote(Note note);
    public List<Note> findByDate(Date date);
    public Note findByID(Integer ID);
}
