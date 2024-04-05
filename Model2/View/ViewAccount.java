import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ViewAccount extends JPanel
{
    private JPanel logoPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel editDeletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel marketPlaceButtonPanel;
    private JPanel messagesPanel;
    private JPanel informationPanel;
    private JPanel advertisementPanel;
    private JPanel accountPanel;
    private JPanel namePanel = new JPanel(new GridLayout(1,2));
    private JPanel eircodePanel = new JPanel(new GridLayout(1,2));
    private JPanel phonePanel = new JPanel(new GridLayout(1,2));
    private JButton placeAdButton = new JButton("Place Advertisement");
    private JButton viewMarketplaceButton = new JButton("View MarketPlace");
    private JButton showMessagesButton = new JButton("Messages");
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
    private JLabel nameLabel2;
    private JLabel eircodeLabel2;
    private JLabel phoneLabel2;
    private JLabel adsPlacedLabel = new JLabel("Recent Advertisements:");
    private JLabel fillerLabel1 = new JLabel("Filler");
    private JLabel fillerLabel2 = new JLabel("Filler");
    private JLabel fillerLabel3 = new JLabel("Filler");
    private JOptionPane deleteAccountWarning = new JOptionPane();

    private String[] accountInformation = new String[3];
    private ResultSet accountDetails;
    private int userChoice;

    public ViewAccount()
    {
        //Code to access the account information
        accountInformation[0] = "Name";
        accountInformation[1] = "Eircode";
        accountInformation[2] = "Phone";
        
        try{
        accountDetails = DatabaseManager.executeQuery(accountInformation, "accounts", "AccountID", "5", "", "");
        while (accountDetails.next()) 
        {
            nameLabel2 = new JLabel(accountDetails.getString(1));
            eircodeLabel2 = new JLabel(accountDetails.getString(2));
            phoneLabel2 = new JLabel(accountDetails.getString(3));
        }
        }
        catch(SQLException sqlException)
        {
            System.out.println("Error");
            sqlException.printStackTrace();
        }
        informationPanel = new JPanel(new GridLayout(1, 2));
        advertisementPanel = new JPanel(new GridLayout(4,1));
        
        topPanel = new JPanel(new GridLayout(1,3));
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        setBackground(MarketPlaceGUI.grey);

        //Top Panel Code

        messagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                                           // Creates a panel using the Flow layout and positions it to the left
        messagesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));        // Adds some borders to make things look nice
        messagesPanel.setBackground(MarketPlaceGUI.green);                                                      // Sets colour of the searchbar panel to green
        messagesPanel.add(showMessagesButton);
    
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
        marketPlaceButtonPanel.add(viewMarketplaceButton);

        topPanel.add(messagesPanel);
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

        namePanel.add(nameLabel);
        namePanel.add(nameLabel2);

        eircodePanel.add(eircodeLabel);
        eircodePanel.add(eircodeLabel2);

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneLabel2);

        accountPanel.add(profilePicture);
        accountPanel.add(namePanel);
        accountPanel.add(eircodePanel);
        accountPanel.add(phonePanel);
        accountPanel.add(likeButton);
        accountPanel.add(dislikeButton);

        adsPlacedLabel.setFont(informationFont);
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
                System.out.println("SearchButton clicked");                                       // Search button method stub
            }
        }); 

        deleteAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent deleteAccount)
            {
                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
                {
                    System.out.println("You pressed yes");
                } 
                else 
                {
                    System.out.println("You pressed no");
                }
            }
        }); 
        
} 
}