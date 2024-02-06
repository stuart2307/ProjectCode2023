import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseManager {

    Connection connection = null;
    private final String DATABASE_URL = "jdbc:mysql://localhost/crocodeal";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    static final String ACCOUNTS[] = {"Username", "Password", "Name", "HouseNumber", "StreetName", "City", "County", "Eircode", "Email", "Phone"};
    static final int ACCOUNTSPOS[] = {0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
    static final String MESSAGES[] = {"SenderID", "RecieverID", "MessageContents"};
    static final int MESSAGESPOS[] = {1, 1, 0};
    static final String ADVERTISEMENTS[] = {"AccountID", "Make", "Model", "FuelType", "Year", "Mileage", "Price", "EngineSize", "PreviousOwners", "Description", "ImageLocation"};
    static final int ADVERTISEMENTSPOS[] = {1, 0, 0, 0, 1, 1, 2, 2, 1, 0, 0};
    static final String REVIEWS[] = {"ReviewerID", "RevieweeID", "ReviewContents", "StarRating"};
    static final int REVIEWSPOS[] = {1, 1, 0, 1};
    static final String CHATLOG[] = {"ChatID", "MessageID"};
    static final int CHATLOGPOS[] = {1, 1};
    static final String ACCOUNTCHATLOG[] = {"ChatID", "AccountID"};
    static final int ACCOUNTCHATLOGPOS[] = {1, 1};
    Scanner enterValue = new Scanner(System.in);

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

    public int createEntry(String table, String parameters[], int parameterspos[], String values[])
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

                // Create a prepared statement using the supplied parameters
                pstat = connection.prepareStatement("INSERT INTO " + table + " (" + parameters + ") VALUES (" + valueString + ")");

                for(int i=0; i<parameters.length;i++)
                {
                    if (parameterspos[i] == 0)
                        {
                            pstat.setString( (i+1) , values[i]);
                        }
                    else if (parameterspos[i] == 1)
                        {
                            pstat.setInt( (i+1) , Integer.parseInt(values[i]));
                        }
                    else if (parameterspos[i] == 2)
                        {
                            pstat.setDouble( (i+1) , Double.parseDouble(values[i]));
                        }
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

    public int executeUpdate(String table, String setParameter, String setValue, String locationParameter, String locationValue) 
    {
        try 
        {
            //Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + table + " SET " + setParameter + "=" + setValue + " WHERE " + locationParameter + "=" + locationValue);

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

public int deleteEntry() //working on this one
    {
        try 
        {
            String table = "";
            String column = "";
            String parameter = "";

            table = enterValue.nextLine();
            column = enterValue.nextLine();
            parameter = enterValue.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from ? where ?=?");
            preparedStatement.setString(1, table);
            preparedStatement.setString(2, column);
            preparedStatement.setString(3, parameter);

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
