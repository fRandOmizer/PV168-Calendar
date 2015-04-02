package Containers;

/**
 * Created by Richard on 8. 3. 2015.
 */


import java.sql.Date;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class Day {
    private Date IDDate;
    private int NumberOfNotes;
    private int NumberOfFinishedNotes;

    //region getter
    public int getNumberOfFinishedNotes() {
        return NumberOfFinishedNotes;
    }

    public int getNumberOfNotes() {
        return NumberOfNotes;
    }

    public Date getIDDate() {
        return IDDate;
    }
    //endregion

    //region setters
    public void setDay(Date IDDate, int NumberOfNotes, int NumberOfFinishedNotes)
    {
        this.IDDate= IDDate;
        this.NumberOfNotes = NumberOfNotes;
        this.NumberOfFinishedNotes = NumberOfFinishedNotes;
    }

    public void setNumberOfFinishedNotes(int numberOfFinishedNotes) {
        NumberOfFinishedNotes = numberOfFinishedNotes;
    }

    public void setNumberOfNotes(int numberOfNotes) {
        NumberOfNotes = numberOfNotes;
    }
    //endregion

    public Day() {
        java.util.Date date = new java.util.Date();
        IDDate = new java.sql.Date(date.getTime());
        NumberOfNotes = 0;
        NumberOfFinishedNotes = 0;
    }

    //region Equals, hashCode, toString
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
    //endregion
}
