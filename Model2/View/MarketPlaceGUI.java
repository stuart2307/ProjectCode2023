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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//
// Class Name : MarketPlaceGUI
//
// Author     : Diarmuid O'Neill, Stuart Rossiter
//
// Purpose    : This GUI is the home base for the system. It provides all nessacary buttons for navigating to different screens,
//              it displays clickable advertisement previews to the user, and allows them to search for specific advertisements
//              using a search bar.
//

public class MarketPlaceGUI extends JPanel
{  

    // Declarations

    protected JPanel topPanel;
    private JPanel searchBarPanel;
    private JPanel logoPanel;
    protected JPanel preLoginButtonPanel;
    protected JPanel postLoginButtonPanel;
    private JPanel advertisements;
    protected JButton placeAdButton;
    protected JButton loginButton;
    protected JButton signUpButton;
    protected JButton accountButton;
    protected JButton logoutButton;
    private JButton searchButton;
    private JButton viewAccount = new JButton("Account");
    private JTextField searchField;
    private AdPreview ads[] = new AdPreview[45]; 
    private ResultSet adResultSet;
    private ResultSetMetaData adRSMD;
    private int adCount = 0;

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");
    private JLabel error = new JLabel("No ads available!");

    public static Color green = new Color(44,238,144);                                                           // Primary menu colour
    public static Color white = new Color(255,255,255);                                                          // Title text colour
    public static Color grey = new Color(219,226,233);                                                           // Primary background colour

    

    // Constructor

