import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
    private JPanel editDeletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel adPanel;
    protected JButton loginButton = new JButton("Log In");
    protected JButton signUpButton = new JButton("Sign Up");
    protected JButton logoutButton = new JButton("Log Out");
    protected JButton placeAdButton = new JButton("Place Advertisement");
    protected JButton viewMarketplaceButton = new JButton("View MarketPlace");
    protected JButton accountButton = new JButton("Account");
    private ResultSet adResultSet;
    protected int adID;
    private JLabel sellerPic = new JLabel();
    private JLabel sellerUsername = new JLabel();
    private ResultSet userResultSet;

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private Font informationFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");
    protected JLabel adTitle = new JLabel("The Ad!");
    private JLabel adImage = new JLabel("Insert image here");
    private JLabel adDescription = new JLabel("You shouldn't be seeing this.");


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
        bottomPanel.setBackground(MarketPlaceGUI.green);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.add(editDeletePanel);
    } 
    public Boolean populateScreen(int advertisementID)
        {
            try 
                {
                    adResultSet = DatabaseManager.executeQuery(DatabaseManager.ADVERTISEMENTS, "advertisements", "AdvertisementID", "" + advertisementID, "", "");
                    adResultSet.next();
                    adID = advertisementID;
                    Blob image = adResultSet.getBlob("Image");
                    adImage.setIcon(new ImageIcon(image.getBytes(1, (int) image.length())));
                    adImage.setText("");
                    adTitle.setText(adResultSet.getInt("Year") + " " + adResultSet.getString("Make") + " " + adResultSet.getString("Model"));
                    adDescription.setText(adResultSet.getString("Description"));
                    userResultSet = DatabaseManager.executeQuery(DatabaseManager.ACCOUNTS, "accounts", "AccountID", "" + adResultSet.getInt("AccountID"), "", "");
                    userResultSet.next();
                    sellerUsername.setText(userResultSet.getString("Username"));
                    image = userResultSet.getBlob("ProfilePic");
                    sellerPic.setIcon(new ImageIcon(image.getBytes(1, (int) image.length())));
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

