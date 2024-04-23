import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

//
// Class Name : AdPanel
//
// Author     : Diarmuid O'Neill
//
// Purpose    : This GUI provides the user with a form like interface to allow them to create a new advertisement.
//

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
    private JLabel transmissionLabel;
    private JLabel yearLabel;
    private JLabel mileageLabel;
    private JLabel priceLabel;
    private JLabel engineLabel;
    private JLabel ownersLabel;
    private JLabel descriptionLabel;
    private JLabel uploadImageLabel;
    private JButton uploadImage;

    private JLabel blankEntryWarning  = new JLabel("All entries must be filled out");
    private JLabel numberWarning = new JLabel("Only numbers allowed in number fields");

    private boolean blankEntryFlag;
    private boolean yearFlag;
    private boolean mileageFlag;
    private boolean ownerFlag;
    private boolean engineFlag;

    private JComboBox fuelType;
    private JComboBox transmissionType;
    private String[] fuelStrings = {"Petrol", "Diesel", "Electric", "Hybrid"};
    private String[] transmissionStrings = {"Manual", "Automatic"};
    private String fuelSelection;
    private String transmissionSelection;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField mileageField;
    private JTextField priceField;
    private JTextField engineField;
    private JTextField ownersField;
    private JTextArea descriptionField;

    private JButton submitButton;
    private JButton returnButton;

    private String detailsArray[] = new String[12];
    private String reviewDetailsArray[] = new String[4];
    private String make;
    private String model;
    private String year;
    private String mileage;
    private String price;
    private String engineSize;
    private String previousOwners;
    private String description;
    private String filePath;


    public AdPanel()
    {
        placeAdPanel = new JPanel();                                                            // Creates a main panel for elements relating to placing an ad
        placeAdPanel.setLayout(new BoxLayout(placeAdPanel, BoxLayout.Y_AXIS));                  // Sets the layout of the placeAdPanel to boxLayout along the Y_Axis
        placeAdPanel.setBackground(MarketPlaceGUI.green);                                       // Sets background colour to green

        titlePanel = new JPanel();                                                              // Creates panel for the title
        titlePanel.setLayout(new BorderLayout());                                               // Sets the titlePanel to BorderLayout
        titlePanel.setPreferredSize(new Dimension(660, 100));                      // Sets the preferred size of title panel
        titlePanel.setBackground(MarketPlaceGUI.green);                                         // Sets the colour of the title panel to green 

        pageTitle = new JLabel("Create Advertisement ");                                   // Creates the text for the title of the page 
        pageTitle.setHorizontalAlignment(JLabel.RIGHT);                                         // Positions the text to the center of the JLabel
        pageTitle.setFont(MarketPlaceGUI.titleFont);                                            // Sets the font, size and text decoration
        pageTitle.setForeground(MarketPlaceGUI.white);                                          // Sets title colour to white

        bottomPanel = new JPanel();                                                             // Creates the bottomPanel
        returnButton = new JButton("Back");                                                // Creates the returnButton
        returnButton.addActionListener(new ActionListener() 
        {

            // This anonymous inner class allows the user to return to the marketplace

            public void actionPerformed(ActionEvent back)
            {
                blankEntryWarning.setVisible(false);                                     // Resets error message
                numberWarning.setVisible(false);                                         // Resets error message
                GUIManager.changeMarketplace(AdPanel.this, "");                         // Switches to MarketPlaceGUI when clicking the back button
            }
        });
        bottomPanel.setLayout(new GridLayout(1,2, 0, 40));                 // Sets bottomPanel to grid layout (1 row 2 columns)

        bottomPanel.setBackground(MarketPlaceGUI.green);                                       // Sets the colour of bottomPanel to green
 
        leftPanel = new JPanel();                                                              // Creates a new JPanel called leftpanel
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));                                // Sets it to flow layout center
        leftPanel.setBackground(MarketPlaceGUI.green);                                         // Sets the background colour to green
        leftPanel.add(returnButton);                                                           // Adds the return button to the left panel

        rightPanel = new JPanel();                                                             // Creates a new JPanel called rightPanel
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));                                // Sets it to be flow layout right
        rightPanel.setBackground(MarketPlaceGUI.green);                                        // Sets its background colour to be green
        rightPanel.add(pageTitle);                                                             // Adds the page title to this panel
 
        bottomPanel.add(leftPanel);                                                            // The right and left panels are added to the bottom panel in order to align the 
        bottomPanel.add(rightPanel);                                                           // title of the page and the back button in the correct areas on the screen

        titlePanel.add(bottomPanel, BorderLayout.SOUTH);                                              // Adds the bottomPanel to the South region of titlePanel using borderLayout
        
        placeAdPanel.add(titlePanel);                                                                 // Adds title panel to the placeAdPanel

        formPanel = new JPanel(new GridLayout(11, 2, 0, 20));                     // Creates a form panel using grid layout with two columns
        formPanel.setBackground(MarketPlaceGUI.green);                                                // Sets colour of formPanel
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
                fuelSelection = (String) fuelType.getSelectedItem();                            // Method for selecting fuelType when using combobox
            }
        });
        transmissionLabel = new JLabel("Transmission Type : ");
        transmissionLabel.setHorizontalAlignment(JLabel.CENTER);
        transmissionType = new JComboBox<>(transmissionStrings);
        transmissionType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent choicePicked)
            {
                transmissionSelection = (String) transmissionType.getSelectedItem();            // Method for selecting transmissionType when using combobox
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
        descriptionField = new JTextArea();
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setPreferredSize(new Dimension(30, 10));
        uploadImageLabel = new JLabel("Upload Image : ");
        uploadImageLabel.setHorizontalAlignment(JLabel.CENTER);
        uploadImage = new JButton("Upload Image");
        uploadImage.addActionListener(new ActionListener() 
        {
 
            // This anonymous inner class is for uploading an image using a file selector

            public void actionPerformed(ActionEvent selectFile)
                {
                    LookAndFeel laf = UIManager.getLookAndFeel();
                    try
                        {
                          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
                        }
                    catch (Exception e)
                        {
                            System.out.println("oops");
                        }
                    JFileChooser getFile = new JFileChooser();
                    getFile.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
                    if (getFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                            filePath = getFile.getSelectedFile().getAbsolutePath();
                        }
                    try
                        {
                            UIManager.setLookAndFeel(laf);
                        }
                    catch (UnsupportedLookAndFeelException ulafe){}
                }
        });
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

                detailsArray[0] = "" + CurrentSession.getUserID();
                detailsArray[1] = make;
                detailsArray[2] = model;
                detailsArray[3] = fuelSelection;
                detailsArray[4] = transmissionSelection;
                detailsArray[5] = year;
                detailsArray[6] = mileage;
                detailsArray[7] = price;
                detailsArray[8] = engineSize;
                detailsArray[9] = previousOwners;
                detailsArray[10] = description;
                detailsArray[11] = filePath;

                try 
                {

                    // Checks to make sure there are no blank entries

                    Verifiers.VerifyEntries(detailsArray);
                    if(blankEntryFlag==true)
                    {
                        blankEntryWarning.setVisible(false);
                        blankEntryFlag = false;
                    }

                }
                catch(BlankEntryException blankEntryException)
                {

                    // Display warning message

                    blankEntryFlag = true;
                    blankEntryWarning.setVisible(true);
                    blankEntryException.printStackTrace();
                }
                finally
                {
 
                    // Refreshes warning message

                    blankEntryWarning.revalidate();
                    blankEntryWarning.repaint();
                }

                try
                {

                    // Checks that the entered year is an int 

                    Verifiers.VerifyInt(year);
                    if(yearFlag==true)
                    {
                        numberWarning.setVisible(false);
                        yearFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {

                    // Displays warning message

                    yearFlag = true;
                    numberWarning.setVisible(true);
                    IntException.printStackTrace();
                }
                finally
                {

                    // Refreshes warning message

                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {

                    // Checks that the entered mileage is an int 

                    Verifiers.VerifyInt(mileage);
                    if(mileageFlag==true)
                    {
                        numberWarning.setVisible(false);
                        mileageFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {

                    // Display warning message 

                    mileageFlag = true;
                    numberWarning.setVisible(true);
                    IntException.printStackTrace();
                }
                finally
                {

                    // Refreshes warning message

                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {

                    // Checks the entered number of previous owners is an int

                    Verifiers.VerifyInt(previousOwners);
                    if(ownerFlag==true)
                    {
                        numberWarning.setVisible(false);
                        ownerFlag = false;
                    }
                }
                catch(InputMismatchException IntException)
                {

                    // Displays a warning message 

                    ownerFlag = true;
                    numberWarning.setVisible(true);
                    IntException.printStackTrace();
                }
                finally
                {

                    // Refreshes warning message

                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                try
                {

                    // Checks to make sure the entered engine size is a double

                    Verifiers.VerifyDouble(engineSize);
                    if(engineFlag==true)
                    {
                        numberWarning.setVisible(false);
                        engineFlag = false;
                    }
                }
                catch(InputMismatchException DoubleException)
                {

                    // Displays warning message

                    engineFlag = true;
                    numberWarning.setVisible(true);
                    DoubleException.printStackTrace();
                }
                finally
                {

                    // Refreshes warning message

                    numberWarning.revalidate();
                    numberWarning.repaint();
                }

                if (blankEntryFlag || yearFlag || mileageFlag || ownerFlag || engineFlag)
                {

                    // If any error was encountered print this message to the console

                    System.out.println("oops, account not created!");
                }
                else
                {

                    // If no errors were encountered then create an advertisement record

                    DatabaseManager.createEntry("advertisements", DatabaseManager.ADVERTISEMENTS, detailsArray);

                    try 
                    {

                        // SQL statement to retrieve advertisementID of the ad we just created

                        ResultSet results = DatabaseManager.executeQuery(new String[]{"AdvertisementID"}, "advertisements", "", "", "DESC", "AdvertisementID");

                        results.next();

                        // Upon creating an ad successfully the user is taken to the ad they just created

                        GUIManager.changeViewAd(AdPanel.this, results.getInt(1));
                    }
                    catch (SQLException e)
                    {

                        // Catch the potential SQL exception

                        System.err.println("Error processing sql results...");
                    }
                }
            }
        });

        warningPanel = new JPanel(new GridLayout(2, 1, 10, 10));                    // Creates a warningPanel using grid layout (2 rows 1 column)
        warningPanel.setBackground(MarketPlaceGUI.green);                                               // Sets background to green
        numberWarning.setVisible(false);                                                          // Sets warnings to be invisible at the start
        blankEntryWarning.setVisible(false);
        warning1 = new JPanel(new FlowLayout(FlowLayout.CENTER));                                       // Creates and lays out individual warnings 
        warning1.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        warning1.setBackground(MarketPlaceGUI.green);
        warning2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        warning2.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        warning2.setBackground(MarketPlaceGUI.green);
        warning1.add(blankEntryWarning);                                                                // Adds warnings to warning panels
        warning2.add(numberWarning);
        warningPanel.add(warning1);                                                                     // Adds individual warning panels to the main warning panel
        warningPanel.add(warning2);

        // Add labels and text fields to the panel
        
        formPanel.add(makeLabel);
        formPanel.add(makeField);
        formPanel.add(modelLabel);
        formPanel.add(modelField);
        formPanel.add(fuelLabel);
        formPanel.add(fuelType);
        formPanel.add(transmissionLabel);
        formPanel.add(transmissionType);
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
        formPanel.add(descriptionField);
        formPanel.add(uploadImageLabel);
        formPanel.add(uploadImage);

        // Adding elements to the PlaceAdPanel

        placeAdPanel.add(formPanel);
        placeAdPanel.add(submitButton);
        placeAdPanel.add(warningPanel);

        // Adding elements to the main AdPanel

        setBackground(MarketPlaceGUI.green);
        add(placeAdPanel);                                                          // Adds the placeAdPanel to the screen
    }
}  
