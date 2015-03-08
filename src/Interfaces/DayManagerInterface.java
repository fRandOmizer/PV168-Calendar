package Interfaces;

import Containers.Day;

import java.util.Date;

/**
 * Created by Richard on 8. 3. 2015.
 */
public interface DayManagerInterface {
    public void CreateDay(Date date);
    public void EditDay(Day day);
    public void DeleteDay(Day day);
    public Day FindByDay(Date day);
}
