import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class loginButtonAL implements ActionListener
{
    JPanel from;
    public loginButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeLogin(from);
        }
}