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
private JLabel pageTitle;

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

public SignUp() {
 //   DatabaseManager DBM = new DatabaseManager(); //Creating an instance of the database manager
    new JPanel(new GridBagLayout());
    //new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    signUpButton = new JButton("Sign Up"); //Sign up button
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
            Verifiers.VerifyUsernameExists(username, "Username", "accounts");
            Verifiers.VerifyEntries(valueParameter);
            Verifiers.VerifyEircode(eircode);
            Verifiers.VerifyPhoneNumber(phone);
            Verifiers.VerifyConfirmPassword(password, confPassword);
            Verifiers.VerifyEmailAddress(email);
            DatabaseManager.createEntry("accounts", DatabaseManager.ACCOUNTS, valueParameter);
        }
        catch(BlankEntryException e){
            e.printStackTrace();
        }
        catch(phoneException p){
            p.printStackTrace();
        }
        catch(EmailException s){
            s.printStackTrace();
        }
        catch(ConfirmPasswordException c){
            c.printStackTrace();
        }
        catch(EircodeException i){
            i.printStackTrace();
        }
        catch(UsernameExistsException e){
            e.printStackTrace();
        }
        }
    });

    pageTitle = new JLabel("Sign Up"); //Adding text to the JLabels 
    pageTitle.setFont(new Font(null, 0, 30));
    pageTitle.setLabelFor(cityInput);
    userNameLabel = new JLabel("Enter Username");
    passWordLabel = new JLabel("Enter Password");
    confirmPasswordLabel = new JLabel("Confirm Password");
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
    confirmPasswordInput = new JPasswordField();
    confirmPasswordInput.setColumns(30);
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

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 5;
    add(userNameLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    add(userNameInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    add(passWordLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 2;
    add(passWordInput);

    gbc.gridx = 1;
    gbc.gridy = 3;
    add(confirmPasswordLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 3;
    add(confirmPasswordInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    add(nameLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 4;
    add(nameInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 5;
    add(houseNumberLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 5;
    add(houseNumberInput,gbc);

    
    gbc.gridx = 1;
    gbc.gridy = 6;
    add(streetNameLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 6;
    add(streetNameInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 7;
    add(cityLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 7;
    add(cityInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 8;
    add(countyLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 8;
    add(countyInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 9;
    add(eirCodeLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 9;
    add(eirCodeInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 10;
    add(emailLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 10;
    add(emailInput,gbc);

    gbc.gridx = 1;
    gbc.gridy = 11;
    add(phoneLabel,gbc);

    gbc.gridx = 2;
    gbc.gridy = 11;
    add(phoneInput,gbc);

    gbc.gridx = 3;
    gbc.gridy = 12;
    add(signUpButton,gbc);
}
}
