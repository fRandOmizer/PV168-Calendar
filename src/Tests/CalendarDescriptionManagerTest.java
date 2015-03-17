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
        assertNull(calendarDescriptionManager.getDescription());        //DESCRIPTION HAS TO BE NULL ON BEGINNING

        calendarDescriptionManager.createDescription(null);             //THIS HAS TO CRASH WITH EXCEPTION
        assertNull(calendarDescriptionManager.getDescription());

        String text = "Description";
        calendarDescriptionManager.createDescription(text);
        assertNotNull(calendarDescriptionManager.getDescription());
        assertEquals(text, calendarDescriptionManager.getDescription());//DESCRIPTION IS SET
    }

    @Test
    public void testEditDescription() throws Exception {
        String text = "Description";
        calendarDescriptionManager.createDescription(text);

        String anotherText = "Another text";
        calendarDescriptionManager.editDescription(anotherText);
        assertEquals(anotherText, calendarDescriptionManager.getDescription());

        calendarDescriptionManager.editDescription(null);
        assertNull(calendarDescriptionManager.getDescription());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertNull(calendarDescriptionManager.getDescription());
        String text = "Description";
        calendarDescriptionManager.createDescription(text);
        assertNotNull(calendarDescriptionManager.getDescription());
        assertEquals(text, calendarDescriptionManager.getDescription());//DESCRIPTION IS SET
    }
}