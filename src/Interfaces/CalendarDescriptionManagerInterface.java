package Interfaces;

/**
 * Created by Richard on 8. 3. 2015.
 */

// NAVRH BY MAL OBSAHOVAT IBA SET DESCRIPTION,
// KTORE V PRIPADE AGRUMENT == NULL VYMAZE
// DESCRIPTION

public interface CalendarDescriptionManagerInterface {
    public void CreateDescription(String text);
    public void EditDescription(String text);
    public String GetDescription();
}
