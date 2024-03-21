import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdPreview extends JPanel
{
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;

    // Constructor

    public AdPreview()
    {
        setLayout(new BorderLayout());
        setBackground(MarketPlaceGUI.white);
        setMaximumSize(new Dimension(400, 200));
        setBorder(BorderFactory.createLineBorder(MarketPlaceGUI.grey, 10, false));


        titleLabel = new JLabel("Title:");
        descriptionLabel = new JLabel("Description:");
        priceLabel = new JLabel("Price:");

        JPanel detailsPanel = new JPanel(new GridLayout(4,1));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        detailsPanel.setBackground(MarketPlaceGUI.white);
        detailsPanel.add(titleLabel);
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(priceLabel);

        add(detailsPanel, BorderLayout.CENTER);
    }

    // Setter Methods
    
    public void setTitle(String title) {
        titleLabel.setText("Title: " + title);
    }

    public void setDescription(String description) {
        descriptionLabel.setText("Description: " + description);
    }

    public void setPrice(double price) {
        priceLabel.setText("Price: $" + price);
    }
}
