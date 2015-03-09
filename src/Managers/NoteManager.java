package Managers;

import Containers.Note;
import Interfaces.NoteManagerInterface;

import java.util.Date;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class NoteManager implements NoteManagerInterface {
    @Override
    public void AddNote(Note note) {

    }
    @Override
    public void EditNote(Note note){

    }
    @Override
    public void DeleteNote(Note note){

    }
    @Override
    public List<Note> FindByDate(Date date){
        return null;
    }
    @Override
    public Note FindByID(Integer ID){
        return null;
    }

}
