import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AccountButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from; 
    public AccountButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        {
            from = switchFrom;
        }
    public void actionPerformed(ActionEvent accountButtonClicked) //Changing to the account panel from the current panel
        {
            GUIManager.changeAccount(from, CurrentSession.getUserID());
        }    
}
