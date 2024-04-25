import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LoginButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from;
    public LoginButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent loginClicked) //Changing from the current panel to the login panel
        {
            GUIManager.changeLogin(from);
        }
}