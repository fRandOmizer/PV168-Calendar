package Interfaces;

import Containers.Day;

import java.util.Date;

/**
 * Created by Richard on 8. 3. 2015.
 */
public interface DayManagerInterface {
    public void createDay(Date date);
    public void editDay(Day day);
    public void deleteDay(Day day);
    public Day findByDay(Date day);
}
