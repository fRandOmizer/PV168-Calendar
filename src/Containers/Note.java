package Containers;

import java.util.Date;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class Note {

    private int ID;
    private String Subject;
    private String Description;
    private Date date;
    private boolean IsDone;

    //region Get
    public int getID() {
        return ID;
    }

    public String getSubject() {
        return Subject;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return IsDone;
    }
    //endregion

    //region Set
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDone(boolean isDone) {
        IsDone = isDone;
    }

    public void setNote(String Subject, String Description, Date date) {
        this.Subject = Subject;
        this.Description = Description;
        this.date = date;
    }
    //endregion

    public Note() {
        this.ID = 0;
        IsDone = false;
        this.date = new Date();
        Description = null;
        Subject = null;
    }

    //region Equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (IsDone != note.IsDone) return false;
        if (!Description.equals(note.Description)) return false;
        if (!Subject.equals(note.Subject)) return false;
        if (!date.equals(note.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Subject.hashCode();
        result = 31 * result + Description.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (IsDone ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "ID=" + ID +
                ", Subject='" + Subject + '\'' +
                ", Description='" + Description + '\'' +
                ", date=" + date +
                ", IsDone=" + IsDone +
                '}';
    }
    //endregion
}