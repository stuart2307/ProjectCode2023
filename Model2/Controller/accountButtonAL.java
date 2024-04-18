import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class accountButtonAL implements ActionListener 
{
    JPanel from;
    public accountButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }
    public void actionPerformed(ActionEvent accountButtonClicked)
        {
            GUIManager.changeAccount(from, CurrentSession.getUserID());
        }    
}
