import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
    protected JPanel loginSignupPanel;
    private JPanel advertisements;
    protected JButton placeAdButton;
    protected JButton loginButton;
    protected JButton signUpButton;
    protected JButton accountButton;
    protected JButton logoutButton;
    private JButton searchButton;
    private JButton viewAccount = new JButton("Account");
    private JTextField searchField;
    private AdPreview ads[] = new AdPreview[15]; 
    private ResultSet adResultSet;
    private ResultSetMetaData adRSMD;
    private int adCount = 0;

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");
    private JLabel error = new JLabel("No ads available!");

    public static Color green = new Color(44,238,144);                                                // Primary menu colour
    public static Color white = new Color(255,255,255);                                               // Title text colour
    public static Color grey = new Color(219,226,233);                                                // Primary background colour

    

    // Constructor

    public MarketPlaceGUI()
        {
            for(int i=0; i < ads.length; i++)
                {
                    ads[i] = new AdPreview();
                } 
            generateAds();
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
            accountButton = new JButton("Your Account");
            accountButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent accountButtonClicked)
                {
                    GUIManager.changeAccount(MarketPlaceGUI.this);
                }
            });
            logoutButton = new JButton("Log Out");
            logoutButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent logoutButtonClicked)
                {
                    CurrentSession.logUserOut();
                    GUIManager.loggedOut();
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
            //loginSignupPanel.add(placeAdButton);
            loginSignupPanel.add(loginButton);
            loginSignupPanel.add(signUpButton); 

            topPanel.add(searchBarPanel);
            topPanel.add(logoPanel);
            topPanel.add(loginSignupPanel);


            advertisements = new JPanel();
            advertisements.setBackground(grey);
            advertisements.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            advertisements.setLayout(new BoxLayout(advertisements, BoxLayout.Y_AXIS));
            for (int i = 0; i < 15; i++)
                {
                    advertisements.add(ads[i]);
                    ads[i].setVisible(false);
                }
            advertisements.add(error);

            JScrollPane scrollPane = new JScrollPane(advertisements);
            scrollPane.setBackground(grey);
            scrollPane.setPreferredSize(new Dimension(100, 100));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            populateAdDisplay();
            add(scrollPane, BorderLayout.CENTER);
            add(topPanel, BorderLayout.NORTH);                                                // Adds the top panel to the main panel using the border layout to position it to the top of the screen
        }
    public void generateAds()
        {
            try
            {
                adCount = 0;
                error.setVisible(false);
                adResultSet = DatabaseManager.executeQuery(new String[]{"AdvertisementID", "Make", "Model", "Year", "Price", "Image"}, "advertisements", "", "", "", "");
                adRSMD = adResultSet.getMetaData();
                if (adResultSet.next() == false)
                    {
                        System.out.println("oops");
                    }
                else
                    {
                        do
                        {
                            ads[adCount].setAdID(adResultSet.getInt("AdvertisementID"));
                            ads[adCount].setTitle(adResultSet.getInt("Year") + " " + adResultSet.getString("Make") + " " + adResultSet.getString("Model"));
                            ads[adCount].setPrice(adResultSet.getInt("Price"));
                            ads[adCount].setImage(adResultSet.getBlob("Image"));
                            adCount++;
                        } while (adResultSet.next() && adCount < 15);
                    }
                while(adResultSet.next() && adCount < 15);
                
            }
            catch (SQLException databaseError)
            {
                error.setVisible(true);
                databaseError.printStackTrace();
            }
        }
    public void populateAdDisplay()
        {
            for (int index = 0; index < adCount; index++)
                {
                    ads[index].setVisible(false);
                    error.setVisible(true);
                }
            if (adCount != 0)
                {
                    error.setVisible(false);
                    for (int index = 0; index < adCount; index++)
                        {
                            ads[index].setVisible(getFocusTraversalKeysEnabled());
                        }
                }
            
        }
}
