import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButtonAL implements ActionListener
{
    public LogoutButtonAL()
        {

        }    
    public void actionPerformed(ActionEvent logoutClicked)
        {
            CurrentSession.logUserOut();
            GUIManager.loggedOut();
        }
}
