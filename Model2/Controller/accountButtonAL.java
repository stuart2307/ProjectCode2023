import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AccountButtonAL implements ActionListener 
{
    private JPanel from;
    public AccountButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }
    public void actionPerformed(ActionEvent accountButtonClicked)
        {
            GUIManager.changeAccount(from, CurrentSession.getUserID());
        }    
}
