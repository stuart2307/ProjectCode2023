
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

public class DRIVER 
{
    public static void main(String args[])
        {
            DatabaseManager.establishConnection();
            //DatabaseManager testDBM = new DatabaseManager();
            //String valueArray[] = {"Revolution825", "12345", "Diarmuid1", "10", "Knocklonegad", "Bagenalstown", "Carlow", "R22 XH60", "diarmuidoneill2003@gmail.com", "0891234567"};
            //testDBM.createEntry("accounts", DatabaseManager.ACCOUNTS, valueArray);

            ////testDBM.deleteEntry("accounts","AccountID","6");

            //testDBM.executeUpdate("Accounts", "username", "Revolution829", "AccountID", "1");

            new AccountPage();
            // SignUp2();
        }
}
