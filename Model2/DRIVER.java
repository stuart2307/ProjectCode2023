
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

public class DRIVER 
{
    public static void main(String args[])
        {
            DatabaseManager testDBM = new DatabaseManager();
            String valueArray[] = new String[11];

            testDBM.createEntry("accounts", ACCOUNTS, valueArray);
            testDBM.clearArray(valueArray);
        }
}
