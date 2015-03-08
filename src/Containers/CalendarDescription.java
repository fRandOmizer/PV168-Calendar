package Containers;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class CalendarDescription {
    private String CalendarDescription;

    public String getCalendarDescription() {
        return CalendarDescription;
    }

    public void setCalendarDescription(String calendarDescription) {
        CalendarDescription = calendarDescription;
    }

    public CalendarDescription() {
        CalendarDescription = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendarDescription)) return false;

        CalendarDescription that = (CalendarDescription) o;

        if (!CalendarDescription.equals(that.CalendarDescription)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return CalendarDescription.hashCode();
    }

    @Override
    public String toString() {
        return "CalendarDescription{" +
                "CalendarDescription='" + CalendarDescription + '\'' +
                '}';
    }
}