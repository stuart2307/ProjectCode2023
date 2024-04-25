import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewAd extends JPanel
{
    //Divides the screen up for convenience when placing components and setting their sizes
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int xBlock = (int) screenSize.getWidth() / 10;
    private int yBlock = (int) screenSize.getHeight() / 6;
    private JPanel logoPanel;
    private JPanel titlePanel;
    protected JPanel preLoginButtonPanel;
    protected JPanel postLoginButtonPanel;
    protected JPanel topPanel;
    protected JPanel bottomPanel;
    protected JPanel footerPanel;
    protected JPanel footerButtonPanel;
    private JPanel priceDescPanel;
    private JPanel sellerInfoPanel;
    private JPanel vehicleInfoPanel;
    private JPanel imagePanel;
    protected JPanel likeDislikePanel = new JPanel(new GridLayout(1, 2));
    protected JButton loginButton = new JButton("Log In");
    protected JButton signUpButton = new JButton("Sign Up");
    protected JButton logoutButton = new JButton("Log Out");
    protected JButton placeAdButton = new JButton("Place Advertisement");
    protected JButton viewMarketplaceButton = new JButton("View MarketPlace");
    protected JButton prelogMarketplaceButton = new JButton("View Marketplace");
    protected JButton accountButton = new JButton("Account");
    protected JButton deleteButton = new JButton("Delete Advertisement");
    protected JButton likeButton = new JButton("Like");
    protected JButton dislikeButton = new JButton("Dislike");
    private ResultSet adResultSet;
    private GridBagConstraints gbc = new GridBagConstraints();
    protected int adID;

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private Font informationFont = new Font("Arial", Font.BOLD, 20);
    
    private JLabel sellerPic = new JLabel();
    private JLabel sellerUsername = new JLabel();
    private JLabel title = new JLabel("Crocodeal");
    protected JLabel adTitle = new JLabel("The Ad!");
    private JLabel adImage = new JLabel("Insert image here");
    private JLabel price = new JLabel();
    private JLabel adDescription = new JLabel("You shouldn't be seeing this.");
    private JLabel fuelType = new JLabel();
    private JLabel mileage = new JLabel();
    private JLabel previousOwners = new JLabel();
    private JLabel gearBox = new JLabel();
    private JLabel engineSize = new JLabel();
    private JLabel phone = new JLabel();
    private JLabel email = new JLabel();
    private JLabel county = new JLabel();
    private JPanel userRatingPanel = new JPanel(new FlowLayout());
    private JLabel userRatingLabel = new JLabel();

    private int adAccountId;

    public ViewAd()
    {
        topPanel = new JPanel(new GridLayout(1,3));
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);

        setBackground(MarketPlaceGUI.grey);

        //Top Panel Code
    
        logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));                                              // Creates a JPanel called logoPanel and positions its contents to the centre using flow layout
        logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));             // Sets a custom border around the contents 
        logoPanel.setBackground(MarketPlaceGUI.green);                                                          // Sets the background to green                                                                        
        title.setFont(titleFont);                                                                               // Sets the title font, bold and size
        title.setForeground(MarketPlaceGUI.white);                                                              // Sets the foreground colour to white
        logoPanel.add(title, BorderLayout.CENTER);                                                              // Adds the title to the logoPanel and positions it to the centre
        topPanel.add(logoPanel);                                                                                // Adds the logoPanel to the topPanel

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));                                             // Creates a JPanel called titlePanel and positions its contents to the centre using flow layout
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));            // Sets a custom border around the contents 
        titlePanel.setBackground(MarketPlaceGUI.green);                                                         // Sets the background to green   
        adTitle.setFont(titleFont);                                                                             // Sets the ad title font, bold and size
        adTitle.setForeground(MarketPlaceGUI.white);                                                            // Sets the foreground colour to white       
        titlePanel.add(adTitle);                                                                                // Adds the ad title to the title panel
        topPanel.add(titlePanel);       

        preLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                     // Creates a JPanel called preLoginButtonPannel and positions its contents to the right using flow layout
        preLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));   // Sets a custom border around the contents
        preLoginButtonPanel.setBackground(MarketPlaceGUI.green);                                                // Sets the background to green
        postLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                    // Creates a JPanel called postLoginButtonPannel and positions its contents to the right using flow layout
        postLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Sets a custom border around the contents
        postLoginButtonPanel.setBackground(MarketPlaceGUI.green);                                               // Sets the background to green

        likeDislikePanel.setVisible(false);
        likeDislikePanel.add(likeButton);
        likeDislikePanel.add(dislikeButton);
        
        userRatingPanel.add(userRatingLabel);

        loginButton.addActionListener(new LoginButtonAL(ViewAd.this));                                          // Adds named action listeners to the buttons
        signUpButton.addActionListener(new SignupButtonAL(ViewAd.this));                                        
        placeAdButton.addActionListener(new PlaceAdButtonAL(ViewAd.this));                                      
        logoutButton.addActionListener(new LogoutButtonAL());                                                   
        accountButton.addActionListener(new AccountButtonAL(ViewAd.this));                                      
        viewMarketplaceButton.addActionListener(new MarketplaceButtonAL(ViewAd.this));                          
        prelogMarketplaceButton.addActionListener(new MarketplaceButtonAL(ViewAd.this));                        
        deleteButton.addActionListener(new ActionListener() {                                                   // Adds anonymous inner class for the "Delete Ad" button
            public void actionPerformed(ActionEvent clicked)
            {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Advertisement?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
            {
                DatabaseManager.deleteEntry("advertisements","AdvertisementId" , "" + adID + "");
                JOptionPane.showMessageDialog(null, "Your Advertisement has been deleted");
                GUIManager.changeMarketplace(ViewAd.this, "");
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Your account has not been deleted");
            }
            }
        });

        preLoginButtonPanel.add(prelogMarketplaceButton);                                                       // Adds buttons to the pre login button panel
        preLoginButtonPanel.add(loginButton);                                                                   // Adds buttons to the pre login button panel
        preLoginButtonPanel.add(signUpButton);                                                                  // Adds buttons to the pre login button panel

        postLoginButtonPanel.add(viewMarketplaceButton);                                                        // Adds buttons to the post login button panel
        postLoginButtonPanel.add(placeAdButton);                                                                // Adds buttons to the post login button panel
        postLoginButtonPanel.add(accountButton);                                                                // Adds buttons to the post login button panel
        postLoginButtonPanel.add(logoutButton);                                                                 // Adds buttons to the post login button panel

        topPanel.add(preLoginButtonPanel, BorderLayout.CENTER);                                                 // Adds prelogin button panel to the top panel

        //Bottom Panel Code
        bottomPanel = new JPanel(new GridBagLayout());                                                          // Creates a new panel with GridBagLayout
        bottomPanel.setBackground(MarketPlaceGUI.grey);                                                         // Sets the background to grey   
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));           // Sets a custom border around the contents

        price.setFont(informationFont);                                                                         // Sets the font for various labels
        fuelType.setFont(informationFont);                                                                      
        gearBox.setFont(informationFont);                                                                       
        mileage.setFont(informationFont);                                                                       
        engineSize.setFont(informationFont);                                                                    
        previousOwners.setFont(informationFont);                                                                
        adDescription.setFont(informationFont);                                                                 
        sellerUsername.setFont(informationFont);                                                                
        phone.setFont(informationFont);                                                                         
        email.setFont(informationFont);                                                                         
        county.setFont(informationFont);     

        imagePanel = new JPanel();                                                                              // Creates a new JPanel
        imagePanel.add(adImage, BorderLayout.CENTER);                                                           // Adds the adImage Label to the imagePanel
        imagePanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 3));                                     // Sets a size for the imagePanel using the blocks defined above
        imagePanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));  // Creates a green line border for the imagePanel
        gbc.anchor = GridBagConstraints.CENTER;                                                                 // Sets the GridBagConstraints Anchor
        gbc.insets = new Insets(10, 10, 10, 10);                                          // Sets the GridBagConstraints Insets
        gbc.gridx = gbc.gridy = 0;                                                                              // Sets gridx and gridy to 0
        gbc.gridheight = gbc.gridwidth = 3;                                                                     // Sets gridwidth and gridheight to 3
        gbc.weightx = gbc.gridwidth / 10;                                                                       // Sets the gridweightx to 0.3
        gbc.weighty = gbc.gridheight / 10;                                                                      // Sets the gridweighty to 0.3
        bottomPanel.add(imagePanel, gbc);                                                                       // Adds the imagePanel to the bottomPanel using the defined GridBagConstraints
        
        gbc.anchor = GridBagConstraints.NORTHWEST;                                                              
        priceDescPanel = new JPanel(new GridBagLayout());                                                       
        priceDescPanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 3));
        priceDescPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        gbc.gridx = 3;
        bottomPanel.add(priceDescPanel, gbc);                                                                   // Adds priceDescPanel to bottomPanel with altered GridBagConstraints

        vehicleInfoPanel = new JPanel(new GridBagLayout());
        vehicleInfoPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        vehicleInfoPanel.setPreferredSize(new Dimension(xBlock * 6, yBlock * 2));
        gbc.gridx = 0;                                                                                          // Changes GridBagConstraints to suit the vehicleInfoPanel
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 6;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        bottomPanel.add(vehicleInfoPanel, gbc);                                                                 // Adds vehicleInfoPanel to bottomPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.NORTHEAST;
        sellerInfoPanel = new JPanel(new GridBagLayout());
        sellerInfoPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        sellerInfoPanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 5));
        gbc.gridx = 7;
        gbc.gridheight = 5;
        gbc.weighty = gbc.gridheight / 10;
        bottomPanel.add(sellerInfoPanel);                                                                       // Adds sellerInfoPanel to bottomPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        priceDescPanel.add(price, gbc);                                                                         // Adds price to priceDescPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridheight++;
        gbc.weighty = gbc.gridheight / 10;
        adDescription.setPreferredSize(new Dimension(xBlock * 3, yBlock * 3));
        priceDescPanel.add(adDescription, gbc);                                                                 // Adds adDescription to priceDescPanel with altered GridBagConstraints


        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        vehicleInfoPanel.add(fuelType, gbc);                                                                    // Adds fuelType to vehicleInfoPanel with altered GridBagConstraints
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 2;
        vehicleInfoPanel.add(mileage, gbc);                                                                     // Adds mileage to vehicleInfoPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridx = 4;
        vehicleInfoPanel.add(previousOwners, gbc);                                                              // Adds previousOwners to vehicleInfoPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        vehicleInfoPanel.add(gearBox, gbc);                                                                     // Adds gearBox to vehicleInfoPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 2;
        vehicleInfoPanel.add(engineSize, gbc);                                                                  // Adds engineSize to vehicleInfoPanel with altered GridBagConstraints
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        sellerInfoPanel.add(sellerPic, gbc);                                                                    // Adds sellerPic to sellerInfoPanel with altered GridBagConstraints

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridwidth = 2;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.gridx++;
        sellerInfoPanel.add(sellerUsername, gbc);                                                               // Adds sellerUsername to sellerInfoPanel with altered GridBagConstraints
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = gbc.gridwidth / 10;
        sellerInfoPanel.add(phone, gbc);                                                                        // Adds phone to sellerInfoPanel with altered GridBagConstraints

        gbc.gridy++;
        sellerInfoPanel.add(email, gbc);                                                                        // Adds email to sellerInfoPanel with altered GridBagConstraints

        gbc.gridy++;
        sellerInfoPanel.add(county, gbc);                                                                       // Adds county to sellerInfoPanel with altered GridBagConstraints

        gbc.gridy++;
        sellerInfoPanel.add(likeDislikePanel, gbc);                                                             // Adds likeDislikePanel to sellerInfoPanel with altered GridBagConstraints

        gbc.gridy++;
        sellerInfoPanel.add(userRatingPanel, gbc);                                                              // Adds userRatingPanel to sellerInfoPanel with altered GridBagConstraints

        sellerInfoPanel.addMouseListener(new MouseListener()                                                    // Adds an anonymous inner class / Mouse Listener for  the sellerInfoPanel
        {
            public void mouseClicked(MouseEvent e) 
            {
                GUIManager.changeAccount(ViewAd.this, adAccountId);
            }
            public void mouseEntered(MouseEvent e) 
            {

            }
            public void mouseExited(MouseEvent e)
            {

            }
            public void mousePressed(MouseEvent e) 
            {
            
            }
            public void mouseReleased(MouseEvent e) 
            {

            }
        });

        add(bottomPanel, BorderLayout.CENTER);                                                                  // Adds the bottomPanel to the center of the bottomPanel

        // Footer Panel Code

        footerPanel = new JPanel();                                                                             // Creates a new JPanel called footerPanel
        footerPanel.setBackground(MarketPlaceGUI.green);                                                        // Sets the background colour to green
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));                                                // Sets the layout to FlowLayout
        
        footerButtonPanel = new JPanel();                                                                       // Creates a new JPanel called footerButtonPanel
        footerButtonPanel.setBackground(MarketPlaceGUI.green);                                                  // Sets the background colour to green
        footerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));                                          // Sets the layout to FlowLayout
        footerButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));      // Creates a custom border for the footerButtonPanel

        footerPanel.add(footerButtonPanel);                                                                     // Adds the footerButtonPanel to the footerPanel
        add(footerPanel, BorderLayout.SOUTH);                                                                   // Adds the footerPanel to the viewAd Panel

    } 
    public void populateScreen(int advertisementID)
        {
            try 
                {
                    adResultSet = DatabaseManager.innerJoinQuery("advertisements", "accounts", "AccountID", "AccountID", "AdvertisementID", "" + advertisementID); // Search for advertisement in database with given ID
                    adResultSet.next(); // Moves to the first row
                    adID = advertisementID; // Sets the adID variable to the provided Ad ID
                    Blob image = adResultSet.getBlob("Image");  // Gets the ad image blob
                    adImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(450, 450, Image.SCALE_SMOOTH))); // Create image from the blob, scale it to 450 x 450, and set it as the icon for adImage
                    adImage.setText(""); // Make sure the text for the adImage Label is empty
                    adTitle.setText(adResultSet.getInt("Year") + " " + adResultSet.getString("Make") + " " + adResultSet.getString("Model")); // Set the title to be the year + make + model, e.g. 2017 Ford Mondeo
                    adDescription.setText("<html><div style='width: " + (xBlock*3) + "px;'>Description: " + adResultSet.getString("Description") + "</div></html>");
                    price.setText("$" + adResultSet.getInt("Price"));
                    fuelType.setText("Fuel Type: " + adResultSet.getString("FuelType"));
                    mileage.setText("Mileage: " + adResultSet.getString("Mileage"));
                    previousOwners.setText("Previous Owners: " + adResultSet.getInt("PreviousOwners"));
                    engineSize.setText("Engine Size: " + adResultSet.getDouble("EngineSize"));
                    gearBox.setText("Gearbox: " + adResultSet.getString("GearBox"));
                    adAccountId = adResultSet.getInt("AccountID");

                    ResultSet userResultSet;
                    userResultSet = DatabaseManager.executeQuery(DatabaseManager.ACCOUNTS, "accounts", "AccountID", "" + adResultSet.getInt("AccountID"), "", "");
                    userResultSet.next();
                    sellerUsername.setText(userResultSet.getString("Username"));
                    phone.setText("Phone: " + userResultSet.getString("Phone"));
                    email.setText("Email: " + userResultSet.getString("Email"));
                    county.setText("County: " + userResultSet.getString("County"));
                    image = userResultSet.getBlob("ProfilePic");
                    sellerPic.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                    int positiveReviews = DatabaseManager.countPositiveReviews( "" + adAccountId + "");
                    int allReviews = DatabaseManager.countAllReviews( "" + adAccountId + "");
                    Double userReviewScore = ((double)positiveReviews/allReviews) * 100.0;   
                    System.out.println(userReviewScore);
                    // Round to two decimal places
                    double roundedUserReviewScore = Math.round(userReviewScore * 100.0)/100.0;
                    if (Double.isNaN(userReviewScore))
                        {
                            userRatingLabel.setText("No Reviews Found!");
                        }
                    else
                        {
                            userRatingLabel.setText("Rating: " + roundedUserReviewScore + "% Positive");
                        }
                    
                    if(CurrentSession.getLoginStatus())
                        {

                            int revieweeID = adAccountId;
                            int reviewerID = CurrentSession.getUserID();

                            likeButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent likeUser)
                                {
                                    int reviewChoice = 1;
                                    DatabaseManager.addReview(reviewerID, revieweeID, reviewChoice);                                 
                                }
                            });
                            dislikeButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent dislikeUser)
                                {
                                    int reviewChoice = 0;
                                    DatabaseManager.addReview(reviewerID, revieweeID, reviewChoice);
                                }
                            });
                            if(adResultSet.getInt("AccountID") == CurrentSession.getUserID())
                                {
                                    likeDislikePanel.setVisible(false);
                                }
                            else
                                {
                                    likeDislikePanel.setVisible(true);
                                }
                        }
                    else
                        {
                            likeDislikePanel.setVisible(false);
                        }
                }
            catch (SQLException sqlException)
                {
                    sqlException.printStackTrace();
                    adImage.setText("No image found!");
                }
            
            
        }

    public int getAdAccountId()
    {
        return adAccountId;
    }

}

