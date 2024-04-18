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
    protected JButton loginButton = new JButton("Log In");
    protected JButton signUpButton = new JButton("Sign Up");
    protected JButton logoutButton = new JButton("Log Out");
    protected JButton placeAdButton = new JButton("Place Advertisement");
    protected JButton viewMarketplaceButton = new JButton("View MarketPlace");
    protected JButton accountButton = new JButton("Account");
    protected JButton deleteButton = new JButton("Delete Advertisement");
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
    private JLabel phone = new JLabel();
    private JLabel email = new JLabel();
    private JLabel county = new JLabel();

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

        preLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        preLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        preLoginButtonPanel.setBackground(MarketPlaceGUI.green);
        postLoginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        postLoginButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        postLoginButtonPanel.setBackground(MarketPlaceGUI.green);

        loginButton.addActionListener(new loginButtonAL(ViewAd.this));
        signUpButton.addActionListener(new signupButtonAL(ViewAd.this));
        placeAdButton.addActionListener(new placeAdButtonAL(ViewAd.this));
        logoutButton.addActionListener(new logoutButtonAL());
        accountButton.addActionListener(new accountButtonAL(ViewAd.this));
        viewMarketplaceButton.addActionListener(new marketplaceButtonAL(ViewAd.this));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clicked)
            {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Advertisement?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
            {
                DatabaseManager.deleteEntry("advertisements","AdvertisementId" , "" + adID + "");
                JOptionPane.showMessageDialog(null, "Your Advertisement has been deleted");
                GUIManager.changeMarketplace(ViewAd.this);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Your account has not been deleted");
            }
            }
        });

        preLoginButtonPanel.add(viewMarketplaceButton);
        preLoginButtonPanel.add(loginButton); 
        preLoginButtonPanel.add(signUpButton);

        postLoginButtonPanel.add(viewMarketplaceButton);
        postLoginButtonPanel.add(placeAdButton);
        postLoginButtonPanel.add(accountButton);
        postLoginButtonPanel.add(logoutButton);

        topPanel.add(preLoginButtonPanel, BorderLayout.CENTER);

        //Bottom Panel Code

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(MarketPlaceGUI.grey);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        price.setFont(informationFont);
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


        imagePanel = new JPanel();
        imagePanel.add(adImage, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 3));
        imagePanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 3;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        bottomPanel.add(imagePanel, gbc);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        priceDescPanel = new JPanel(new GridBagLayout());
        priceDescPanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 3));
        priceDescPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        gbc.gridx = 3;
        bottomPanel.add(priceDescPanel, gbc);

        vehicleInfoPanel = new JPanel(new GridBagLayout());
        vehicleInfoPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        vehicleInfoPanel.setPreferredSize(new Dimension(xBlock * 6, yBlock * 2));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 6;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        bottomPanel.add(vehicleInfoPanel, gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        sellerInfoPanel = new JPanel(new GridBagLayout());
        sellerInfoPanel.setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, true));
        sellerInfoPanel.setPreferredSize(new Dimension(xBlock * 3, yBlock * 5));
        gbc.gridx = 7;
        gbc.gridheight = 5;
        gbc.weighty = gbc.gridheight / 10;
        bottomPanel.add(sellerInfoPanel);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        priceDescPanel.add(price, gbc);

        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridheight++;
        gbc.weighty = gbc.gridheight / 10;
        priceDescPanel.add(adDescription, gbc);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        vehicleInfoPanel.add(fuelType, gbc);
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 2;
        vehicleInfoPanel.add(mileage, gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridx = 4;
        vehicleInfoPanel.add(previousOwners, gbc);

        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        vehicleInfoPanel.add(gearBox, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 2;
        vehicleInfoPanel.add(engineSize, gbc);
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.weightx = gbc.gridwidth / 10;
        gbc.weighty = gbc.gridheight / 10;
        sellerInfoPanel.add(sellerPic, gbc);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = gbc.gridwidth = 2;
        gbc.gridx++;
        sellerInfoPanel.add(sellerUsername, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = gbc.gridwidth = 3;
        sellerInfoPanel.add(phone, gbc);

        gbc.gridy++;
        sellerInfoPanel.add(email, gbc);

        gbc.gridy++;
        sellerInfoPanel.add(county, gbc);

        add(bottomPanel, BorderLayout.CENTER);

        // Footer Panel Code

        footerPanel = new JPanel();
        footerPanel.setBackground(MarketPlaceGUI.green);
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        footerButtonPanel = new JPanel();
        footerButtonPanel.setBackground(MarketPlaceGUI.green);
        footerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));

        footerPanel.add(footerButtonPanel);
        add(footerPanel, BorderLayout.SOUTH);

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
                    adDescription.setText("Description: " + adResultSet.getString("Description"));
                    price.setText("$" + adResultSet.getInt("Price"));
                    fuelType.setText("Fuel Type: " + adResultSet.getString("FuelType"));
                    mileage.setText("Mileage: " + adResultSet.getString("Mileage"));
                    previousOwners.setText("Previous Owners: " + adResultSet.getInt("PreviousOwners"));
                    engineSize.setText("Engine Size: " + adResultSet.getDouble("EngineSize"));
                    gearBox.setText("Gearbox: " + adResultSet.getString("GearBox"));
                    adAccountId = adResultSet.getInt("AccountID");
                    

                    userResultSet = DatabaseManager.executeQuery(DatabaseManager.ACCOUNTS, "accounts", "AccountID", "" + adResultSet.getInt("AccountID"), "", "");
                    userResultSet.next();
                    sellerUsername.setText(userResultSet.getString("Username"));
                    phone.setText("Phone: " + userResultSet.getString("Phone"));
                    email.setText("Email: " + userResultSet.getString("Email"));
                    county.setText("County: " + userResultSet.getString("County"));
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

    public int getAdAccountId()
    {
        return adAccountId;
    }
}

