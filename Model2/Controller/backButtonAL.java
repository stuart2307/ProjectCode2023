import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class backButtonAL implements ActionListener
{
    JPanel fromPanel;
    public backButtonAL(JPanel from)
        {   
            fromPanel = from;
        }
    public void actionPerformed(ActionEvent backClicked)
        {
            GUIManager.backButton(fromPanel);
        }
}
