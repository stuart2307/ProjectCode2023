import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdPanel extends JPanel
{
    private JPanel placeAdPanel;
    private JPanel titlePanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel formPanel;
    private JPanel warningPanel;
    private JPanel warning1;
    private JPanel warning2;

    private JLabel pageTitle;
    private JLabel makeLabel;
    private JLabel modelLabel;
    private JLabel fuelLabel;
    private JLabel yearLabel;
    private JLabel mileageLabel;
    private JLabel priceLabel;
    private JLabel engineLabel;
    private JLabel ownersLabel;
    private JLabel descriptionLabel;

    private JLabel blankEntryWarning  = new JLabel("All entries must be filled out");
    private JLabel numberWarning = new JLabel("Only numbers allowed in number fields");

    private boolean blankEntryFlag;
    private boolean yearFlag;
    private boolean mileageFlag;
    private boolean ownerFlag;
    private boolean engineFlag;

    private JComboBox fuelType;
    private String[] fuelStrings = {"Petrol", "Diesel", "Electric", "Hybrid"};
    private String selection;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField mileageField;
    private JTextField priceField;
    private JTextField engineField;
    private JTextField ownersField;
    private JTextField descriptionField;

    private JButton submitButton;
    private JButton returnButton;

    private String detailsArray[] = new String[9];
    private String make;
    private String model;
    private String year;
    private String mileage;
    private String price;
    private String engineSize;
    private String previousOwners;
    private String description;


    public AdPanel()
    {
        placeAdPanel = new JPanel();                                                // Creates a main panel for elements relating to placing an ad
        placeAdPanel.setLayout(new BoxLayout(placeAdPanel, BoxLayout.Y_AXIS));      // Sets the layout of the placeAdPanel to boxLayout along the Y_Axis
        placeAdPanel.setBackground(MarketPlaceGUI.green);

        titlePanel = new JPanel();                                                  // Creates panel for the title
        titlePanel.setLayout(new BorderLayout());                                   // Sets the titlePanel to BorderLayout
        titlePanel.setPreferredSize(new Dimension(660, 220));          // Sets the preferred size of title panel
        titlePanel.setBackground(MarketPlaceGUI.green);                             // Sets the colour of the title panel to green 

        pageTitle = new JLabel("Create Advertisement ");                        // Creates the text for the title of the page 
        pageTitle.setHorizontalAlignment(JLabel.RIGHT);                             // Positions the text to the center of the JLabel
        pageTitle.setFont(MarketPlaceGUI.titleFont);                                // Sets the font, size and text decoration
        pageTitle.setForeground(MarketPlaceGUI.white);                              // Sets title colour to white

        bottomPanel = new JPanel();                                                 // Creates the topPanel
        returnButton = new JButton("Back");                                    // Creates the returnButton
        returnButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent back)
            {
                blankEntryWarning.setVisible(false);
                numberWarning.setVisible(false);
                GUIManager.changeMarketplace(AdPanel.this);                         // Switches to MarketPlaceGUI when clicking the back button
            }
        });
        bottomPanel.setLayout(new GridLayout(1,2, 0, 40));      // Sets bottomPanel to borderLayout

        bottomPanel.setBackground(MarketPlaceGUI.green);                            // Sets the colour of bottomPanel to green

        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        leftPanel.setBackground(MarketPlaceGUI.green);
        leftPanel.add(returnButton);

        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(MarketPlaceGUI.green);
        rightPanel.add(pageTitle);

        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        titlePanel.add(bottomPanel, BorderLayout.SOUTH);                            // Adds the bottomPanel to the South region of titlePanel using borderLayout
        
        placeAdPanel.add(titlePanel);                                               // Adds title panel to the placeAdPanel

        formPanel = new JPanel(new GridLayout(9, 2, 0, 20));    // Creates a form panel using grid layout with two columns
        formPanel.setBackground(MarketPlaceGUI.green);                              // Sets colour of formPanel
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));     // Sets an empty top and bottom border to formpanel

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
                selection = (String) fuelType.getSelectedItem();                     // Method for selecting fuelType when using combobox
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
        engineLabel = new JLabel("Engine Size : ");
        engineLabel.setHorizontalAlignment(JLabel.CENTER);
        engineField = new JTextField(20);
        ownersLabel = new JLabel("Previous Owners : ");
        ownersLabel.setHorizontalAlignment(JLabel.CENTER);
        ownersField = new JTextField(20);
        descriptionLabel = new JLabel("Description : ");
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionField = new JTextField(20);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent submit)
            {
                // Getting input from form

                make = makeField.getText();
                model = modelField.getText();
                year = yearField.getText();
                mileage = mileageField.getText();
                price = priceField.getText();
                engineSize = engineField.getText();
                previousOwners = ownersField.getText();
                description = descriptionField.getText();

                // Adding details to array

                detailsArray[0] = make;
                detailsArray[1] = model;
                detailsArray[2] = selection;
                detailsArray[3] = year;
                detailsArray[4] = mileage;
                detailsArray[5] = price;
                detailsArray[6] = engineSize;
                detailsArray[7] = previousOwners;
                detailsArray[8] = description;

                try 
                {
                    Verifiers.VerifyEntries(detailsArray);
                    if(blankEntryFlag==true)
                    {
                        blankEntryWarning.setVisible(false);
                        blankEntryFlag = false;
                    }

                }
                catch(BlankEntryException blankEntryException)
                {
                    blankEntryFlag = true;
                    blankEntryWarning.setVisible(true);
                    // blankEntryException.printStackTrace();
                }
                finally
                {
                    blankEntryWarning.revalidate();
                    blankEntryWarning.repaint();
                }

                try
                {
                    Verifiers.VerifyInt(year);
                    if(yearFlag==true)
                    {
                        numberWarning.setVisible(false);
                        yearFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {
                    yearFlag = true;
                    numberWarning.setVisible(true);
                    // IntException.printStackTrace();
                }
                finally
                {
                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {
                    Verifiers.VerifyInt(mileage);
                    if(mileageFlag==true)
                    {
                        numberWarning.setVisible(false);
                        mileageFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {
                    mileageFlag = true;
                    numberWarning.setVisible(true);
                    // IntException.printStackTrace();
                }
                finally
                {
                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {
                    Verifiers.VerifyInt(previousOwners);
                    if(ownerFlag==true)
                    {
                        numberWarning.setVisible(false);
                        ownerFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {
                    ownerFlag = true;
                    numberWarning.setVisible(true);
                    // IntException.printStackTrace();
                }
                finally
                {
                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {
                    Verifiers.VerifyDouble(engineSize);
                    if(engineFlag==true)
                    {
                        numberWarning.setVisible(false);
                        engineFlag = false;
                    }
                }
                catch(InputMismatchException DoubleException)
                {
                    engineFlag = true;
                    numberWarning.setVisible(true);
                    // DoubleException.printStackTrace();
                }
                finally
                {
                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

            }
        });

        warningPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        warningPanel.setBackground(MarketPlaceGUI.green);
        numberWarning.setVisible(false);
        blankEntryWarning.setVisible(false);
        warning1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        warning1.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
        warning1.setBackground(MarketPlaceGUI.green);
        warning2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        warning2.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        warning2.setBackground(MarketPlaceGUI.green);
        warning1.add(blankEntryWarning);
        warning2.add(numberWarning);
        warningPanel.add(warning1);
        warningPanel.add(warning2);

        // Add labels and text fields to the panel
        
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
        formPanel.add(ownersLabel);
        formPanel.add(ownersField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);

        // Adding elements to the PlaceAdPanel

        placeAdPanel.add(formPanel);
        placeAdPanel.add(submitButton);
        placeAdPanel.add(warningPanel);

        // Adding elements to the main AdPanel

        setBackground(MarketPlaceGUI.green);
        add(placeAdPanel);                                                          // Adds the placeAdPanel to the screen
    }
}  
