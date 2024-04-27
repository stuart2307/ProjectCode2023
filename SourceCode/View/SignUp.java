import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUp extends JPanel 
{

    private JLabel userNameLabel; //Declaring labels for input text fields
    private JLabel passWordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel nameLabel;
    private JLabel houseNumberLabel;
    private JLabel streetNameLabel;
    private JLabel cityLabel;
    private JLabel countyLabel;
    private JLabel eirCodeLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;    
    private JLabel blankEntryWarning;
    private JLabel invalidPhoneWarning;
    private JLabel invalidEmailWarning;
    private JLabel confirmPasswordWarning;
    private JLabel invalidEircodeWarning;
    private JLabel invalidUsernameWarning;

    private boolean blankEntryFlag;
    private boolean phoneNoFlag;
    private boolean usernameFlag;
    private boolean eircodeFlag;
    private boolean emailFlag;
    private boolean passwordFlag;

    private JTextField userNameInput; //Declaring corresponding textfields for inputs
    private JPasswordField passWordInput;
    private JPasswordField confirmPasswordInput;
    private JTextField nameInput;
    private JTextField houseNumberInput;
    private JTextField streetNameInput;
    private JTextField cityInput;
    private JTextField countyInput;
    private JTextField eirCodeInput;
    private JTextField emailInput;
    private JTextField phoneInput;
    private JButton signUpButton;
    private JButton backButton;

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Sign Up");

    public SignUp() {
        
        setBorder(BorderFactory.createLineBorder(green, 2));
        setBackground(green);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //No weight is added to the components so they are automatically placed in the centre of the screen

        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonAL(SignUp.this));
        gbc.gridx = 0; //Choosing where to place the component using x and y values for the gridbagconstraints
        gbc.gridy = 0; // Place at the first row
        gbc.insets = new Insets(10, 10, 10, 10); 
        add(backButton, gbc);

        userNameLabel = new JLabel("Enter Username:"); //Username input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 10); //Sets external padding for all components

        add(userNameLabel, gbc);

        userNameInput = new JTextField(); //Adding corresponding text fields to the JLabels
        userNameInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 1;

        add(userNameInput, gbc);

        passWordLabel = new JLabel("Enter Password:"); //password input
        gbc.gridx = 0;
        gbc.gridy = 2;  

        add(passWordLabel, gbc);

        passWordInput = new JPasswordField(); //Adding corresponding text fields to the JLabels
        passWordInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 2;  

        add(passWordInput, gbc); //confirm password input

        confirmPasswordLabel = new JLabel(" Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;

        add(confirmPasswordLabel, gbc);

        confirmPasswordInput = new JPasswordField(); //Adding corresponding text fields to the JLabels
        confirmPasswordInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 3;

        add(confirmPasswordInput, gbc); 

        nameLabel = new JLabel("Enter Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 4;

        add(nameLabel, gbc);

        nameInput = new JTextField(); //Adding corresponding text fields to the JLabels
        nameInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 4;

        add(nameInput, gbc); //house number input

        houseNumberLabel = new JLabel("Enter House Number:");
        gbc.gridx = 0;
        gbc.gridy = 5;

        add(houseNumberLabel, gbc);

        houseNumberInput = new JTextField(); //Adding corresponding text fields to the JLabels
        houseNumberInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 5;
 
        add(houseNumberInput, gbc); //street name input

        streetNameLabel = new JLabel("Enter Street Name:");
        gbc.gridx = 0;
        gbc.gridy = 6;

        add(streetNameLabel, gbc);

        streetNameInput = new JTextField(); //Adding corresponding text fields to the JLabels
        streetNameInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 6;

        add(streetNameInput, gbc); //city input

        cityLabel = new JLabel("Enter City:");
        gbc.gridx = 0;
        gbc.gridy = 7;

        add(cityLabel, gbc);

        cityInput = new JTextField(); //Adding corresponding text fields to the JLabels
        cityInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 7;

        add(cityInput, gbc); //county input

        countyLabel = new JLabel("Enter County:");
        gbc.gridx = 0;
        gbc.gridy = 8;

        add(countyLabel, gbc);

        countyInput = new JTextField(); //Adding corresponding text fields to the JLabels
        countyInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 8;

        add(countyInput, gbc); //eircode input

        eirCodeLabel = new JLabel("Enter Eircode:");
        gbc.gridx = 0;
        gbc.gridy = 9;

        add(eirCodeLabel, gbc);

        eirCodeInput = new JTextField(); //Adding corresponding text fields to the JLabels
        eirCodeInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 9;

        add(eirCodeInput, gbc); //email input

        emailLabel = new JLabel("Enter Email:");
        gbc.gridx = 0;
        gbc.gridy = 10;

        add(emailLabel, gbc);

        emailInput = new JTextField(); //Adding corresponding text fields to the JLabels
        emailInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 10;

        add(emailInput, gbc); //Phone input

        phoneLabel = new JLabel("Enter Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 11;

        add(phoneLabel, gbc);

        phoneInput = new JTextField(); //Adding corresponding text fields to the JLabels
        phoneInput.setColumns(30); //Set columns sets the width of the textfield 
        gbc.gridx = 1;
        gbc.gridy = 11;

        add(phoneInput, gbc);

        title.setFont(titleFont);                                                                               // Sets the title font, bold and size
        title.setForeground(white);                                                                             // Sets the title text colour to white
        title.setBackground(green);

        title.setOpaque(true);                                                                         //Allows the title to be visible over other components
        gbc.gridx = 1;                                                                                          
        gbc.gridy = 0;                                                                                         
        add(title,gbc); 

        signUpButton = new JButton("Sign Up"); //JButton placed near the bottom of the other components                                                              
        gbc.gridx = 1;
        gbc.gridy = 12;
        add(signUpButton,gbc);

        blankEntryWarning = new JLabel("All Entries must be filled out"); //JLabel that gives the user a warning
        gbc.gridx = 1;
        gbc.gridy = 13;
        blankEntryWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(blankEntryWarning, gbc);

        invalidUsernameWarning = new JLabel("Username Already Exists"); //JLabel that gives the user a warning
        gbc.gridx = 2;
        gbc.gridy = 1;
        invalidUsernameWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(invalidUsernameWarning, gbc);

        confirmPasswordWarning = new JLabel("The Password And Confirm Password do not match"); //JLabel that gives the user a warning
        gbc.gridx = 2;
        gbc.gridy = 3;
        confirmPasswordWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(confirmPasswordWarning, gbc);

        invalidEircodeWarning = new JLabel("Invalid Eircode Entered"); //JLabel that gives the user a warning
        gbc.gridx = 2;
        gbc.gridy = 9;
        invalidEircodeWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(invalidEircodeWarning, gbc);

        invalidEmailWarning = new JLabel("Invalid Email Entered"); //JLabel that gives the user a warning
        gbc.gridx = 2;
        gbc.gridy = 10;
        invalidEmailWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(invalidEmailWarning, gbc);

        invalidPhoneWarning = new JLabel("Invalid Phone Number Entered"); //JLabel that gives the user a warning
        gbc.gridx = 2;
        gbc.gridy = 11;
        invalidPhoneWarning.setVisible(false); //Visibility of the warning depends on whether or not a boolean variable was set to true
        add(invalidPhoneWarning, gbc);

        signUpButton.addActionListener(new ActionListener() { //Anonymous inner class to handle the sign up event since this will probably be it's only use

        public void actionPerformed(ActionEvent signUp){ 
     
        String valueParameter[] = new String[11];

        String username = userNameInput.getText(); //Taking the values from the textfields and converting them to text
        String password = String.valueOf(passWordInput.getPassword()); //Taking the value from JPasswordField and converting it to string since it's stored as a char
        String confPassword = String.valueOf(confirmPasswordInput.getPassword());
        String name = nameInput.getText();
        String houseNumber = houseNumberInput.getText();
        String streetName = streetNameInput.getText();
        String city = cityInput.getText();
        String county = countyInput.getText();
        String eircode = eirCodeInput.getText();
        String email = emailInput.getText();
        String phone = phoneInput.getText();

        //Inserting values to the values array
        valueParameter[0] = username; 
        valueParameter[1] = password;
        valueParameter[2] = name;
        if (houseNumber.equals(""))
        {
            valueParameter[3] = null;
        }
        else
        {
        valueParameter[3] = houseNumber;            
        }
        valueParameter[4] = streetName;
        valueParameter[5] = city;
        valueParameter[6] = county;
        valueParameter[7] = eircode;
        valueParameter[8] = email;
        valueParameter[9] = phone;
        File imagePathFile = new File("View/Crocodefault.jpg");
        valueParameter[10] = imagePathFile.getAbsolutePath().replace("\\", "/");
        try{
            Verifiers.VerifyEntries(valueParameter); //Verifying that the entries are not blank
            if(blankEntryFlag == true)
            {
                blankEntryWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
                blankEntryFlag = false; 
            }
        }
        catch(BlankEntryException blankEntryException)
        {
            blankEntryFlag = true; //Sets the boolean to show the warning if the exception is caught
            blankEntryWarning.setVisible(true); //Shows the warning
            blankEntryException.printStackTrace(); //Shows details about the exception
        }
        finally 
            {
                blankEntryWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                blankEntryWarning.repaint();  
            }
            

        try{
        Verifiers.VerifyUsernameExists(username, "Username", "accounts"); //Verifying that the username is not taken
        if(usernameFlag == true) 
        {
            invalidUsernameWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
            usernameFlag = false;
        }
        }
        catch(UsernameExistsException usernameException)
        {
            usernameFlag = true; //Sets the boolean to show the warning if the exception is caught
            invalidUsernameWarning.setVisible(true); //Shows the warning
            usernameException.printStackTrace(); //Shows details about the exception
        }
        finally
            {
                invalidUsernameWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                invalidUsernameWarning.repaint();
            }
            
        try{
        Verifiers.VerifyConfirmPassword(password, confPassword); //Verifying that the passwords entered are identical 
        if(passwordFlag == true)
        {
            confirmPasswordWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
            passwordFlag = false;
        }
        }
        catch(ConfirmPasswordException passwordException){
            passwordFlag = true; //Sets the boolean to show the warning if the exception is caught
            confirmPasswordWarning.setVisible(true); //Shows the warning
            passwordException.printStackTrace(); //Shows details about the exception
        }
        finally
            {
                confirmPasswordWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                confirmPasswordWarning.repaint(); 
            }
            
        try{
        Verifiers.VerifyEircode(eircode); //Verifying that the eircode entered is valid
        if(eircodeFlag == true) 
        {
            invalidEircodeWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
            eircodeFlag = false;
        }
        }
        catch(EircodeException eircodeException){
            eircodeFlag = true; //Sets the boolean to show the warning if the exception is caught
            invalidEircodeWarning.setVisible(true); //Shows the warning
            eircodeException.printStackTrace(); //Shows details about the exception
        }
        finally
            {
                invalidEircodeWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                invalidEircodeWarning.repaint();  
            }
            

        try{
        Verifiers.VerifyEmailAddress(email); //Verifying that the email entered is valid
        if(emailFlag == true)
        {
            invalidEmailWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
            emailFlag = false;
        }
        }
        catch(EmailException emailException){
            emailFlag = true; //Sets the boolean to show the warning if the exception is caught
            invalidEmailWarning.setVisible(true); //Shows the warning
            emailException.printStackTrace(); //Shows details about the exception
        }
        finally
            {
                invalidEmailWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                invalidEmailWarning.repaint(); 
            }
            

        try{
        Verifiers.VerifyPhoneNumber(phone); //Verifying that the phone number entered is valid
        if(phoneNoFlag == true)
        {
            invalidPhoneWarning.setVisible(false); //Sets the boolean to show the warning to false if the entry is passed
            phoneNoFlag = false;
        }
         
        }
        catch(phoneException phoneNoException){
            phoneNoFlag = true; //Sets the boolean to show the warning if the exception is caught
            invalidPhoneWarning.setVisible(true); //Shows the warning
            phoneNoException.printStackTrace();//Shows details about the exception
        } 
        finally
            {
                invalidPhoneWarning.revalidate(); //Updating the page depending in order to show or hide the warnings
                invalidPhoneWarning.repaint();
            }
            

        if (blankEntryFlag || usernameFlag || passwordFlag || eircodeFlag || emailFlag || phoneNoFlag) //If any of the flags are set to true, there will be no entry made in the database
            {
                System.out.println("oops, account not created!");
            }
        else
            {
                DatabaseManager.createEntry("accounts", DatabaseManager.ACCOUNTS, valueParameter);
                GUIManager.backButton(SignUp.this);
            }
        }
        
    });
}
}

