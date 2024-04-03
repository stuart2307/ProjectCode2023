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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUp2 extends JPanel {

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
    private JPanel mainPanel;
    private JPanel gridPanel;

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Sign Up");

    public SignUp2() {
        
        setBorder(BorderFactory.createLineBorder(green, 2));
        setBackground(green);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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

        title.setOpaque(true);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(title,gbc);

        signUpButton = new JButton("Sign Up");
        gbc.gridx = 1;
        gbc.gridy = 12;
        
        add(signUpButton,gbc);
        signUpButton.addActionListener(new ActionListener() { //Anonymous inner class to handle the sign up event since this will probably be it's only use

        public void actionPerformed(ActionEvent signUp){ 
     
        String valueParameter[] = new String[10];

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
        try{
            Verifiers.VerifyEntries(valueParameter);
            Verifiers.VerifyUsernameExists(username, "Username", "accounts");
            Verifiers.VerifyConfirmPassword(password, confPassword);
            Verifiers.VerifyEircode(eircode);
            Verifiers.VerifyEmailAddress(email);
            Verifiers.VerifyPhoneNumber(phone);
            DatabaseManager.createEntry("accounts", DatabaseManager.ACCOUNTS, valueParameter);
        }
        catch(BlankEntryException e){
            e.printStackTrace();
        }
        catch(phoneException e){
            e.printStackTrace();
        }
        catch(EmailException e){
            e.printStackTrace();
        }
        catch(ConfirmPasswordException e){
            e.printStackTrace();
        }
        catch(EircodeException e){
            e.printStackTrace();
        }
        catch(UsernameExistsException e){
            e.printStackTrace();
        }
        }
    });

    }
}

