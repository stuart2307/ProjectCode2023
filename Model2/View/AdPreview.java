import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class AdPreview extends JPanel
{
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel imageLabel;
    private JPanel infoPanel;
    private ImageIcon adImageIcon = new ImageIcon();
    private int AdID;
    private JPanel fromPanel;

    // Constructor
    public AdPreview(JPanel from)
    {
        fromPanel = from;
        setLayout(new BorderLayout());
        setBackground(MarketPlaceGUI.white);
        setMaximumSize(new Dimension(650, 325));
        setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.green, 10, false));
        
        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        imageLabel = new JLabel("IMAGE HERE");
        imageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5));
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        detailsPanel.setBackground(MarketPlaceGUI.white);
        GridBagConstraints gbc = new GridBagConstraints();
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
        
        add(detailsPanel, BorderLayout.CENTER);
        addMouseListener(new MouseListener() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                GUIManager.changeViewAd(fromPanel, AdID);
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
    }

    // Setter Methods
    public void setAdID(int id) {
        AdID = id;
    }
    
    public void setTitle(String title) {
        titleLabel.setText("Title: " + title);
    }

    public void setDescription(String description) {
        descriptionLabel.setText("Description: " + description);
    }

    public void setPrice(double price) {
        priceLabel.setText("Price: $" + price);
    }
    
    public void setImage(Blob imageBlob)
    {
        imageLabel.setIcon(null);
        imageLabel.setText("");
        try
        {
            byte imageBytes[] = imageBlob.getBytes(1, (int) imageBlob.length());
            adImageIcon.setImage(Toolkit.getDefaultToolkit().createImage(imageBytes).getScaledInstance(300, 300, Image.SCALE_SMOOTH));
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
