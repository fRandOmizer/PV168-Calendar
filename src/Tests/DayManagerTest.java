package Tests;

import Containers.Day;
import Managers.DayManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DayManagerTest {
    private DayManager dayManager;

    @Before
    public void setUp() throws Exception {
        dayManager = new DayManager();
    }

    @Test
    public void testCreateDay() throws Exception {
        try {
            dayManager.createDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date());
        Day day = dayManager.findByDay(new Date());
        assertNotNull(day);
        dayManager.deleteDay(day);
        day = dayManager.findByDay(new Date());
        assertNull(day);
    }

    @Test
    public void testEditDay() throws Exception {
        //?? co ma tato funkcia robit??
    }

    @Test
    public void testDeleteDay() throws Exception {
        try {
            dayManager.deleteDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date());
        Day day = dayManager.findByDay(new Date());
        dayManager.deleteDay(day);
        day = dayManager.findByDay(new Date());
        assertNull(day);
    }

    @Test
    public void testFindByDay() throws Exception {
        try {
            dayManager.findByDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.createDay(new Date());
        Day day = dayManager.findByDay(new Date());
        assertEquals(day.getIDDate(),new Date());
    }
}