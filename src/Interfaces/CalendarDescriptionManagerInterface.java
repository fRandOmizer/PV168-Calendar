package Interfaces;

/**
 * Created by Richard on 8. 3. 2015.
 */

// NAVRH BY MAL OBSAHOVAT IBA SET DESCRIPTION,
// KTORE V PRIPADE AGRUMENT == NULL VYMAZE
// DESCRIPTION

public interface CalendarDescriptionManagerInterface {
    public void createDescription(String text);
    public void editDescription(String text);
    public String getDescription();
}
