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
            dayManager.CreateDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.CreateDay(new Date());
        Day day = dayManager.FindByDay(new Date());
        assertNotNull(day);
        dayManager.DeleteDay(day);
        day = dayManager.FindByDay(new Date());
        assertNull(day);
    }

    @Test
    public void testEditDay() throws Exception {
        //?? co ma tato funkcia robit??
    }

    @Test
    public void testDeleteDay() throws Exception {
        try {
            dayManager.DeleteDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.CreateDay(new Date());
        Day day = dayManager.FindByDay(new Date());
        dayManager.DeleteDay(day);
        day = dayManager.FindByDay(new Date());
        assertNull(day);
    }

    @Test
    public void testFindByDay() throws Exception {
        try {
            dayManager.FindByDay(null);
        }
        catch (Exception ex) {
            //OK
        }
        dayManager.CreateDay(new Date());
        Day day = dayManager.FindByDay(new Date());
        assertEquals(day.getIDDate(),new Date());
    }
}