import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JPanel
{
    private JLabel usernameLabel;
    private JLabel usernameWarning;
    private JLabel passwordWarning;
    private JLabel blankEntryWarning;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton backButton;

    private boolean wrongPasswordFlag;
    private boolean invalidUsernameFlag;
    private boolean blankEntryFlag;

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Log In");

    public Login()
    {
        setLayout(new GridBagLayout());
        setBackground(green);
        GridBagConstraints gbc = new GridBagConstraints();

        backButton = new JButton("Back");
        gbc.gridx = 0; 
        gbc.gridy = 0; // Place at the first row
        gbc.insets = new Insets(10, 10, 10, 10); 
        add(backButton, gbc);


        title.setFont(titleFont);                                                                               // Sets the title font, bold and size
        title.setForeground(white);                                                                             // Sets the title text colour to white
        title.setBackground(green);

        title.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(title,gbc);

        usernameLabel = new JLabel("Enter Username:");
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        usernameInput = new JTextField();
        usernameInput.setColumns(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameInput, gbc);

        passwordLabel = new JLabel("Enter Password:");
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordInput = new JPasswordField();
        passwordInput.setColumns(30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordInput, gbc);

        loginButton = new JButton("Log In");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 0, 15);
        add(loginButton,gbc);

        loginButton.addActionListener(new ActionListener() { //Anonymous inner class to handle the sign up event since this will probably be it's only use

        public void actionPerformed(ActionEvent signUp){ 
     
        String valueParameter[] = new String[2];

        String username = usernameInput.getText(); //Taking the values from the textfields and converting them to text
        String password = String.valueOf(passwordInput.getPassword()); //Taking the value from JPasswordField and converting it to string since it's stored as a char

        //Inserting values to the values array
        valueParameter[0] = username; 
        valueParameter[1] = password;
        try
        {
            Verifiers.VerifyEntries(valueParameter);
            if(blankEntryFlag == true)
            {
                blankEntryWarning.setVisible(false);
            }
        }
        catch(BlankEntryException e)
        {
            blankEntryFlag = true;
            blankEntryWarning = new JLabel("All Entries Must Be Filled Out");
            gbc.gridx = 1;
            gbc.gridy = 5;
            add(blankEntryWarning, gbc);
            blankEntryWarning.revalidate();
            blankEntryWarning.repaint();
            e.printStackTrace();
        }
        try
        {
            Verifiers.VerifyUsernameNotFound(username, "Username", "accounts");
            if(invalidUsernameFlag == true)
            {
                usernameWarning.setVisible(false);
            }
        }
        catch(UsernameNotFoundException e)
        {
            invalidUsernameFlag = true;
            usernameWarning = new JLabel("Username Not Found");
            gbc.gridx = 2;
            gbc.gridy = 1;
            add(usernameWarning, gbc);
            usernameWarning.revalidate();
            usernameWarning.repaint();
            e.printStackTrace();
        }

        try
        {
            Verifiers.VerifyWrongPassword(username, password);
            if(wrongPasswordFlag == true)
            {
                passwordWarning.setVisible(false);
            }
            System.out.println("Login Successful");
        }
        catch(WrongPasswordException e){
            wrongPasswordFlag = true;
            passwordWarning = new JLabel("Incorrect Password");
            gbc.gridx = 2;
            gbc.gridy = 2;
            add(passwordWarning, gbc);
            passwordWarning.revalidate();
            passwordWarning.repaint();
            e.printStackTrace();
        }
        }
        });
    }
}

