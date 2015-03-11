package Tests;

import Managers.CalendarDescriptionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarDescriptionManagerTest {

    private CalendarDescriptionManager calendarDescriptionManager;

    @Before
    public void setUp() throws Exception {
        calendarDescriptionManager = new CalendarDescriptionManager();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateDescription() throws Exception {
        assertNull(calendarDescriptionManager.GetDescription());        //DESCRIPTION HAS TO BE NULL ON BEGINNING

        calendarDescriptionManager.CreateDescription(null);             //THIS HAS TO CRASH WITH EXCEPTION
        assertNull(calendarDescriptionManager.GetDescription());

        String text = "Description";
        calendarDescriptionManager.CreateDescription(text);
        assertNotNull(calendarDescriptionManager.GetDescription());
        assertEquals(text, calendarDescriptionManager.GetDescription());//DESCRIPTION IS SET
    }

    @Test
    public void testEditDescription() throws Exception {
        String text = "Description";
        calendarDescriptionManager.CreateDescription(text);

        String anotherText = "Another text";
        calendarDescriptionManager.EditDescription(anotherText);
        assertEquals(anotherText, calendarDescriptionManager.GetDescription());

        calendarDescriptionManager.EditDescription(null);
        assertNull(calendarDescriptionManager.GetDescription());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertNull(calendarDescriptionManager.GetDescription());
        String text = "Description";
        calendarDescriptionManager.CreateDescription(text);
        assertNotNull(calendarDescriptionManager.GetDescription());
        assertEquals(text, calendarDescriptionManager.GetDescription());//DESCRIPTION IS SET
    }
}