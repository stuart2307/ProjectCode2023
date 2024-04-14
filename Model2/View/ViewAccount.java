import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

public class ViewAccount extends JPanel
{
    private JPanel logoPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel editDeletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel marketPlaceButtonPanel;
    private JPanel backPanel;
    private JPanel informationPanel;
    private JPanel advertisementPanel;
    private JPanel accountPanel;
    private JPanel profilePicPanel = new JPanel(new GridLayout(1,1));
    private JPanel namePanel = new JPanel(new GridLayout(1,2));
    private JPanel eircodePanel = new JPanel(new GridLayout(1,2));
    private JPanel phonePanel = new JPanel(new GridLayout(1,2));
    private JButton placeAdButton = new JButton("Place Advertisement");
    private JButton viewMarketplaceButton = new JButton("View MarketPlace");
    private JButton backButton = new JButton("Back");
    private JButton likeButton = new JButton("Like");
    private JButton dislikeButton = new JButton("Dislike");
    private JButton editDetails = new JButton("Edit Account");
    private JButton deleteAccount = new JButton("Delete Account");

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private Font informationFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");
    private JLabel profilePicture = new JLabel("Insert image here");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel eircodeLabel = new JLabel("Eircode:");
    private JLabel phoneLabel = new JLabel("Phone:");
    private JLabel nameLabel2 = new JLabel();
    private JLabel eircodeLabel2 = new JLabel();
    private JLabel phoneLabel2 = new JLabel();
    private JLabel adsPlacedLabel = new JLabel("Recent Advertisements:");
    private JLabel fillerLabel1 = new JLabel("Filler");
    private JLabel fillerLabel2 = new JLabel("Filler");
    private JLabel fillerLabel3 = new JLabel("Filler");

    private String[] accountInformation = new String[4];
    private ResultSet accountDetails;
    private int userChoice;
    
    public static Color black = new Color(000,000,000);

    //Needs To display profile pic and recent ads

    public ViewAccount()
    {
                
        informationPanel = new JPanel(new GridLayout(1, 2));
        advertisementPanel = new JPanel(new GridLayout(4,1));
        
        topPanel = new JPanel(new GridLayout(1,3));
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        setBackground(MarketPlaceGUI.grey);

        //Top Panel Code

        backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                                           // Creates a panel using the Flow layout and positions it to the left
        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));        // Adds some borders to make things look nice
        backPanel.setBackground(MarketPlaceGUI.green);                                                      // Sets colour of the searchbar panel to green
        backPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent goBack)
            {
                GUIManager.backButton(ViewAccount.this);
            }
        });
    
        logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));                                              // Creates a JPanel called logoPanel and positions its contents to the centre using flow layout
        logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));             // Sets a custom border around the contents 
        logoPanel.setBackground(MarketPlaceGUI.green);                                                          // Sets the background to green                                                                        
        title.setFont(titleFont);                                                                               // Sets the title font, bold and size
        title.setForeground(MarketPlaceGUI.white); 
        logoPanel.add(title, BorderLayout.CENTER);                                                              // Adds the title to the logoPanel and positions it to the centre

        marketPlaceButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                        // Creates a panel using the Flow layout and positions it to the right
        marketPlaceButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));      // Adds some borders to make things look nice
        marketPlaceButtonPanel.setBackground(MarketPlaceGUI.green);                                                   // Sets the colour of the loginSignup panel to green
        marketPlaceButtonPanel.add(placeAdButton);
        placeAdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent placeAd)
            {
                GUIManager.changeCreateAd(ViewAccount.this);
            }
        });

        marketPlaceButtonPanel.add(viewMarketplaceButton);
        viewMarketplaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent viewMarketplace)
            {
                GUIManager.changeMarketplace(ViewAccount.this);
            }
        });

        topPanel.add(backPanel);
        topPanel.add(logoPanel);
        topPanel.add(marketPlaceButtonPanel);
        topPanel.setBackground(MarketPlaceGUI.green);

        //Account Information Code
        accountPanel = new JPanel(new GridLayout(3,1));
        nameLabel.setFont(informationFont);
        eircodeLabel.setFont(informationFont);
        phoneLabel.setFont(informationFont);
        
        nameLabel2.setFont(informationFont);
        eircodeLabel2.setFont(informationFont);
        phoneLabel2.setFont(informationFont);

        profilePicPanel.add(profilePicture);
        profilePicPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, black));

        namePanel.add(nameLabel);
        namePanel.add(nameLabel2);
        namePanel.setBorder(BorderFactory.createMatteBorder(0,1,1,0, black)); //left,bottom

        eircodePanel.add(eircodeLabel);
        eircodePanel.add(eircodeLabel2);
        eircodePanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1, black)); //right,up,down

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneLabel2);
        phonePanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, black)); //bottom

        accountPanel.setBorder(BorderFactory.createLineBorder(black, 2));
        accountPanel.add(profilePicture);
        accountPanel.add(namePanel);
        accountPanel.add(eircodePanel);
        accountPanel.add(phonePanel);
        accountPanel.add(likeButton);
        accountPanel.add(dislikeButton);

        adsPlacedLabel.setFont(informationFont);
        advertisementPanel.setBorder(BorderFactory.createLineBorder(black, 2));
        advertisementPanel.add(adsPlacedLabel);
        advertisementPanel.add(fillerLabel1);
        advertisementPanel.add(fillerLabel2);
        advertisementPanel.add(fillerLabel3);

        informationPanel.add(accountPanel);
        informationPanel.add(advertisementPanel);

        add(informationPanel, BorderLayout.CENTER);

        //Bottom Panel Code

        bottomPanel = new JPanel(new GridLayout(1,2));
        bottomPanel.setBackground(MarketPlaceGUI.green);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        editDeletePanel.setBackground(MarketPlaceGUI.green);
        editDeletePanel.add(editDetails);
        editDeletePanel.add(deleteAccount);
        bottomPanel.add(editDeletePanel);
        add(bottomPanel, BorderLayout.SOUTH);
        editDetails.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent searchButtonClicked)
            {
                GUIManager.changeEditAccount(ViewAccount.this);
            }
        }); 

        deleteAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent deleteAccount)
            {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
                {
                    DatabaseManager.deleteEntry("accounts","AccountID" , "" + CurrentSession.getUserID() + "");
                    JOptionPane.showMessageDialog(null, "Your account has been deleted");
                    GUIManager.changeMarketplace(ViewAccount.this);
                    CurrentSession.logUserOut();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Your account has not been deleted");
                }
            }
        }); 
} 
    public void populatePage()
    {
        //Code to access the account information
        accountInformation[0] = "Name";
        accountInformation[1] = "Eircode";
        accountInformation[2] = "Phone";
        accountInformation[3] = "ProfilePic";
        profilePicture.setText("IMAGE GOES HERE");
        try{
            accountDetails = DatabaseManager.executeQuery(accountInformation, "accounts", "AccountID", "" + CurrentSession.getUserID(), "", "");
            if (accountDetails.next()) 
        {
        nameLabel2.setText(accountDetails.getString("Name"));
        eircodeLabel2.setText(accountDetails.getString("Eircode"));
        phoneLabel2.setText(accountDetails.getString("Phone"));
                Blob image = accountDetails.getBlob("ProfilePic");
                profilePicture.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(image.getBytes(1, (int) image.length())).getScaledInstance(300, 300 , Image.SCALE_SMOOTH)));
                profilePicture.setText("");
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
