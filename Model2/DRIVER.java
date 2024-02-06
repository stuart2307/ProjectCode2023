
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

public class DRIVER 
{
    public static void main(String args[])
        {

            DatabaseManager testDBM = new DatabaseManager();
            String valueArray[] = {"1", "Revolution825", "12345, ", "Diarmuid, ", "10, ", "Knocklonegad, ", "Bagenalstown, ", "Carlow, ", "R22 XH60, ", "diarmuidoneill2003@gmail.com, ", "0891234567"};

            


            testDBM.createEntry("accounts", DatabaseManager.ACCOUNTS, valueArray);
            testDBM.clearArray(valueArray);
        }
}
