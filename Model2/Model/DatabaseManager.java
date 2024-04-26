import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

//
// Class Name : DatabaseManager
//
// Author     : Stuart Rossiter, Diarmuid O'Neill, Isaiah Andres
//
// Purpose    : This class handles database connections and provides methods for CRUD operations
//

public class DatabaseManager {

    static Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost/crocodeal";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Paramater arrays for database tables

    static final String ACCOUNTS[] = {"Username", "Password", "Name", "HouseNumber", "StreetName", "City", "County", "Eircode", "Email", "Phone", "ProfilePic"};
    static final String MESSAGES[] = {"SenderID", "RecieverID", "MessageContents"};
    static final String ADVERTISEMENTS[] = {"AccountID", "Make", "Model", "FuelType", "GearBox", "Year", "Mileage", "Price", "EngineSize", "PreviousOwners", "Description", "Image"};
    static final String REVIEWS[] = {"ReviewerID", "RevieweeID", "PositiveReview"};
    static final String CHATLOG[] = {"ChatID", "MessageID"};
    static final String ACCOUNTCHATLOG[] = {"ChatID", "AccountID"};

    private DatabaseManager(){}

    public static void establishConnection()
    {

        // This method establishes a connection to the database

        try 
        {
            //Establish the connection

            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            PreparedStatement pstat = connection.prepareStatement("SET GLOBAL max_allowed_packet=16777216");
            pstat.executeUpdate();
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

    public static void createEntry(String table, String parameters[], String values[])
        {

            // This method allows the creation of an entry in the database

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

                // Makes a string for parameters

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
                                    // Insert image into the database
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
                pstat.executeUpdate();
            }
            catch(SQLIntegrityConstraintViolationException SQLICVE)
            {
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                // Handle exceptions appropriately
            }
        }

    public static ResultSet executeQuery(String parameters[], String table, String column, String value, String order, String orderField) 
        {
            try 
            {
                int index = 0;
                String parametersString = "";
                // Creates a string for the parameters to select
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
                        // If column is set to LIKE, selects from table using the LIKE keyword
                        if(!column.toUpperCase().equals("LIKE"))
                        statementString  += " WHERE " + column + "=?";
                        else
                            {
                                for (int i = 0; i < parameters.length; i++)
                                    {
                                        if (i==0)
                                        statementString += " WHERE " + parameters[i] + " LIKE ?";
                                        else
                                        statementString += " OR " + parameters[i] + " LIKE ?";
                                    }
                            }
                    }
                // Orders results, if order string is ASC or DESC
                if (order.toUpperCase().equals("ASC") || order.toUpperCase().equals("DESC"))
                    {
                        statementString += " ORDER BY " + orderField + " " + order;
                    }
                // Fills the statement with the necessary parameters
                PreparedStatement preparedStatement = connection.prepareStatement(statementString);
                if (!column.equals("") && !value.equals(""))
                    {
                        if (!column.toUpperCase().equals("LIKE"))
                        preparedStatement.setString(1, value);
                        else
                            {
                                for(int i = 0; i < parameters.length; i++)
                                    {
                                        preparedStatement.setString(i + 1, value);
                                    }
                            }
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




    public static void executeUpdate(String table, String setParameter, String setValue, String locationParameter, String locationValue) 
        {
            try 
            {
                //Create a prepared statement
                PreparedStatement prepStatement = connection.prepareStatement("UPDATE " + table + " SET " + setParameter + "=? WHERE " + locationParameter + "=?");
    
                //If image is to be updated
                if (setParameter.equals("Image") || setParameter.equals("ProfilePic"))
                    {
                        try
                            {
                                File image = new File(setValue); // Creates new file
                                FileInputStream fis = new FileInputStream(image);  // Creates new FileInputStream from image file
                                prepStatement.setBinaryStream((1), fis, (int) image.length()); //Sets binary stream to image FileInputStream
                            }
                        catch (FileNotFoundException fnfe)
                            {
                                fnfe.printStackTrace();
                                System.out.println("oops");
                            }
                    }
                else
                    {
                        prepStatement.setString(1, setValue);
                    }
                prepStatement.setString (2, locationValue);
                //Execute the update
                prepStatement.executeUpdate();
            } 
    
            catch (SQLException failedConnection) 
            {
                failedConnection.printStackTrace();
                //Handle exceptions appropriately
            }
        }


public static ResultSet innerJoinQuery(String tableOne, String tableTwo, String constraintOne, String constraintTwo, String parameter, String value)
        {
            try 
            {
                // Prepares a statement using the provided parameters, using an inner join
                PreparedStatement pstat = connection.prepareStatement("SELECT * FROM " + tableOne + " INNER JOIN " + tableTwo + " ON " + tableOne + "." + constraintOne +" = " + tableTwo + "." + constraintTwo + " WHERE " + parameter + " = ?");  
                // Sets the value parameter   
                pstat.setString(1, value);
                // Executes query and returns ResultSet
                return pstat.executeQuery();
            } 
            catch (SQLException e) 
            {
                return null;
            }

        }

public static void deleteEntry(String table, String column, String parameter)
    {

        // This method allows the deletion of entries in a table

        try 
        {
            // Creates a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + column + "=?");
            // Sets the parameter for deleting an entry
            preparedStatement.setString(1, parameter);
            // Executes the update
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

public static boolean countRows(String table, String column, String parameter)
{

    // 

    try
    {

        //WHERE BINARY converts the where clause to a binary string to make the input case sensitive

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + table + " WHERE BINARY " + column +"=?"); 
        preparedStatement.setString(1, parameter); 
        ResultSet rs = preparedStatement.executeQuery();                //Taking in the results to a ResultSet variable
        rs.next();                                                      //Moving the pointer of the resultset to the next row which is the first one
        int rowCount = rs.getInt(1);                        //Getting the result of the first column
        if(rowCount >= 1)                                               //If there exists a column then the input for the where clause exists eg. a username already exists
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

    // Checks if a username and password match an account in the database
    
    try
    {
        // Prepares a statement for checking if the username and password exist
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM accounts WHERE BINARY Username =?" + " AND Password =?");
        // Sets username and password values
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        // Execute Query
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int rowCount = rs.getInt(1);
        if(rowCount >= 1)
        {
            return true; // If account exists return true
        }
        else
        {
            return false; // If account does not exist return false
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return false;
    }
    
}

public static ResultSet checkReviews(int reviewerID, int revieweeID)
{
    try
    {
        // Prepares statement for checking if review exists
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT PositiveReview FROM reviews WHERE ReviewerID =? AND RevieweeID =?"); //execute query method didn't support the AND clause
        // Sets the ID values
        preparedStatement.setInt(1, reviewerID);
        preparedStatement.setInt(2, revieweeID);
        // Executes Query
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        // Returns ResultSet
        return rs;
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return null;
    }
}

public static void updateReviews(int reviewerID, int revieweeID, int reviewType)
{
    try
    {
        // Prepares statement for updating reviews
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE reviews SET PositiveReview =? WHERE ReviewerID =? AND RevieweeID =?");
        // Sets parameter values
        preparedStatement.setInt(1, reviewType);
        preparedStatement.setInt(2, reviewerID);
        preparedStatement.setInt(3, revieweeID);
        // Executes Update
        preparedStatement.executeUpdate();
    }
    catch(SQLException sqle)
    {

    }    
}

public static void addReview(int reviewerID, int revieweeID, int reviewType)
{
    PreparedStatement preparedStatement = null; 
    try
    {
        // Prepares a statement to add a review
        preparedStatement = connection.prepareStatement("INSERT INTO reviews (ReviewerID, RevieweeID, PositiveReview) VALUES (?,?,?)");
        // Sets parameter values
        preparedStatement.setInt(1, reviewerID);
        preparedStatement.setInt(2, revieweeID);
        preparedStatement.setInt(3, reviewType);
        // Executes Update
        preparedStatement.executeUpdate();
    }
    catch(SQLIntegrityConstraintViolationException SQLICVE) //Exception that takes place upon a duplicate entry due to keys
    {
        System.out.println("sqlicve triggered");    
        try
        {
            if (preparedStatement != null)
                preparedStatement.cancel();
            // Fetches the review if one already exists from the reviewer to the reviewee
            ResultSet rs = DatabaseManager.checkReviews(reviewerID, revieweeID);
            int currentReview = rs.getInt("PositiveReview"); 
            // if the existing review is of the opposite type, update it to match the new choice
            if(currentReview != reviewType)
            {
                DatabaseManager.updateReviews(reviewerID, revieweeID, reviewType); //handles case by which the user inputs the same review
            }
        }
        catch(SQLException sqle)
        {

        }
    }
    catch(SQLException sqle)
    {
        System.out.println("sqle triggered");
    }   
          
}

public static int countPositiveReviews(String revieweeID)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM reviews WHERE RevieweeID =? AND PositiveReview = '1'"); //
        preparedStatement.setString(1, revieweeID); 
        ResultSet rs = preparedStatement.executeQuery(); //Taking in the results to a ResultSet variable
        rs.next(); //Moving the pointer of the resultset to the next row which is the first one
        int rowCount = rs.getInt(1); //Getting the result of the first column
        return rowCount;
    }
    catch(SQLException sqle)
    {
        return -1;
    }    
}

public static int countAllReviews(String revieweeID)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM reviews WHERE RevieweeID =?"); //
        preparedStatement.setString(1, revieweeID);  
        ResultSet rs = preparedStatement.executeQuery(); //Taking in the results to a ResultSet variable
        rs.next(); //Moving the pointer of the resultset to the next row which is the first one
        int rowCount = rs.getInt(1); //Getting the result of the first column
        return rowCount;
    }
    catch(SQLException sqle)
    {
        return -1;
    }    
}

}