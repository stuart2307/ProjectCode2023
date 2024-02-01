
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

public class DRIVER 
{
    public static void main(String args[])
        {
            DatabaseManager testDBM = new DatabaseManager();

            testDBM.createEntry("accounts", "'testuser', 'TestUser123', 'User of Tests', 82,  'Lil Usey', 'England', 'The Big One', 'Y35 FKYU', 'hello@goodbye.sup', '0871234567'" );
        }
}
