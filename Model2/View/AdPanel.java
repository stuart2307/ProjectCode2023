import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdPanel extends JPanel
{
    private JPanel placeAdPanel;
    private JPanel titlePanel;
    private JPanel formPanel;
    private JLabel pageTitle;
    private JLabel makeLabel;
    private JLabel modelLabel;
    private JTextField makeField;
    private JTextField modelField;


    public AdPanel()
    {
        placeAdPanel = new JPanel();                                                // Creates a main panel for elements relating to placing an ad
        placeAdPanel.setLayout(new BoxLayout(placeAdPanel, BoxLayout.Y_AXIS));

        titlePanel = new JPanel();                                                  // Creates panel for the title
        titlePanel.setLayout(new BorderLayout());                                   // Sets the titlePanel to BorderLayout
        titlePanel.setPreferredSize(new Dimension(500, 250));          // Sets the preferred size of title panel
        titlePanel.setBackground(MarketPlaceGUI.green);
        pageTitle = new JLabel("Create Advertisement");                        // Creates the text for the title of the page 
        pageTitle.setHorizontalAlignment(JLabel.CENTER);                            // Positions the text to the center of the JLabel
        pageTitle.setFont(MarketPlaceGUI.titleFont);                                // Sets the font, size and text decoration

        titlePanel.add(pageTitle, BorderLayout.CENTER);                             // Adds the title to the center of the titlePanel using borderLayout
        
        placeAdPanel.add(titlePanel);                                               // Adds title panel to the placeAdPanel

        formPanel = new JPanel(new GridLayout(2, 2));

        // Create labels and text fields

        makeLabel = new JLabel("Car Make : ");
        makeField = new JTextField(20);
        modelLabel = new JLabel("Car Model : ");
        modelField = new JTextField(20);

        // Add labels and text fields to the panel
        
        formPanel.add(makeLabel);
        formPanel.add(makeField);
        formPanel.add(modelLabel);
        formPanel.add(modelField);

        placeAdPanel.add(formPanel);

        add(placeAdPanel);         // Adds the placeAdPanel to the screen
    }
}  
