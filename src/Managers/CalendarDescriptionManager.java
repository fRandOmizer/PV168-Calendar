package Managers;

import Containers.CalendarDescription;
import Containers.Day;
import Exceptions.ServiceFailureException;
import Interfaces.CalendarDescriptionManagerInterface;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Richard on 8. 3. 2015.
 */
public class CalendarDescriptionManager implements CalendarDescriptionManagerInterface {
    final static Logger logger = Logger.getLogger(NoteManager.class.getName());

    private final DataSource dataSource;

    public CalendarDescriptionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void createDescription(String text){
        if(text == null) throw new IllegalArgumentException("text is null");

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO  calendardescription (\"date\",description) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                Date date = new Date();
                st.setDate(1,new java.sql.Date(date.getTime()));
                st.setString(2, text);
                int addedRows = st.executeUpdate();
                if(addedRows != 1) {
                    throw new ServiceFailureException("Internal Error: More rows inserted when trying to insert note " + text);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public void editDescription(String text){

        if(text==null)
        {
            text="";
        }

        try (Connection conn = dataSource.getConnection()) {
            try(PreparedStatement st = conn.prepareStatement("UPDATE calendardescription SET \"date\"=?,description=?", Statement.RETURN_GENERATED_KEYS)) {
                Date date = new Date();
                st.setDate(1, new java.sql.Date(date.getTime()));
                st.setString(2, text);
                if(st.executeUpdate()!=1) {
                    throw new IllegalArgumentException("cannot update descrition "+text);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
    @Override
    public String getDescription()
    {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement("SELECT description FROM calendardescription ORDER BY \"date\" ASC LIMIT 1")) {
                ResultSet rs = st.executeQuery();
                String result = null;

                if(rs.next())
                {
                    result=rs.getString("description");
                    if(result.equals(""))
                    {
                        result = null;
                    }
                }

                return result;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "db connection problem", ex);
            throw new ServiceFailureException("Error when retrieving all notes", ex);
        }
    }
}
