import java.util.InputMismatchException;

//
// Class Name : Verifiers
//
// Author     : Isaiah Andres, Diarmuid O'Neill
//
// Purpose    : This class contains all the verifier methods for exception handling.
//              these methods are used to validate user entered data.
//

public class Verifiers 
{
    public static void VerifyEntries(String[] Entries) throws BlankEntryException 
    {
 
        // This method throws a BlankEntryException if not all fields have been filled out in a particular form
      
        for(int i = 0; i < Entries.length; i++)
        {
            if(Entries[i] == null || Entries[i].equals("") ) throw new BlankEntryException("All entries must be filled out");
            else
            {
                System.out.println("All entries are filled out, adding to database..."); //Putting this in place until we can switch pages
            }
        }
       
    }

    public static void VerifyPhoneNumber(String numberInput) throws phoneException
    {

        // This method throws a phoneException if the entered phone number does not match a particular pattern

        if(!numberInput.matches("[0-9 ]+")) throw new phoneException("Only numbers may be entered into the phone number textfield");
        else
        {
            System.out.println("Phone Number Accepted");
        }
    }

    public static void VerifyEmailAddress(String emailInput) throws EmailException
    {

        // This method throws an EmailException if the entered email does not match a particular pattern

        if(!emailInput.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) throw new EmailException("Invalid email address entered");
        else
        {
            System.out.println("Email Address Accepted");
        }
    }

    public static void VerifyConfirmPassword(String password, String confirmPassword) throws ConfirmPasswordException
    {

        // This method ensures the entered confirm password matches the previous password

        if(!password.equals(confirmPassword)) throw new ConfirmPasswordException("The passwords do not match"); 
        else
        {
            System.out.println("Passwords Match");
        }
    }

    public static void VerifyEircode(String eircode) throws EircodeException
    {

        // This method ensures entered eircodes match a particular pattern 

        if(!eircode.matches("[A-Za-z]{1}[0-9]{1,2}[ -]{1}[A-Za-z0-9]{4}")) throw new EircodeException("Invalid Eircode"); //Allows for lowercase and uppercase letters and spaces and dashes
        else
        {
            System.out.println("Eircode Is Valid");
        }
    }

    public static void VerifyUsernameExists(String username, String column, String table) throws UsernameExistsException
    {

        // This method ensures an entered username is not already taken

        if(DatabaseManager.countRows(table, column, username)) throw new UsernameExistsException("Username is taken");  
        else 
        {
            System.out.println("Username is valid");
        }  
    }

    public static void VerifyUsernameNotFound(String username, String column, String table) throws UsernameNotFoundException
    {

        // This method checks if a username exists in the database

        if(!DatabaseManager.countRows(table, column, username)) throw new UsernameNotFoundException("Username is not found");  
        else 
        {
            System.out.println("Username is found");
        }  
    }

    public static void VerifyWrongPassword(String username, String password) throws WrongPasswordException
    {

        // This method checks if a users password is correct

        if(!DatabaseManager.checkPassword(username, password)) throw new WrongPasswordException("Incorrect password entered");
        else
        {
            System.out.println("Password is valid");
        }
        
    }

    public static void VerifyInt(String item) throws InputMismatchException
    {

        // This method ensures only ints are entered

        if(!item.matches("[0-9]{1,10}")) throw new InputMismatchException("Only Numbers Allowed in Number Fields");
        else
        {
            System.out.println("Input Successful");
        }
    }

    public static void VerifyDouble(String item) throws InputMismatchException
    {

        // This method ensures only doubles are entered
        
        try
        {
            @SuppressWarnings("unused")
            Double parsed = Double.parseDouble(item);
        }
        catch(NumberFormatException e)
        {
            throw new InputMismatchException("Only Numbers Allowed in Number Fields");
        }
    }

}
