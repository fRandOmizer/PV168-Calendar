package Managers;

import Containers.Day;
import Containers.Note;
import Exceptions.ServiceFailureException;
import Interfaces.DayManagerInterface;
import java.sql.Date;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class DayManager implements DayManagerInterface {


    final static Logger logger = Logger.getLogger(NoteManager.class.getName());

    private final DataSource dataSource;

    public DayManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Mohlo by to rovno vracat Day
    @Override
    public void createDay(Date date){

        if(date == null) throw new IllegalArgumentException("Date is null");

        Day day = new Day();
        day.setDay( date,0,0);

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO day (\"date\",numberOfFinishedNotes,numberOfNotes) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                st.setDate(1, day.getIDDate());
                st.setInt(2, day.getNumberOfFinishedNotes());
                st.setInt(3, day.getNumberOfNotes());
                int addedRows = st.executeUpdate();
                if(addedRows != 1) {
                    throw new ServiceFailureException("Internal Error: More rows inserted when trying to insert note " + day);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public void editDay(Day day){
        if(day==null) throw new IllegalArgumentException("day pointer is null");
        if(day.getIDDate() ==null) throw new IllegalArgumentException("day with null id cannot be updated");
        if(day.getNumberOfFinishedNotes() <0 ) throw new IllegalArgumentException("day NumberOfFinishedNotes is less than 0");
        if(day.getNumberOfNotes() < 0) throw new IllegalArgumentException("day NumberOfNotes is less than 0");


        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement st = conn.prepareStatement("UPDATE day SET numberOfFinishedNotes=?,numberOfNotes=? WHERE \"date\"=?")) {
                st.setInt(1, day.getNumberOfFinishedNotes());
                st.setInt(2, day.getNumberOfNotes());
                st.setDate(3, day.getIDDate());
                if(st.executeUpdate()!=1) {
                    throw new IllegalArgumentException("cannot update note "+day);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public void deleteDay(Day day){
        if(day==null) throw new IllegalArgumentException("day pointer is null");
        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement st = conn.prepareStatement("DELETE FROM day WHERE \"date\"=?")) {
                st.setDate(1, day.getIDDate());
                if(st.executeUpdate()!=1) {
                    throw new ServiceFailureException("did not delete day with id ="+day.getIDDate());
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    //Premenovat na FindByDate
    @Override
    public Day findByDay(Date date){
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("SELECT numberOfFinishedNotes,numberOfNotes FROM day WHERE \"date\" = ?")) {
                st.setDate(1, date);
                ResultSet rs = st.executeQuery();
                Day result = new Day();
                result.setDay(date, 0, 0);
                if(rs.next())
                {
                    result.setNumberOfFinishedNotes(rs.getInt("numberOfNotes"));
                    result.setNumberOfFinishedNotes(rs.getInt("numberOfFinishedNotes"));
                }
                else
                {
                    result = null;
                }
                return result;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
}
