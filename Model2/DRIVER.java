
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

public class DRIVER 
{
    public static void main(String args[])
        {
            DatabaseManager.establishConnection();
            GUIManager.prepareManager();
        }
}
