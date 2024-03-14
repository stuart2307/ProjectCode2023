import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUp
{

private JFrame signUpFrame;
private JLabel userNameLabel; //Declaring labels for input text fields
private JLabel passWordLabel;
private JLabel nameLabel;
private JLabel houseNumberLabel;
private JLabel streetNameLabel;
private JLabel cityLabel;
private JLabel countyLabel;
private JLabel eirCodeLabel;
private JLabel emailLabel;
private JLabel phoneLabel;    
private JLabel pageTitle;

private JTextField userNameInput; //Declaring corresponding textfields for inputs
private JPasswordField passWordInput;
private JTextField nameInput;
private JTextField houseNumberInput;
private JTextField streetNameInput;
private JTextField cityInput;
private JTextField countyInput;
private JTextField eirCodeInput;
private JTextField emailInput;
private JTextField phoneInput;
private JPanel signUpPanel;
private JButton signUpButton;

public SignUp() {
    DatabaseManager DBM = new DatabaseManager(); //Creating an instance of the database manager
    signUpFrame = new JFrame(); //Creating an instance of a jframe
    signUpPanel = new JPanel(new FlowLayout(0, 800, 20)); //Creating an instance of a jpanel
    
    signUpButton = new JButton("Sign Up"); //Sign up button
    signUpButton.addActionListener(new ActionListener() { //Anonymous inner class to handle the sign up event since this will probably be it's only use

        public void actionPerformed(ActionEvent signUp){ 
        String valueParameter[] = new String[10];

        String username = userNameInput.getText(); //Taking the values from the textfields and converting them to text
        String password = String.valueOf(passWordInput.getPassword()); //Taking the value from JPasswordField and converting it to string since it's stored as a char
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
        valueParameter[3] = houseNumber;
        valueParameter[4] = streetName;
        valueParameter[5] = city;
        valueParameter[6] = county;
        valueParameter[7] = eircode;
        valueParameter[8] = email;
        valueParameter[9] = phone;

        DBM.createEntry("accounts", DatabaseManager.ACCOUNTS, valueParameter);

        }
    });

    pageTitle = new JLabel("Sign Up"); //Adding text to the JLabels 
    pageTitle.setFont(new Font(null, 0, 30));
    userNameLabel = new JLabel("Enter Username");
    passWordLabel = new JLabel("Enter Password");
    nameLabel = new JLabel("Enter Name");
    houseNumberLabel = new JLabel("Enter House Number");
    streetNameLabel = new JLabel("Enter Street Name");
    cityLabel = new JLabel("Enter City");
    countyLabel = new JLabel("Enter County");
    eirCodeLabel = new JLabel("Enter Eircode");
    emailLabel = new JLabel("Enter Email");
    phoneLabel = new JLabel("Enter Phone Number");

    userNameInput = new JTextField(); //Adding corresponding text fields to the JLabels
    userNameInput.setColumns(30); //Set columns sets the width of the textfield 
    passWordInput = new JPasswordField();
    passWordInput.setColumns(30);
    nameInput = new JTextField();
    nameInput.setColumns(30);
    houseNumberInput = new JTextField();
    houseNumberInput.setColumns(30);
    streetNameInput = new JTextField();
    streetNameInput.setColumns(30);
    cityInput = new JTextField();
    cityInput.setColumns(30);
    countyInput = new JTextField();
    countyInput.setColumns(30);
    eirCodeInput = new JTextField();
    eirCodeInput.setColumns(30);
    emailInput = new JTextField();
    emailInput.setColumns(30);
    phoneInput = new JTextField();
    phoneInput.setColumns(30);

    signUpPanel.add(userNameLabel); //Row 1
    signUpPanel.add(userNameInput);

    signUpPanel.add(passWordLabel); //Row 2
    signUpPanel.add(passWordInput);

    signUpPanel.add(nameLabel); //Row 3
    signUpPanel.add(nameInput);

    signUpPanel.add(houseNumberLabel); //Row 4
    signUpPanel.add(houseNumberInput);

    signUpPanel.add(streetNameLabel); //Row 5
    signUpPanel.add(streetNameInput);

    signUpPanel.add(cityLabel); //Row 6
    signUpPanel.add(cityInput);

    signUpPanel.add(countyLabel); //Row 7
    signUpPanel.add(countyInput);

    signUpPanel.add(eirCodeLabel); //Row 8
    signUpPanel.add(eirCodeInput); 

    signUpPanel.add(emailLabel); //Row 9
    signUpPanel.add(emailInput);

    signUpPanel.add(phoneLabel); //Row 10
    signUpPanel.add(phoneInput);

    signUpPanel.add(signUpButton);
    
    signUpPanel.setBackground(new Color(144,238,144)); //Setting background color of the JPanel to green

    signUpFrame.add(signUpPanel, BorderLayout.CENTER); //Adding the Sign Up panel to the center of the border layout also covers the east and west borders
    signUpFrame.add(pageTitle, BorderLayout.NORTH); //Adding the page title to the top of the frame
    signUpFrame.setMinimumSize(new Dimension(640, 480)); // Sets a minimum size for the frame
    signUpFrame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
    signUpFrame.setVisible(true); //Makes the frame visible
}
}
