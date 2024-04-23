import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPasswordField;

public class EditAccount extends JPanel{

    private JLabel userNameLabel; //Declaring labels for input text fields
    private JLabel newPassWordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel nameLabel;
    private JLabel houseNumberLabel;
    private JLabel streetNameLabel;
    private JLabel cityLabel;
    private JLabel countyLabel;
    private JLabel eirCodeLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;    
    private JLabel profilePicture;
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
    private JButton editButton;
    private JButton backButton;
    private JButton addPic;

    private String filePath;

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Edit Details");
    private String[] accountInformation = new String[11];

    public EditAccount() {
        
        setBorder(BorderFactory.createLineBorder(green, 2));
        setBackground(green);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        backButton = new JButton("Back");
        backButton.addActionListener(new accountButtonAL(EditAccount.this));
        gbc.gridx = 0; 
        gbc.gridy = 0; // Place at the first row
        gbc.insets = new Insets(10, 10, 10, 10); 
        add(backButton, gbc);

        addPic = new JButton("Add Profile Picture");
        addPic.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent addProfilePic)
            {
                LookAndFeel laf = UIManager.getLookAndFeel();
                    try
                        {
                          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
                        }
                    catch (Exception e)
                        {
                            System.out.println("oops");
                        }
                    JFileChooser getFile = new JFileChooser();
                    getFile.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
                    if (getFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                            filePath = getFile.getSelectedFile().getAbsolutePath();
                        } 
                    try
                        {
                            UIManager.setLookAndFeel(laf);
                        }
                    catch (UnsupportedLookAndFeelException ulafe){}
            }
        }); 
        gbc.gridx = 2; 
        gbc.gridy = 0; // Place at the first row
        gbc.insets = new Insets(10, 10, 10, 10); 
        add(addPic, gbc);

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

        newPassWordLabel = new JLabel("Enter new Password:"); //password input
        gbc.gridx = 0;
        gbc.gridy = 2;  

        add(newPassWordLabel, gbc);

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

        editButton = new JButton("Confirm Edit");
        gbc.gridx = 1;
        gbc.gridy = 12;
        add(editButton,gbc);

        blankEntryWarning = new JLabel("All Entries must be filled out");
        gbc.gridx = 1;
        gbc.gridy = 13;
        blankEntryWarning.setVisible(false);
        add(blankEntryWarning, gbc);

        invalidUsernameWarning = new JLabel("Username Already Exists");
        gbc.gridx = 2;
        gbc.gridy = 1;
        invalidUsernameWarning.setVisible(false);
        add(invalidUsernameWarning, gbc);

        confirmPasswordWarning = new JLabel("The Password And Confirm Password do not match");
        gbc.gridx = 2;
        gbc.gridy = 3;
        confirmPasswordWarning.setVisible(false);
        add(confirmPasswordWarning, gbc);

        invalidEircodeWarning = new JLabel("Invalid Eircode Entered");
        gbc.gridx = 2;
        gbc.gridy = 9;
        invalidEircodeWarning.setVisible(false);
        add(invalidEircodeWarning, gbc);

        invalidEmailWarning = new JLabel("Invalid Email Entered");
        gbc.gridx = 2;
        gbc.gridy = 10;
        invalidEmailWarning.setVisible(false);
        add(invalidEmailWarning, gbc);

        invalidPhoneWarning = new JLabel("Invalid Phone Number Entered");
        gbc.gridx = 2;
        gbc.gridy = 11;
        invalidPhoneWarning.setVisible(false);
        add(invalidPhoneWarning, gbc);

        editButton.addActionListener(new ActionListener() { //Anonymous inner class to handle the sign up event since this will probably be it's only use

        public void actionPerformed(ActionEvent editAccount){ 

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
        String profilePicPath = filePath;

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
        valueParameter[10]= profilePicPath;
        try{
            Verifiers.VerifyEntries(valueParameter);
            if(blankEntryFlag == true)
            {
                blankEntryWarning.setVisible(false);
                blankEntryFlag = false;
            }
        }
        catch(BlankEntryException blankEntryException)
        {
            blankEntryFlag = true;
            blankEntryWarning.setVisible(true);
            blankEntryException.printStackTrace();
        }
        finally 
        {
            blankEntryWarning.revalidate();
            blankEntryWarning.repaint();  
        }
            
        try{
        ResultSet accountDetails = DatabaseManager.executeQuery(accountInformation, "accounts", "AccountID", "" + CurrentSession.getUserID() + "", "", "");
        if(accountDetails.next())
        {
            if(username.equals(userNameInput.getText()))
            {
                
            }
            else
            {
                try
                {
                    Verifiers.VerifyUsernameExists(username, "Username", "accounts");
                }
                catch(UsernameExistsException usernameException)
                {
                    usernameFlag = true;
                    invalidUsernameWarning.setVisible(true);
                    usernameException.printStackTrace();
                }
                finally
                {
                    invalidUsernameWarning.revalidate();
                    invalidUsernameWarning.repaint();
                } 
            }  
    }
        
        }
        catch(SQLException sqlException)
        {
            System.out.println("Error");
            sqlException.printStackTrace();
        }
            
        try{
        Verifiers.VerifyConfirmPassword(password, confPassword);
        if(passwordFlag == true)
        {
            confirmPasswordWarning.setVisible(false);
            passwordFlag = false;
        }
        }
        catch(ConfirmPasswordException passwordException){
            passwordFlag = true;
            confirmPasswordWarning.setVisible(true);
            passwordException.printStackTrace();
        }
        finally
            {
                confirmPasswordWarning.revalidate();
                confirmPasswordWarning.repaint(); 
            }
            
        try{
        Verifiers.VerifyEircode(eircode);
        if(eircodeFlag == true)
        {
            invalidEircodeWarning.setVisible(false);
            eircodeFlag = false;
        }
        }
        catch(EircodeException eircodeException){
            eircodeFlag = true;
            invalidEircodeWarning.setVisible(true);
            eircodeException.printStackTrace();
        }
        finally
            {
                invalidEircodeWarning.revalidate();
                invalidEircodeWarning.repaint();  
            }
            

        try{
        Verifiers.VerifyEmailAddress(email);
        if(emailFlag == true)
        {
            invalidEmailWarning.setVisible(false);
            emailFlag = false;
        }
        }
        catch(EmailException emailException){
            emailFlag = true;
            invalidEmailWarning.setVisible(true);
            emailException.printStackTrace();
        }
        finally
            {
                invalidEmailWarning.revalidate();
                invalidEmailWarning.repaint(); 
            }
            

        try{
        Verifiers.VerifyPhoneNumber(phone);
        if(phoneNoFlag == true)
        {
            invalidPhoneWarning.setVisible(false);
            phoneNoFlag = false;
        }
         
        }
        catch(phoneException phoneNoException){
            phoneNoFlag = true;
            invalidPhoneWarning.setVisible(true);
            phoneNoException.printStackTrace();
        }
        finally
            {
                invalidPhoneWarning.revalidate();
                invalidPhoneWarning.repaint();
            }
            

        if (blankEntryFlag || usernameFlag || passwordFlag || eircodeFlag || emailFlag || phoneNoFlag)
            {
                System.out.println("oops, account not created!");
            }
        else
            {
                for(int i=0;i<valueParameter.length;i++)
                {
                    DatabaseManager.executeUpdate("accounts",DatabaseManager.ACCOUNTS[i] ,valueParameter[i] ,"AccountId" ,"" + CurrentSession.getUserID() + "");
                    GUIManager.changeAccount(EditAccount.this, CurrentSession.getUserID());
                }
            }
        }
        
    });
}
public void populateEditPage()
{
    //Code to access the account information
    accountInformation[0] = "Username";
    accountInformation[1] = "Password";
    accountInformation[2] = "Name";
    accountInformation[3] = "HouseNumber";
    accountInformation[4] = "StreetName";
    accountInformation[5] = "City";
    accountInformation[6] = "County";
    accountInformation[7] = "Eircode";
    accountInformation[8] = "Email";
    accountInformation[9] = "Phone";
    accountInformation[10] = "ProfilePic";
    try{
        ResultSet accountDetails = DatabaseManager.executeQuery(accountInformation, "accounts", "AccountID", "" + CurrentSession.getUserID(), "", "");
        if (accountDetails.next()) 
    {
        userNameInput.setText(accountDetails.getString("Username"));
        nameInput.setText(accountDetails.getString("Name"));
        houseNumberInput.setText(accountDetails.getString("HouseNumber"));
        streetNameInput.setText(accountDetails.getString("StreetName"));
        cityInput.setText(accountDetails.getString("City"));
        countyInput.setText(accountDetails.getString("County"));
        eirCodeInput.setText(accountDetails.getString("Eircode"));
        emailInput.setText(accountDetails.getString("Email"));    
        phoneInput.setText(accountDetails.getString("Phone"));
   //     Blob image = accountDetails.getBlob("ProfilePic");
   //     if(this.profilePicture != null)
   //     {
   //     profilePicture.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(300, 300 , Image.SCALE_SMOOTH)));
   //     profilePicture.setText("");
   //     }
    }
        else throw new UserNotFoundException("User not found in database!");
    }
    catch(SQLException sqlException)
    {
    System.out.println("Error");
    sqlException.printStackTrace();
    }
        catch(UserNotFoundException unfe)
        {
            unfe.printStackTrace();
        }
}
}
