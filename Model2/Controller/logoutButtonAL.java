import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logoutButtonAL implements ActionListener
{
    public logoutButtonAL()
        {

        }    
    public void actionPerformed(ActionEvent logOut)
        {
            CurrentSession.logUserOut();
            GUIManager.loggedOut();
        }
}
