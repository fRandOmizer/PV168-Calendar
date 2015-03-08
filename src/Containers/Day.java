package Containers;

/**
 * Created by Richard on 8. 3. 2015.
 */

import java.util.Date;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class Day {
    private Date IDDate;
    private int NumberOfNotes;
    private int NumberOfFinishedNotes;

    public int getNumberOfFinishedNotes() {
        return NumberOfFinishedNotes;
    }

    public void setNumberOfFinishedNotes(int numberOfFinishedNotes) {
        NumberOfFinishedNotes = numberOfFinishedNotes;
    }

    public int getNumberOfNotes() {
        return NumberOfNotes;
    }

    public void setNumberOfNotes(int numberOfNotes) {
        NumberOfNotes = numberOfNotes;
    }

    public Date getIDDate() {
        return IDDate;
    }

    public void setIDDate(Date IDDate) {
        this.IDDate = IDDate;
    }

    public Day() {
        IDDate = new Date();
        NumberOfNotes = 0;
        NumberOfFinishedNotes = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;

        Day day = (Day) o;

        if (NumberOfFinishedNotes != day.NumberOfFinishedNotes) return false;
        if (NumberOfNotes != day.NumberOfNotes) return false;
        if (!IDDate.equals(day.IDDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = IDDate.hashCode();
        result = 31 * result + NumberOfNotes;
        result = 31 * result + NumberOfFinishedNotes;
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "IDDate=" + IDDate +
                ", NumberOfNotes=" + NumberOfNotes +
                ", NumberOfFinishedNotes=" + NumberOfFinishedNotes +
                '}';
    }
}
