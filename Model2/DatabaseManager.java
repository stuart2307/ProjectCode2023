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
    static final String MESSAGES[] = {"SenderID", "RecieverID", "MessageContents"};
    static final String ADVERTISEMENTS[] = {"AccountID", "Make", "Model", "FuelType", "Year", "Mileage", "Price", "EngineSize", "PreviousOwners", "Description", "ImageLocation"};
    static final String REVIEWS[] = {"ReviewerID", "RevieweeID", "ReviewContents", "StarRating"};
    static final String CHATLOG[] = {"ChatID", "MessageID"};
    static final String ACCOUNTCHATLOG[] = {"ChatID", "AccountID"};
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

    public int createEntry(String table, String parameters[], String values[])
        {
            try
            {

                // Creates a New Entry for a Table

                // Create a prepared statement using the supplied parameters
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + table + " (" + parameters + ") VALUES (" + values + ")");
                // Return the number of rows affected
                return preparedStatement.executeUpdate();
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
            String parameter;

            System.out.println("Choose a table to delete from: \n1. accountchatlog \n2. accounts \n3. advertisements \n4. chatlog \n5. messages \n6. reviews"); //Using a menu system for simplicity when using this method
            int choice = enterValue.nextInt();
            if(choice == 1) //Might change to switch statement later on
            {
                table = "accountchatlog";
            }
            else if(choice == 2)
            {
                table = "accounts";
            }
            else if(choice == 3)
            {
                table = "advertisements";
            }
            else if(choice == 4)
            {
                table = "chatlog";
            }
            else if(choice == 5)
            {
                table = "messages";
            }
            else if(choice == 6)
            {
                table = "reviews";
            }

            System.out.println("Choose which row to delete from by entering the column name and the corresponding details of the row to be deleted. \neg. AccountID='1'");
            parameter = enterValue.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from " + table + " where " + parameter);

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
