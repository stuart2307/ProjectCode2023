import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    Connection connection = null;
    private final String DATABASE_URL = "jdbc:mysql://localhost/crocodeal";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    static final String ACCOUNTS[] = {"Username", "Password", "Name", "HouseNumber", "StreetName", "City", "County", "Eircode", "Email", "Phone"};
    static final String MESSAGES[] = {"SenderID", "RecieverID", "MessageContents"};
    static final String ADVERTISEMENTS[] = {"AccountID", "Make", "Model", "FuelType", "Year", "Mileage", "Price", "EngineSize", "PreviousOwners", "Description", "ImageLocation"};
    static final String REVIEWS[] = {"ReviewerID", "RevieweeID", "ReviewContents", "StarRating"};
    static final String CHATLOG[] = {"ChatID", "MessageID"};
    static final String ACCOUNTCHATLOG[] = {"ChatID", "AccountID"};




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

            // Closes Connection 

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




    public int createEntry(String table, String parameters[], String values[])
        {
            try
            {
                int index;
                String valueString = "";

                // Creates a New Entry for a Table
                PreparedStatement pstat = null;

                // Makes a string for the values placeholders
                for (index = 0; index < parameters.length; index++)
                    {
                        if (index == 0)
                            {
                                valueString = "?";
                            }
                        else
                            {
                                valueString += ", ?";
                            }
                    }
                String parametersString ="";
                for (index = 0; index < parameters.length; index++)
                    {
                        if (index == 0)
                            {
                                parametersString = parameters[index];
                            }
                        else
                            {
                                parametersString += ", " + parameters[index];
                            }
                    }
                    
                // Create a prepared statement using the supplied parameters
                pstat = connection.prepareStatement("INSERT INTO " + table + " (" + parametersString + ") VALUES (" + valueString + ")");

                for(int i=0; i<parameters.length;i++)
                {
                    pstat.setString( (i+1) , values[i]);
                }

                // Execute update and return the number of rows affected
                return pstat.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                // Handle exceptions appropriately
                return -1;
            }
        }




    public ResultSet executeQuery(String parameters[], String table, String column, String value) 
        {
            try 
            {
                int index = 0;
                String parametersString = "";
                while (index < parameters.length && parameters[index] != null)
                    {
                        if (index == 0)
                            {
                                parametersString = parameters[index];
                            }
                        else
                            {
                                parametersString += ", " + parameters[index];
                            }
                        index++;
                    }
                //Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + parametersString + " FROM " + table + " WHERE " + column + "=?");
                preparedStatement.setString(1, value);
    
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




    public int executeUpdate(String table, String setParameter, String setValue, String locationParameter, String locationValue) 
        {
            try 
            {
                //Create a prepared statement
                PreparedStatement prepStatement = connection.prepareStatement("UPDATE " + table + " SET " + setParameter + "=? WHERE " + locationParameter + "=?");
    
                prepStatement.setString (1, setValue);
                prepStatement.setString (2, locationValue );
    
                //Execute the update and return the number of affected rows
                return prepStatement.executeUpdate();
            } 
    
            catch (SQLException failedConnection) 
            {
                failedConnection.printStackTrace();
                //Handle exceptions appropriately
                return -1;
            }
        }




public int deleteEntry(String table, String column, String parameter) //working on this one
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + column + "=?");
            preparedStatement.setString(1, parameter);

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




public void clearArray(String array[])
    {
        int index;
        for (index = 0; index < array.length; index++)
            {
                array[index] = null;
            }
    }
}
