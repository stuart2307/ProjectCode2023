import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MarketPlaceGUI 
{  

    // Declarations

    private JFrame frame;
    private JPanel topPanel;
    private JPanel loginSignupPanel;
    private JPanel searchBarPanel;
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JButton loginButton;
    private JButton signUpButton;
    private JButton searchButton;
    private JTextField searchField;

    // Constructor

    public  MarketPlaceGUI()
        { 
            
            frame = new JFrame();                                                                       // Creates a JFrame object called frame
            mainPanel = new JPanel(new BorderLayout());                                                 // Creates a JPanel instance called mainPanel                     
            loginButton = new JButton("Login");                                                    // Creates a JButton instance called loginButton
            signUpButton = new JButton("Sign Up");                                                 // Creates a JButton instance called signUpButton
            searchButton = new JButton("Search");                                                  // Creates a JButton instance called searchButton
            searchField = new JTextField(20);                                                   // Creates a JTextField instance called searchField
            
            topPanel = new JPanel(new GridLayout(1, 2));                                                // Creates a top panel using Grid layout (1 row, 2 columns) to hold the button panels

            searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));                                         // Creates a panel using the Flow layout and positions it to the left
            searchBarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));       // Adds some borders to make things look nice
            searchBarPanel.add(searchButton);
            searchBarPanel.add(searchField);
            
            loginSignupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));                                      // Creates a panel using the Flow layout and positions it to the right
            loginSignupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));     // Adds some borders to make things look nice
            loginSignupPanel.add(loginButton);
            loginSignupPanel.add(signUpButton); 

            topPanel.add(searchBarPanel);
            topPanel.add(loginSignupPanel);

            mainPanel.add(topPanel, BorderLayout.NORTH);                                                // Adds the top panel to the main panel using the border layout to position it to the top of the screen

            frame.setMinimumSize(new Dimension(640, 480));                                 // Sets a minimum size for the frame
            frame.add(mainPanel);                                                                       // Adds the panel to the frame and positions it to the centre (Frame defaults to the border layout)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                       // Allows us to terminate the program upon closing the frame
            frame.setTitle("MarketPlace");                                                        // Sets title of frame
            frame.pack();                                                                               // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            frame.setVisible(true);                                                                   // Allows frame to actually be visible
        }
}
