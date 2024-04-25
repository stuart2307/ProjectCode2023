import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButtonAL implements ActionListener //Implementing the ActionListener class
{
    public LogoutButtonAL() //Empty Constructor
        {

        }    
    public void actionPerformed(ActionEvent logoutClicked) //Calling methods to log the user out
        {
            CurrentSession.logUserOut();
            GUIManager.loggedOut();
        }
}
