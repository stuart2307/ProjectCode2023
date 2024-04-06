import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewAd extends JPanel
{
    private JPanel logoPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel editDeletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel accountPanel;
    private JPanel namePanel = new JPanel(new GridLayout(1,2));
    private JPanel eircodePanel = new JPanel(new GridLayout(1,2));
    private JPanel phonePanel = new JPanel(new GridLayout(1,2));
    protected JButton loginButton = new JButton("Log In");
    protected JButton signUpButton = new JButton("Sign Up");
    protected JButton logoutButton = new JButton("Log Out");
    protected JButton placeAdButton = new JButton("Place Advertisement");
    protected JButton viewMarketplaceButton = new JButton("View MarketPlace");
    protected JButton accountButton = new JButton("Account");
    private JButton likeButton = new JButton("Like");
    private JButton dislikeButton = new JButton("Dislike");
    private JButton editDetails = new JButton("Edit Account");
    private JButton deleteAccount = new JButton("Delete Account");

    public static Font titleFont = new Font("Arial", Font.BOLD, 30);
    private Font informationFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");
    protected JLabel adTitle = new JLabel("The Ad!");
    private JLabel profilePicture = new JLabel("Insert image here");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel eircodeLabel = new JLabel("Eircode:");
    private JLabel phoneLabel = new JLabel("Phone:");


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

        //Account Information Code
        accountPanel = new JPanel(new GridLayout(3,1));
        nameLabel.setFont(informationFont);
        eircodeLabel.setFont(informationFont);
        phoneLabel.setFont(informationFont);

        namePanel.add(nameLabel);

        eircodePanel.add(eircodeLabel);

        phonePanel.add(phoneLabel);

        accountPanel.add(profilePicture);
        accountPanel.add(namePanel);
        accountPanel.add(eircodePanel);
        accountPanel.add(phonePanel);
        accountPanel.add(likeButton);
        accountPanel.add(dislikeButton);

        //Bottom Panel Code

        bottomPanel = new JPanel(new GridBagLayout());
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

