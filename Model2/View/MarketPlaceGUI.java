import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MarketPlaceGUI 
{  

    // Declarations

    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel imagePanel;
    private JButton loginButton;
    private JButton signUpButton;

    // Constructor

    public  MarketPlaceGUI()
        { 
            
            frame = new JFrame();                                                                       // Creates a JFrame object called frame

            loginButton = new JButton("Login");                                                    // Creates a JButton instance called loginButton 
            signUpButton = new JButton("Sign Up");                                                 //Creates a JButton instance called signUpButton

            buttonPanel = new JPanel();                                                                       // Creates a JPanel object called panel
            buttonPanel.setPreferredSize(new Dimension(100,40));    
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 1750, 10, 15));    // Creates a border on the panel, Setting the left border creates more space to bring it to the top right, will have to find a better way to do this
            buttonPanel.setLayout(new GridLayout(1, 2));                                            // Changes the layout of the panel to the grid layout, with 0 rows and 1 column
            buttonPanel.add(loginButton);
            buttonPanel.add(signUpButton);

            frame.add(buttonPanel, BorderLayout.NORTH);                                                 // Adds the panel to the frame and centers it in the frame (Frame defaults to the border layout)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                       // Allows us to terminate the program upon closing the frame
            frame.setTitle("MarketPlace");                                                        // Sets title of frame
            frame.pack();                                                                               // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            frame.setVisible(true);                                                                   // Allows frame to actually be visible
        }
}
