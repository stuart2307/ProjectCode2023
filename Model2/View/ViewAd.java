import java.awt.BorderLayout;
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
    private JPanel logoPanel;
    private JPanel titlePanel;
    protected JPanel buttonPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel priceDescPanel;
    private JPanel sellerInfoPanel;
    private JPanel vehicleInfoPanel;
    protected JButton loginButton = new JButton("Log In");
    protected JButton signUpButton = new JButton("Sign Up");
    protected JButton logoutButton = new JButton("Log Out");
    protected JButton placeAdButton = new JButton("Place Advertisement");
    protected JButton viewMarketplaceButton = new JButton("View MarketPlace");
    protected JButton accountButton = new JButton("Account");
    private ResultSet adResultSet;
    private GridBagConstraints gbc = new GridBagConstraints();
    protected int adID;
    
    private ResultSet userResultSet;

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
        title.setForeground(MarketPlaceGUI.white); 
        logoPanel.add(title, BorderLayout.CENTER);      
        topPanel.add(logoPanel);                                                        // Adds the title to the logoPanel and positions it to the centre

        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        titlePanel.setBackground(MarketPlaceGUI.green);
        adTitle.setFont(titleFont);
        adTitle.setForeground(MarketPlaceGUI.white);
        titlePanel.add(adTitle);
        topPanel.add(titlePanel);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(MarketPlaceGUI.green);
        loginButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent loginClicked)
                {
                    GUIManager.changeLogin(ViewAd.this);
                }
        });
        signUpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent signupClicked)
                {
                    GUIManager.changeSignup(ViewAd.this);
                }
        });
        placeAdButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent placeAdClicked)
                {
                    GUIManager.changeLogin(ViewAd.this);
                }
        });
        logoutButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent logoutClicked)
                {
                    GUIManager.loggedOut();
                    CurrentSession.logUserOut();
                }
        });
        accountButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent accountClicked)
                {
                    GUIManager.changeAccount(ViewAd.this);
                }
        });
        viewMarketplaceButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent marketplaceClicked)
                {
                    GUIManager.changeMarketplace(ViewAd.this);
                }
        });
        buttonPanel.add(viewMarketplaceButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        //Bottom Panel Code

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(MarketPlaceGUI.grey);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        price.setFont(informationFont);
        price.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        fuelType.setFont(informationFont);
        fuelType.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        gearBox.setFont(informationFont);
        gearBox.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        mileage.setFont(informationFont);
        mileage.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        engineSize.setFont(informationFont);
        engineSize.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        previousOwners.setFont(informationFont);
        previousOwners.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        adDescription.setFont(informationFont);
        adDescription.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));
        sellerUsername.setFont(informationFont);
        sellerUsername.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, true));

        

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 3;
        gbc.weightx = gbc.gridwidth;
        gbc.weighty = gbc.gridheight;
        bottomPanel.add(adImage, gbc);
        
        priceDescPanel = new JPanel(new GridBagLayout());
        
        add(bottomPanel, BorderLayout.CENTER);
        
       
    } 
    public Boolean populateScreen(int advertisementID)
        {
            try 
                {
                    adResultSet = DatabaseManager.executeQuery(DatabaseManager.ADVERTISEMENTS, "advertisements", "AdvertisementID", "" + advertisementID, "", "");
                    adResultSet.next();
                    adID = advertisementID;
                    Blob image = adResultSet.getBlob("Image");
                    adImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(450, 450, Image.SCALE_SMOOTH)));
                    adImage.setText("");
                    adTitle.setText(adResultSet.getInt("Year") + " " + adResultSet.getString("Make") + " " + adResultSet.getString("Model"));
                    adDescription.setText(adResultSet.getString("Description"));
                    price.setText("$" + adResultSet.getInt("Price"));
                    fuelType.setText("Fuel Type: " + adResultSet.getString("FuelType"));
                    mileage.setText("Mileage: " + adResultSet.getString("Mileage"));
                    previousOwners.setText("Previous Owners: " + adResultSet.getInt("PreviousOwners"));
                    engineSize.setText("Engine Size: " + adResultSet.getDouble("EngineSize"));
                    gearBox.setText("Gearbox: " + adResultSet.getString("GearBox"));

                    userResultSet = DatabaseManager.executeQuery(DatabaseManager.ACCOUNTS, "accounts", "AccountID", "" + adResultSet.getInt("AccountID"), "", "");
                    userResultSet.next();
                    sellerUsername.setText(userResultSet.getString("Username"));
                    image = userResultSet.getBlob("ProfilePic");
                    sellerPic.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                    
                    return true;
                }
            catch (SQLException sqlException)
                {
                    sqlException.printStackTrace();
                    adImage.setText("No image found!");
                    return false;
                }
            
            
        }
}

