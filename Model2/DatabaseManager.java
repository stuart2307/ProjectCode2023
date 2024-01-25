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
            // Establish the connection

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

    public ResultSet executeQuery(String query, Object... parameters) 
    {
        try 
        {
            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters, if any
            for (int i = 0; i < parameters.length; i++) 
            {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            // Execute the query and return the result set
            return preparedStatement.executeQuery();
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
            // Handle exceptions appropriately in a real application
            return null;
        }
    }

    public int executeUpdate(String query, Object... parameters) 
    {
        try 
        {
            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters, if any
            for (int i = 0; i < parameters.length; i++) 
            {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            // Execute the update query and return the number of affected rows
            return preparedStatement.executeUpdate();
        } 

        catch (SQLException failedConnection) 
        {
            failedConnection.printStackTrace();
            // Handle exceptions appropriately in a real application
            return -1;
        }
    }
}
