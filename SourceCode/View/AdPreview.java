import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Blob;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdPreview extends JPanel
{
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel imageLabel;
    private JPanel infoPanel;
    private JPanel detailsPanel;
    private ImageIcon adImageIcon = new ImageIcon();
    private int AdID;
    private JPanel fromPanel;
    private Font labelFont = new Font("Arial", Font.BOLD, 15);
    private GridBagConstraints gbc;

    // Constructor
    public AdPreview(JPanel from)
    {
        //Specifies which panel the adpreview is coming from. needed for ActionListeners
        fromPanel = from;
        //Sets some basic styling and layout features of the panel
        setLayout(new BorderLayout());
        setBackground(MarketPlaceGUI.white);
        setMaximumSize(new Dimension(650, 325));
        setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 5, false));
        
        //Instantiates labels and sets their font
        titleLabel = new JLabel("Title:");
        titleLabel.setFont(labelFont);
        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(labelFont);
        priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
        imageLabel = new JLabel("IMAGE HERE");
        imageLabel.setFont(labelFont);
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5));

        //Instantiates detailsPanel and gives it a gridbag layout
        detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        detailsPanel.setBackground(MarketPlaceGUI.white);

        //Sets gridbag constraints for individual components in the details and info panels, and adds them to their respective panels.
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(imageLabel, gbc);
        infoPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 1;
        detailsPanel.add(infoPanel);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        infoPanel.add(titleLabel, gbc);
        gbc.gridy++;
        gbc.gridy++;
        infoPanel.add(descriptionLabel, gbc);
        gbc.gridy++;
        gbc.gridy++;
        infoPanel.add(priceLabel, gbc);
        
        //Adds detailsPanel to the AdPreview
        add(detailsPanel, BorderLayout.CENTER);
        //Adds a mouse listener to the ad preview, which switches to the View Ad page of the ad preview when it is clicked
        addMouseListener(new MouseListener() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                GUIManager.changeViewAd(fromPanel, AdID);
            }
            //All methods must be overridden, even if they don't have a function
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
    }

    // Setter Methods
    public void setAdID(int id) {
        AdID = id;
    }
    
    public void setTitle(String title) {
        titleLabel.setText("Title: " + title);
    }

    public void setSeller(String seller) {
        descriptionLabel.setText("Seller: " + seller);
    }

    public void setPrice(int price) {
        priceLabel.setText("Price: $" + price);
    }
    
    public void setImage(Blob imageBlob)
    {
        imageLabel.setIcon(null);
        imageLabel.setText("");
        try
        {
            byte imageBytes[] = imageBlob.getBytes(1, (int) imageBlob.length());
            adImageIcon.setImage(Toolkit.getDefaultToolkit().createImage(imageBytes).getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            imageLabel.setIcon(adImageIcon);
        }
        catch (SQLException error)
        {
            error.printStackTrace();
            imageLabel.setText("IMAGE NOT FOUND");
        }
    }
    // Getter Methods
    public int getId(){
        return AdID;
    }
}
