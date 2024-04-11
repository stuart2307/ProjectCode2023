import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    static Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost/crocodeal";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    static final String ACCOUNTS[] = {"AccountID, Username", "Password", "Name", "HouseNumber", "StreetName", "City", "County", "Eircode", "Email", "Phone", "ProfilePic"};
    static final String MESSAGES[] = {"SenderID", "RecieverID", "MessageContents"};
    static final String ADVERTISEMENTS[] = {"AccountID", "Make", "Model", "FuelType", "GearBox", "Year", "Mileage", "Price", "EngineSize", "PreviousOwners", "Description", "Image"};
    static final String REVIEWS[] = {"ReviewerID", "RevieweeID", "ReviewContents", "StarRating"};
    static final String CHATLOG[] = {"ChatID", "MessageID"};
    static final String ACCOUNTCHATLOG[] = {"ChatID", "AccountID"};

    public static void establishConnection()
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

    public static void close() 
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




    public static int createEntry(String table, String parameters[], String values[])
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
                    if (parameters[i].equals("Image") || parameters[i].equals("ProfilePic"))
                        {
                            try
                                {
                                    File image = new File(values[i]);
                                    FileInputStream fis = new FileInputStream(image); 
                                    pstat.setBinaryStream((i+1), fis, (int) image.length());
                                }
                            catch (FileNotFoundException fnfe)
                                {
                                    fnfe.printStackTrace();
                                    System.out.println("oops");
                                }
                            
                        }
                    else
                        {
                            pstat.setString( (i+1) , values[i]);
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




    public static ResultSet executeQuery(String parameters[], String table, String column, String value, String order, String orderField) 
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
                String statementString = "SELECT " + parametersString + " FROM " + table;
                if (!column.equals("") && !value.equals(""))
                    {
                        statementString  += " WHERE " + column + "=?";
                    }
                if (order.toUpperCase().equals("ASC") || order.toUpperCase().equals("DESC"))
                    {
                        statementString += " ORDER BY " + orderField + " " + order;
                    }
                PreparedStatement preparedStatement = connection.prepareStatement(statementString);
                if (!column.equals("") && !value.equals(""))
                    {
                        preparedStatement.setString(1, value);
                    }
                
    
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




    public static int executeUpdate(String table, String setParameter, String setValue, String locationParameter, String locationValue) 
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




public static int deleteEntry(String table, String column, String parameter) //working on this one
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




public static void clearArray(String array[])
    {
        int index;
        for (index = 0; index < array.length; index++)
            {
                array[index] = null;
            }
    }

public static boolean countRows(String table, String column, String parameter)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + table + " WHERE BINARY " + column +"=?"); //WHERE BINARY converts the where clause to a binary string to make the input case sensitive
        preparedStatement.setString(1, parameter); 
        ResultSet rs = preparedStatement.executeQuery(); //Taking in the results to a ResultSet variable
        rs.next(); //Moving the pointer of the resultset to the next row which is the first one
        int rowCount = rs.getInt(1); //Getting the result of the first column
        if(rowCount >= 1) //If there exists a column then the input for the where clause exists eg. a username already exists
        {
            return true;
        }
        else 
        {
            return false;
        }
        
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return false;
    }
}

public static boolean checkPassword(String username, String password)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM accounts WHERE BINARY Username =?" + " AND Password =?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int rowCount = rs.getInt(1);
        if(rowCount >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return false;
    }
    
}

}