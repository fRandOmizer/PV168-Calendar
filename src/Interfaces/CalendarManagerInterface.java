package Interfaces;

import Containers.Day;
import Containers.Note;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */
public interface CalendarManagerInterface {
    public List<Day> ChangeMonth(Year year,Month month);
    public List<Note> getNotesForDay(Day day);
    public void AddNoteToDay(Day day, Note note);
    public void EditNoteToDay(Day day, Note note);
    public void DeleteNoteToDay(Day day, Note note);
}
