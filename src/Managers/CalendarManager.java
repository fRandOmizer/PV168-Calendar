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

// METODA CHANGEMONTH BY MALA STALE VRACAT POLE ALE PRAZDNE

public class CalendarManager implements CalendarManagerInterface {
    @Override
    public List<Day> ChangeMonth(Year year, Month month) {
        return null;
    }
    @Override
    public List<Note> getNotesForDay(Day day) {
        return null;
    }
    @Override
    public void AddNoteToDay(Day day, Note note){

    }
    @Override
    public void EditNoteToDay(Day day, Note note){

    }
    @Override
    public void DeleteNoteToDay(Day day, Note note){

    }
}
