import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    Connection connection = null;
    private static final String DATABASE_URL = "jdbc://localhost/crocodeal";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public DatabaseManager() 
    {
        try 
        {
            //Establish the connection

            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } 
        
        catch (SQLException failedConnection) 
        {
            System.out.println("CRITICAL ERROR. ABORTING PROCESS.");
            failedConnection.printStackTrace();
        }
    }

    public void close() 
    {
        try 
        {
            if (connection != null && !connection.isClosed()) 
            {
                connection.close();
            }
        } 
        
        catch (SQLException failedConnection) 
        {
            failedConnection.printStackTrace();
        }
    }

    public int createEntry(String table, String parameters)
        {
            try
            {
                //Create a prepared statement using the supplied parameters
                PreparedStatement preparedStatement = connection.prepareStatement("insert into " + table + " values (" + parameters + ");");
                //return the number of rows affected
                return preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                //Handle exceptions appropriately
                return -1;
            }
        }

    public ResultSet executeQuery(String parameters, String table) 
    {
        try 
        {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement("select ");

            //Execute the query and return the result set
            return preparedStatement.executeQuery();
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
            //Handle exceptions appropriately
            return null;
        }
    }

    public int executeUpdate(String table, String parameters) 
    {
        try 
        {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement("update " + table + " " + parameters);

            //Execute the update and return the number of affected rows
            return preparedStatement.executeUpdate();
        } 

        catch (SQLException failedConnection) 
        {
            failedConnection.printStackTrace();
            //Handle exceptions appropriately
            return -1;
        }
    }

public int deleteEntry(String table, String parameters)
    {
        try
        {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement("delete from " + table + " where " + parameters);

            //Execute the deletion, and return the number of affected rows
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //Handle exceptions appropriately
            return -1;
        }
    }
}