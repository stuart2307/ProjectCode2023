import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JPanel
{
    private JLabel usernameLabel; //Declaring variables
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
    private JLabel title = new JLabel("Log In"); //Setting the label's text

    public Login()
    {
        setLayout(new GridBagLayout()); //Setting the layout and background colour
        setBackground(green);
        GridBagConstraints gbc = new GridBagConstraints();

        backButton = new JButton("Back"); //Placing the back button, and adding an action listener to it
        backButton.addActionListener(new BackButtonAL(Login.this)); 
        gbc.gridx = 0; 
        gbc.gridy = 0; // Place at the first row
        gbc.insets = new Insets(10, 10, 10, 10); //Specifying the padding for this element
        add(backButton, gbc);


        title.setFont(titleFont);                                                                               // Sets the title font, bold and size
        title.setForeground(white);                                                                             // Sets the title text colour to white
        title.setBackground(green);

        title.setOpaque(true); //Setting the title to be visible
        gbc.gridx = 1;
        gbc.gridy = 0; // Place the title next to the back button
        gbc.insets = new Insets(0, 0, 10, 0); //Specifying the padding for this element
        add(title,gbc);

        usernameLabel = new JLabel("Enter Username:"); //Setting the label's text
        gbc.insets = new Insets(0, 0, 0, 10); //Specifying the padding for this element
        gbc.gridx = 0;
        gbc.gridy = 1; //Placing the label below the title and corresponding field below the title
        add(usernameLabel, gbc);

        usernameInput = new JTextField();
        usernameInput.setColumns(30);
        gbc.gridx = 1;
        gbc.gridy = 1; 
        add(usernameInput, gbc);

        passwordLabel = new JLabel("Enter Password:"); //Setting the label's text
        gbc.insets = new Insets(0, 0, 0, 10); //Specifying the padding for this element
        gbc.gridx = 0;
        gbc.gridy = 2; //PLacing the password label and corresponding field below the username field
        add(passwordLabel, gbc);

        passwordInput = new JPasswordField();
        passwordInput.setColumns(30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordInput, gbc);
 
        loginButton = new JButton("Log In"); //Setting the label's text
        gbc.gridx = 1;
        gbc.gridy = 4; //Placing the log in button at the bottom 
        gbc.insets = new Insets(10, 0, 0, 15); //Specifying the padding for this element
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
            Verifiers.VerifyEntries(valueParameter); //Verifying that the entries are not blank
            if(blankEntryFlag == true) 
            {
                blankEntryWarning.setVisible(false); //Setting the entry to be invisible once the verifier has been passed
            }
        }
        catch(BlankEntryException e)
        {
            blankEntryFlag = true; //Sets the boolean to show the warning if the exception is caught
            blankEntryWarning = new JLabel("All Entries Must Be Filled Out");
            gbc.gridx = 1;
            gbc.gridy = 5; //Placing the label at the bottom
            add(blankEntryWarning, gbc); 
            blankEntryWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
            blankEntryWarning.repaint();
            e.printStackTrace(); //Shows details about the exception
        }

        try
        {
            Verifiers.VerifyUsernameNotFound(username, "Username", "accounts"); //Verifying that the username exists
            if(invalidUsernameFlag == true)
            {
                usernameWarning.setVisible(false); //Setting the entry to be invisible once the verifier has been passed
            }
        }
        catch(UsernameNotFoundException e)
        {
            invalidUsernameFlag = true; //Sets the boolean to show the warning if the exception is caught
            usernameWarning = new JLabel("Username Not Found");
            gbc.gridx = 2;
            gbc.gridy = 1;
            add(usernameWarning, gbc);
            usernameWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
            usernameWarning.repaint(); //Shows details about the exception might have to be rewritten
            e.printStackTrace();
        }

        try
        {
            Verifiers.VerifyWrongPassword(username, password); //Verify that the password entered is correct
            if(wrongPasswordFlag == true)
            {
                passwordWarning.setVisible(false); //Setting the entry to be invisible once the verifier has been passed
            }
            ResultSet rs = DatabaseManager.executeQuery(new String[]{"AccountID"}, "accounts", "Username", username, "", ""); //Retrieving the user's details
            rs.next();
            CurrentSession.logUserIn(rs.getInt("AccountID")); //Setting the user as logged in in order to unlock additional features 
            System.out.println("Login Successful");
            GUIManager.loggedIn(); //Unlocking the additional features
            GUIManager.changeMarketplace(Login.this, ""); //Changing to the marketplace
            usernameInput.setText("");
            passwordInput.setText(""); //Clearing the inputs so they don't show up after trying to log in again
        }
        catch(WrongPasswordException e){
            wrongPasswordFlag = true; //Sets the boolean to show the warning if the exception is caught
            passwordWarning = new JLabel("Incorrect Password");
            gbc.gridx = 2;
            gbc.gridy = 2;
            add(passwordWarning, gbc);
            passwordWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
            passwordWarning.repaint(); //Shows details about the exception might have to be rewritten
            e.printStackTrace();
        }
        catch(SQLException sqlError)
        {
            sqlError.printStackTrace();
        }
        }
        });
    }
}




