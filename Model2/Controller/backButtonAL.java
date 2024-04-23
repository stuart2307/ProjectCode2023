import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonAL implements ActionListener
{
    private JPanel from;
    public BackButtonAL(JPanel switchFrom)
        {   
            from = switchFrom;
        }
    public void actionPerformed(ActionEvent backClicked)
        {
            GUIManager.backButton(from);
        }
}
