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
                        if(parameters[index].equals("AdvertisementID"))
                        {

                        }
                        else if (index == 0)
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
                        if (parameters[index].equals("AdvertisementID"))
                            {

                            }
                        else if (index == 0)
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
            catch(SQLIntegrityConstraintViolationException SQLICVE)
            {
                return 1;
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
                if (order.toUpperCase().equals("ASC") || order.toUpperCase().equals("DESC"))
                    {
                        statementString += " ORDER BY " + orderField + " " + order;
                    }
                
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




    public static int executeUpdate(String table, String setParameter, String setValue, String locationParameter, String locationValue) 
        {
            try 
            {
                //Create a prepared statement
                PreparedStatement prepStatement = connection.prepareStatement("UPDATE " + table + " SET " + setParameter + "=? WHERE " + locationParameter + "=?");
    
                if (setParameter.equals("Image") || setParameter.equals("ProfilePic"))
                    {
                        try
                            {
                                File image = new File(setValue);
                                FileInputStream fis = new FileInputStream(image); 
                                prepStatement.setBinaryStream((1), fis, (int) image.length());
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


public static ResultSet innerJoinQuery(String tableOne, String tableTwo, String constraintOne, String constraintTwo, String parameter, String value)
        {
            try 
            {
                PreparedStatement pstat = connection.prepareStatement("SELECT * FROM " + tableOne + " INNER JOIN " + tableTwo + " ON " + tableOne + "." + constraintOne +" = " + tableTwo + "." + constraintTwo + " WHERE " + parameter + " = ?");     
                pstat.setString(1, value);
                return pstat.executeQuery();
            } 
            catch (SQLException e) 
            {
                return null;
            }

        }

public static int deleteEntry(String table, String column, String parameter)
    {

        // This method allows the deletion of entries in a table

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

    // 
    
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

public static ResultSet checkReviews(String reviewerID, String revieweeID)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT PositiveReview FROM reviews WHERE ReviewerID =? AND RevieweeID =?"); //execute query method didn't support the AND clause
        preparedStatement.setString(1, reviewerID);
        preparedStatement.setString(2, revieweeID);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs;
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        return null;
    }
}

public static void updateReviews(String reviewerID, String revieweeID, String reviewType)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE reviews SET PositiveReview =? WHERE ReviewerID =? AND RevieweeID =?");
        preparedStatement.setString(1, reviewType);
        preparedStatement.setString(2, reviewerID);
        preparedStatement.setString(3, revieweeID);
        preparedStatement.executeUpdate();
    }
    catch(SQLException sqle)
    {

    }    
}

public static void addReview(String reviewerID, String revieweeID, String reviewType)
{
    try
    {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reviews (ReviewerID, RevieweeID, PositiveReview) VALUES (?,?,?)");
        preparedStatement.setString(1, reviewerID);
        preparedStatement.setString(2, revieweeID);
        preparedStatement.setString(3, reviewType);
        preparedStatement.executeUpdate();
    }
    catch(SQLIntegrityConstraintViolationException SQLICVE) //Exception that takes place upon a duplicate entry due to keys
    {
        System.out.println("sqlicve triggered");    
        try
        {
            ResultSet rs = DatabaseManager.checkReviews(reviewerID, revieweeID);
            String currentReview = rs.getString("PositiveReview"); 
            if(!currentReview.equals(reviewType))
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