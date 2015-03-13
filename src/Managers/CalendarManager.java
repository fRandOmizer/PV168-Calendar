package Managers;

import Containers.Day;
import Containers.Note;
import Interfaces.CalendarManagerInterface;

import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class CalendarManager implements CalendarManagerInterface {
    @Override
    public List<Day> changeMonth(Year year, Month month) {
        return null;
    }
    @Override
    public void addNoteToDay(Day day, Note note){

    }
    @Override
    public void editNoteToDay(Day day, Note note){

    }
    @Override
    public void deleteNoteToDay(Day day, Note note){

    }
}
