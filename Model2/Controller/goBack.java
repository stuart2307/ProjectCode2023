import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class goBack implements ActionListener
{
    JPanel fromPanel;
    public goBack(JPanel from)
        {   
            fromPanel = from;
        }
    public void actionPerformed(ActionEvent backClicked)
        {
            GUIManager.backButton(fromPanel);
        }
}
