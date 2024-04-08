import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class signupButtonAL implements ActionListener
{
    JPanel from;
    public signupButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeSignup(from);
        }    
}
