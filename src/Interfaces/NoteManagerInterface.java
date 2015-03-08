package Interfaces;

import Containers.Note;

import java.util.Date;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */
public interface NoteManagerInterface {
    public void AddNote(Note note);
    public void EditNote(Note note);
    public void DeleteNote(Note note);
    public List<Note> FindByDate(Date date);
    public Note FindByID(Integer ID);
}
