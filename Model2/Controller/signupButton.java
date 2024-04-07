import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class signupButton implements ActionListener
{
    JPanel from;
    public signupButton(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeSignup(from);
        }    
}
