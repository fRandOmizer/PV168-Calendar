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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return IsDone;
    }

    public void setDone(boolean isDone) {
        IsDone = isDone;
    }

    public Note() {
        this.ID = 0;
        IsDone = false;
        this.date = new Date();
        Description = null;
        Subject = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (ID != note.ID) return false;
        if (IsDone != note.IsDone) return false;
        if (!Description.equals(note.Description)) return false;
        if (!Subject.equals(note.Subject)) return false;
        if (!date.equals(note.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + Subject.hashCode();
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
}