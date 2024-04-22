import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SignupButtonAL implements ActionListener
{
    private JPanel from;
    public SignupButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent signup)
        {
            GUIManager.changeSignup(from);
        }    
}
