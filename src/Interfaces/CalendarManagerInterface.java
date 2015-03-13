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
    public List<Day> changeMonth(Year year, Month month);
    public void addNoteToDay(Day day, Note note);
    public void editNoteToDay(Day day, Note note);
    public void deleteNoteToDay(Day day, Note note);
}
