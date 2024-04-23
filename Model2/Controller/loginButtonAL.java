import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LoginButtonAL implements ActionListener
{
    private JPanel from;
    public LoginButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent loginClicked)
        {
            GUIManager.changeLogin(from);
        }
}