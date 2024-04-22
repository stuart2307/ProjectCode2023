import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonAL implements ActionListener
{
    JPanel fromPanel;
    public BackButtonAL(JPanel from)
        {   
            fromPanel = from;
        }
    public void actionPerformed(ActionEvent backClicked)
        {
            GUIManager.backButton(fromPanel);
        }
}
