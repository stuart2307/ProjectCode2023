import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
    private JLabel fuelLabel;
    private JLabel yearLabel;
    private JLabel mileageLabel;
    private JLabel priceLabel;
    private JLabel engineLabel;
    private JComboBox fuelType;
    private String[] fuelStrings = {"Petrol", "Diesel", "Electric", "Hybrid"};
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField mileageField;
    private JTextField priceField;
    private JTextField engineField;


    public AdPanel()
    {
        placeAdPanel = new JPanel();                                                // Creates a main panel for elements relating to placing an ad
        placeAdPanel.setLayout(new BoxLayout(placeAdPanel, BoxLayout.Y_AXIS));      // Sets the layout of the placeAdPanel to boxLayout along the Y_Axis

        titlePanel = new JPanel();                                                  // Creates panel for the title
        titlePanel.setLayout(new BorderLayout());                                   // Sets the titlePanel to BorderLayout
        titlePanel.setPreferredSize(new Dimension(500, 250));          // Sets the preferred size of title panel
        titlePanel.setBackground(MarketPlaceGUI.green);                             // Sets the colour of the title panel to green 
        pageTitle = new JLabel("Create Advertisement");                        // Creates the text for the title of the page 
        pageTitle.setHorizontalAlignment(JLabel.CENTER);                            // Positions the text to the center of the JLabel
        pageTitle.setFont(MarketPlaceGUI.titleFont);                                // Sets the font, size and text decoration

        titlePanel.add(pageTitle, BorderLayout.CENTER);                             // Adds the title to the center of the titlePanel using borderLayout
        
        placeAdPanel.add(titlePanel);                                               // Adds title panel to the placeAdPanel

        formPanel = new JPanel(new GridLayout(7, 2, 0, 20));                     // Creates a form panel using grid layout with two columns

        // Create labels and text fields

        makeLabel = new JLabel("Car Make : ");                                 
        makeLabel.setHorizontalAlignment(JLabel.CENTER);
        makeField = new JTextField(20);
        modelLabel = new JLabel("Car Model : ");
        modelLabel.setHorizontalAlignment(JLabel.CENTER);
        modelField = new JTextField(20);
        fuelLabel = new JLabel("Fuel Type : ");
        fuelLabel.setHorizontalAlignment(JLabel.CENTER);
        fuelType = new JComboBox<>(fuelStrings);
        fuelType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent choicePicked)
            {
                System.out.println(fuelType.getSelectedItem());                     // Method stub for fuelType
            }
        });
        yearLabel = new JLabel("Car Year : ");
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        yearField = new JTextField(20);
        mileageLabel = new JLabel("Car Mileage : ");
        mileageLabel.setHorizontalAlignment(JLabel.CENTER);
        mileageField = new JTextField(20);
        priceLabel = new JLabel("Car Price : ");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceField = new JTextField(20);
        engineLabel = new JLabel("Enigine Size : ");
        engineLabel.setHorizontalAlignment(JLabel.CENTER);
        engineField = new JTextField(20);



        // Add labels and text fields to the panel
        
        formPanel.setBackground(MarketPlaceGUI.green);
        formPanel.add(makeLabel);
        formPanel.add(makeField);
        formPanel.add(modelLabel);
        formPanel.add(modelField);
        formPanel.add(fuelLabel);
        formPanel.add(fuelType);
        formPanel.add(yearLabel);
        formPanel.add(yearField);
        formPanel.add(mileageLabel);
        formPanel.add(mileageField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(engineLabel);
        formPanel.add(engineField);



        placeAdPanel.add(formPanel);

        add(placeAdPanel);         // Adds the placeAdPanel to the screen
    }
}  
