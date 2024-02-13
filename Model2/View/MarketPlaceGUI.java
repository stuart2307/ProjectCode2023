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
    private JPanel panel;
    private JButton button;

    // Constructer

    public  MarketPlaceGUI()
        { 
            
            frame = new JFrame();                                                                       // Creates a JFrame object called frame

            button = new JButton("Login");                                                         // Creates a JButton object called 
            button.setMaximumSize(new Dimension(100, 50));                                 // Set maximum size (not working when in full screen)

            panel = new JPanel();                                                                       // Creates a JPanel object called panel
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));     // Creates a border on the panel
            panel.setLayout(new GridLayout(0, 1));                                            // Changes the layout of the panel to the grid layout, with 0 rows and 1 column
            panel.add(button);                                                                          // Adds button to panel


            frame.add(panel, BorderLayout.CENTER);                                                      // Adds the panel to the frame and centers it in the frame (Frame defaults to the border layout)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                       // Allows us to terminate the program upon closing the frame
            frame.setTitle("MarketPlace");                                                        // Sets title of frame
            frame.pack();                                                                               // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            frame.setVisible(true);                                                                   // Allows frame to actually be visible
        }
}
