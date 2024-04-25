import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from;
    public BackButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        {   
            from = switchFrom;
        }
    public void actionPerformed(ActionEvent backClicked) //Changing from the current panel to the previous panel
        {
            GUIManager.backButton(from);
        }
}