    public MarketPlaceGUI()
        {
            for(int i=0; i < ads.length; i++)
                {
                    ads[i] = new AdPreview(MarketPlaceGUI.this);
                } 
            generateAds("");
            setLayout(new BorderLayout());                                                                   
            setBackground(grey);      
            placeAdButton = new JButton("Place Ad");                                                              // Creates a JButton instance called placeAdButton
            placeAdButton.addActionListener(new placeAdButtonAL(MarketPlaceGUI.this));                                 // Adds an action listener to the placeAdButton to take the user to the place ad page           
            loginButton = new JButton("Login");                                                                   // Creates a JButton instance called loginButton
            loginButton.addActionListener(new loginButtonAL(MarketPlaceGUI.this));                                     // Adds an action listener to the loginButton to take the user to the login page
            signUpButton = new JButton("Sign Up");                                                                // Creates a JButton instance called signUpButton
            signUpButton.addActionListener(new signupButtonAL(MarketPlaceGUI.this));                                   // Adds an action listener to the signupButton to take the user to the sign up page
            searchButton = new JButton("Search");                                                                 // Creates a JButton instance called searchButton
            searchButton.addActionListener(new ActionListener()
            {

                // This anonymous inner class allows the user to search for specific ads 

                public void actionPerformed(ActionEvent searchButtonClicked)
                {
                    String search = "%" + searchField.getText() + "%";                                                 // Gets the search string from the JTextField
                    GUIManager.changeMarketplace(MarketPlaceGUI.this, search);                                         // This refreshes the marketplace and generates ads based on the inputted search
                }
            }); 
            accountButton = new JButton("Your Account");                                                          // Creates a JButton instance called accountButton
            accountButton.addActionListener(new accountButtonAL(MarketPlaceGUI.this));                                 // Adds an action listener to take the user to their account
            logoutButton = new JButton("Log Out");                                                                // Adds a log out button to allow the user to log out
            logoutButton.addActionListener(new logoutButtonAL());                                                      // Adds action listener to allow the user to log out 
            searchField = new JTextField(20);                                                                  // Creates a JTextField instance called searchField
            
            topPanel = new JPanel(new GridLayout(1, 3));                                                     // Creates a top panel using Grid layout (1 row, 3 columns) to hold the button panels

            searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                                              // Creates a panel using the Flow layout and positions it to the left
            searchBarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));           // Adds some borders to make things look nice
            searchBarPanel.setBackground(green);                                                                       // Sets colour of the searchbar panel to green
            searchBarPanel.add(searchButton);                                                                       
            searchBarPanel.add(searchField);                                                                        

            logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));                                                 // Creates a JPanel called logoPanel and positions its contents to the centre using flow layout
            logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));                // Sets a custom border around the contents 
            logoPanel.setBackground(green);                                                                            // Sets the background to green
            title.setFont(titleFont);                                                                                  // Sets the title font, bold and size
            title.setForeground(white);                                                                                // Sets the title text colour to white
            logoPanel.add(title, BorderLayout.CENTER);                                                                 // Adds the title to the logoPanel and positions it to the centre
            
            preLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                        // Creates a panel using the Flow layout and positions it to the right
            preLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));      // Adds some borders to make things look nice
            preLoginButtonPanel.setBackground(green);                                                                  // Sets the colour of the loginSignup panel to green
            preLoginButtonPanel.add(loginButton);
            preLoginButtonPanel.add(signUpButton); 

            postLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                       // Creates a panel using the Flow layout and positions it to the right
            postLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));     // Adds some borders to make things look nice
            postLoginButtonPanel.setBackground(green);                                                                 // Sets the colour of the loginSignup panel to green
            postLoginButtonPanel.add(placeAdButton);
            postLoginButtonPanel.add(accountButton);
            postLoginButtonPanel.add(logoutButton);
            postLoginButtonPanel.setVisible(false);                                                              // Sets this panel to be invisible until user has logged in 

            topPanel.add(searchBarPanel);
            topPanel.add(logoPanel);
            topPanel.add(preLoginButtonPanel);

            advertisements = new JPanel();                                                                             // New JPanel for advertisements
            advertisements.setBackground(grey);                                                                        // Sets background
            advertisements.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));           // Sets border
            advertisements.setLayout(new GridLayout(0, 3));                                                  // Sets new GridLayout (0 rows 3 columns)
            for (int i = 0; i < 45; i++)
                {
                    advertisements.add(ads[i]);                                                                        // Adds advertisements to the advertisements panel from the ad array
                    ads[i].setVisible(false);                                                                    // Sets their visibility to false for now
                }
            advertisements.add(error);                                                                                 // Adds this message for now

            JScrollPane scrollPane = new JScrollPane(advertisements);                                                  // Creates new scroll pane to allow scrolling
            scrollPane.setBackground(grey);
            scrollPane.setPreferredSize(new Dimension(100, 100));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);                           // Scroll bar only allows vertical scrolling when needed
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                           // Never allows horizontal scrolling

            populateAdDisplay();                                                                                       // Sets ads to be visible up to a maximum of 45 ads
            add(scrollPane, BorderLayout.CENTER);                                                                      // Adds the scroll pane to the main panel using border layout to position it to the center of the screen
            add(topPanel, BorderLayout.NORTH);                                                                         // Adds the top panel to the main panel using the border layout to position it to the top of the screen
        }
    public void generateAds(String search)
        {

            // This method retrieves ads from the database and deals with any seraches that may have been inputted

            try
            {
                adCount = 0;
                error.setVisible(false);                                                                         // Sets the error message to be invisible
                if (search.equals(""))                                                                        // If search is blank
                {
                    // This case deals with blank searches
                    adResultSet = DatabaseManager.executeQuery(new String[]{"AdvertisementID", "Year", "Make", "Model", "Price", "Image"}, "advertisements", "", "", "", "");
                }
                else
                { 
                    // This case handles when a search has been inputted
                    adResultSet = DatabaseManager.executeQuery(new String[]{"AdvertisementID", "Year", "Make", "Model", "Price", "Image"}, "advertisements", "LIKE", search, "", "");
                }
                if (adResultSet.next() == false)
                    {
                        System.out.println("oops");
                    }
                else
                    {
                        do
                        {   

                            // This loop sets the details of the ad previews to be displayed on the page

                            ads[adCount].setAdID(adResultSet.getInt("AdvertisementID"));
                            ads[adCount].setTitle(adResultSet.getInt("Year") + " " + adResultSet.getString("Make") + " " + adResultSet.getString("Model"));
                            ads[adCount].setPrice(adResultSet.getInt("Price"));
                            ads[adCount].setImage(adResultSet.getBlob("Image"));
                            adCount++;
                        } while (adResultSet.next() && adCount < 45);
                    }
                
            }
            catch (SQLException databaseError)
            {

                // Catches a potential SQLException

                error.setVisible(true);
                databaseError.printStackTrace();
            }
            finally
            {

                // Refreshes the page

                populateAdDisplay();
                revalidate();
                repaint();
            }
        }
    public void populateAdDisplay()
        {

            // This method sets up to 45 ads to be visible on the page at any one time

            for (int index = 0; index < 45; index++)
                {
                    if (index < adCount)
                    {
                        ads[index].setVisible(true);
                    }
                    else
                    {
                        ads[index].setVisible(false);
                    }
                    
                }
            if (adCount == 0)
                {

                    // If no ads are found in the database

                    error.setVisible(true);
                    for (int index = 0; index < 45; index++)
                        {
                            ads[index].setVisible(false);
                        }
                } 
        }
}
