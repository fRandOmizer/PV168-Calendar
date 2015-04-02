package Interfaces;

import Containers.Day;

import java.sql.Date;


/**
 * Created by Richard on 8. 3. 2015.
 */
public interface DayManagerInterface {
    //Mohlo by to rovno vracat Day
    public void createDay(Date date);
    public void editDay(Day day);
    public void deleteDay(Day day);
    public Day findByDay(Date date);
}
