import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SignupButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from;
    public SignupButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        { 
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent signup) //Changing from the current panel to the sign up panel
        {
            GUIManager.changeSignup(from);
        }    
}
