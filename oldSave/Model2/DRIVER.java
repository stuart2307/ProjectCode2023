import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DRIVER 
    {
        public static void main(String args[])
            {
                Connection connection = null;
                final String DATABASE_URL = "jdbc://localhost/crocodeal";
                try
                    {
                        connection = DriverManager.getConnection(DATABASE_URL, "root", "");
                    }
                catch(SQLException failedConnection)
                    {
                        System.out.println("CRITICAL ERROR. ABORTING PROCESS.");
                        failedConnection.printStackTrace();
                    }
            }
    }
