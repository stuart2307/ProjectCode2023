public class Verifiers {
    public static void VerifyEntries(String[] Entries) throws BlankEntryException
    {
      
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
        if(!numberInput.matches("[0-9 ]+")) throw new phoneException("Only numbers may be entered into the phone number textfield");
        else
        {
            System.out.println("Phone Number Accepted");
        }
    }

    public static void VerifyEmailAddress(String emailInput) throws EmailException
    {
        if(!emailInput.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) throw new EmailException("Invalid email address entered");
        else
        {
            System.out.println("Email Address Accepted");
        }
    }

    public static void VerifyConfirmPassword(String password, String confirmPassword) throws ConfirmPasswordException
    {
        if(!password.equals(confirmPassword)) throw new ConfirmPasswordException("The passwords do not match"); 
        else
        {
            System.out.println("Passwords Match");
        }
    }

    public static void VerifyEircode(String eircode) throws EircodeException
    {
        if(!eircode.matches("[A-Za-z]{1}[0-9]{1,2}[ -/A-Za-z]{2,3}[0-9]{2}")) throw new EircodeException("Invalid Eircode"); //Allows for lowercase and uppercase letters and spaces and dashes
        else
        {
            System.out.println("Eircode Is Valid");
        }
    }

    public static void VerifyUsernameExists(String username, String column, String table) throws UsernameExistsException
    {
        if(DatabaseManager.countRows(table, column, username)) throw new UsernameExistsException("Username is taken");  
        else 
        {
            System.out.println("Username is valid");
        }  
    }

    public static void VerifyUsernameNotFound(String username, String column, String table) throws UsernameNotFoundException
    {
        if(!DatabaseManager.countRows(table, column, username)) throw new UsernameNotFoundException("Username is not found");  
        else 
        {
            System.out.println("Username is found");
        }  
    }

    public static void VerifyWrongPassword(String username, String password) throws WrongPasswordException
    {
        if(DatabaseManager.checkPassword(username, password)) throw new WrongPasswordException("Incorrect password entered");
        else
        {
            System.out.println("Password is valid");
        }
        
    }

}
