package Managers;

import Containers.Day;
import Containers.Note;
import Interfaces.CalendarManagerInterface;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Richard on 8. 3. 2015.
 */

// METODA CHANGEMONTH BY MALA STALE VRACAT POLE ALE PRAZDNE

public class CalendarManager implements CalendarManagerInterface {

    private  NoteManager noteManager;
    private DayManager dayManager;
    private CalendarDescriptionManager descriptionManager;

    public CalendarManager(DataSource dataSourceDay, DataSource dataSourceDescription, DataSource dataSourceNote) {
        noteManager = new NoteManager(dataSourceNote);
        dayManager = new DayManager(dataSourceDay);
        descriptionManager = new CalendarDescriptionManager(dataSourceDescription);
    }

    @Override
    public List<Day> changeMonth(Year year, Month month) {
        int iYear = year.getValue();
        int iMonth = month.getValue();
        int iDay = 1;

        Calendar cal = new GregorianCalendar(iYear, iMonth, iDay);

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<Day> result = new ArrayList<>();
        for (int i =0;i<daysInMonth;i++)
        {
            cal = Calendar.getInstance();
            cal.set( cal.YEAR, iYear );
            cal.set( cal.MONTH, iMonth );
            cal.set( cal.DATE, i+1 );
            Date tempDate = new Date(cal.getTime().getTime());
            if(dayManager.findByDay(tempDate)!=null)
            {
                result.add(dayManager.findByDay(tempDate));
            }
        }

        return result;
    }
    @Override
    public List<Note> getNotesForDay(Day day) {
        return noteManager.findByDate(day.getIDDate());
    }
    @Override
    public void addNoteToDay(Day day, Note note){

        if(day== null || note == null)
        {
            return;
        }

        if(dayManager.findByDay(day.getIDDate())==null)
        {
            dayManager.createDay(day.getIDDate());
        }

        if(note.isDone())
        {
            day.setNumberOfFinishedNotes(day.getNumberOfFinishedNotes()+1);
            day.setNumberOfNotes(day.getNumberOfNotes() + 1);
        }
        else
        {
            day.setNumberOfNotes(day.getNumberOfNotes()+1);
        }
        dayManager.editDay(day);
        note.setDate(day.getIDDate());
        noteManager.addNote(note);
    }
    @Override
    public void editNoteToDay(Day day, Note note){
        if(note.isDone())
        {
            day.setNumberOfFinishedNotes(day.getNumberOfFinishedNotes() + 1);
        }
        dayManager.editDay(day);
        noteManager.editNote(note);
    }
    @Override
    public void deleteNoteToDay(Day day, Note note){
        noteManager.deleteNote(note);

        day.setNumberOfFinishedNotes(day.getNumberOfFinishedNotes() - 1);
        day.setNumberOfNotes(day.getNumberOfNotes() - 1);
        dayManager.editDay(day);
    }
}
