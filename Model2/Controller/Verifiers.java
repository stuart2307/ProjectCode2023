public class Verifiers {
    public static void VerifyEntries(String[] Entries) throws BlankEntryException
    {
      
        for(int i = 0; i < 9; i++)
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
}
