import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MarketPlaceGUI extends JPanel
{  

    // Declarations
    private JPanel topPanel;
    private JPanel searchBarPanel;
    private JPanel logoPanel;
    private JPanel loginSignupPanel;
    private JPanel advertisements;
    private JButton placeAdButton;
    private JButton loginButton;
    private JButton signUpButton;
    private JButton searchButton;
    private JButton viewAccount = new JButton("Account");
    private JTextField searchField;
    private AdPanel placeAdPanel = new AdPanel();
    private SignUp SignUp2 = new SignUp();
    private Login loginPanel = new Login();
    private AdPreview ad = new AdPreview();
    private AdPreview ad2 = new AdPreview();
    private AdPreview ad3 = new AdPreview();
    private AdPreview ad4 = new AdPreview();
    private AdPreview ad5 = new AdPreview();
    private ViewAccount acc ;

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");

    public static Color green = new Color(44,238,144);                                                // Primary menu colour
    public static Color white = new Color(255,255,255);                                               // Title text colour
    public static Color grey = new Color(219,226,233);                                                // Primary background colour

    

    // Constructor

    public MarketPlaceGUI()
        { 
            setLayout(new BorderLayout());                                                 // Creates a JPanel instance called mainPanel  
            setBackground(grey);     
            placeAdButton = new JButton("Place Ad");                                                    // Creates a JButton instance called loginButton
            placeAdButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent placeAdClicked)
                {
                    GUIManager.changeCreateAd(MarketPlaceGUI.this);
                }
            });              
            loginButton = new JButton("Login");                                                    // Creates a JButton instance called loginButton
            loginButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent loginClicked)
                {
                    GUIManager.changeLogin(MarketPlaceGUI.this);
                }
            });
            signUpButton = new JButton("Sign Up");                                                 // Creates a JButton instance called signUpButton
            signUpButton.addActionListener(new ActionListener()                                         // Add an action listener to the signup button
            {
                public void actionPerformed(ActionEvent signupClicked)                                  // Anonymous inner class contains what happens when button is clicked
                {
                    GUIManager.changeSignup(MarketPlaceGUI.this);
                }
            });
            searchButton = new JButton("Search");                                                  // Creates a JButton instance called searchButton
            searchButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent searchButtonClicked)
                {
                    System.out.println("SearchButton clicked");                                       // Search button method stub
                }
            }); 
            searchField = new JTextField(20);                                                   // Creates a JTextField instance called searchField
            
            topPanel = new JPanel(new GridLayout(1, 3));                                                  // Creates a top panel using Grid layout (1 row, 2 columns) to hold the button panels

            searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                                           // Creates a panel using the Flow layout and positions it to the left
            searchBarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));        // Adds some borders to make things look nice
            searchBarPanel.setBackground(green);                                                                    // Sets colour of the searchbar panel to green
            searchBarPanel.add(searchButton);
            searchBarPanel.add(searchField);

            logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));                                              // Creates a JPanel called logoPanel and positions its contents to the centre using flow layout
            logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));             // Sets a custom border around the contents 
            logoPanel.setBackground(green);                                                                         // Sets the background to green
            title.setFont(titleFont);                                                                               // Sets the title font, bold and size
            title.setForeground(white);                                                                             // Sets the title text colour to white
            logoPanel.add(title, BorderLayout.CENTER);                                                              // Adds the title to the logoPanel and positions it to the centre
            
            loginSignupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                        // Creates a panel using the Flow layout and positions it to the right
            loginSignupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));      // Adds some borders to make things look nice
            loginSignupPanel.setBackground(green);                                                                  // Sets the colour of the loginSignup panel to green
            loginSignupPanel.add(placeAdButton);
            loginSignupPanel.add(loginButton);
            loginSignupPanel.add(signUpButton); 

            topPanel.add(searchBarPanel);
            topPanel.add(logoPanel);
            topPanel.add(loginSignupPanel);


            advertisements = new JPanel();
            advertisements.setBackground(grey);
            advertisements.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            advertisements.setLayout(new BoxLayout(advertisements, BoxLayout.Y_AXIS));
            advertisements.add(ad);
            advertisements.add(ad2);
            advertisements.add(ad3);
            advertisements.add(ad4);
            advertisements.add(ad5);

            JScrollPane scrollPane = new JScrollPane(advertisements);
            scrollPane.setBackground(grey);
            scrollPane.setPreferredSize(new Dimension(100, 100));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            add(scrollPane, BorderLayout.CENTER);
            add(topPanel, BorderLayout.NORTH);                                                // Adds the top panel to the main panel using the border layout to position it to the top of the screen
            viewAccount.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent signupClicked)                                  // Anonymous inner class contains what happens when button is clicked
                {
                    GUIManager.changeViewAccount(MarketPlaceGUI.this);
                }
            });
            add(viewAccount);

        }
}
