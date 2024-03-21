import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Login 
{
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JPanel logoPanel;
    private JPanel barPanel;
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour

    public Login()
        {
            loginFrame = new JFrame("Log In");
            loginPanel = new JPanel(new BorderLayout());
            logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            barPanel = new JPanel(new GridLayout(1, 3));

            loginPanel.setBackground(grey);
            title.setFont(titleFont);
            title.setForeground(white);
            logoPanel.setBackground(green);
            logoPanel.add(title, BorderLayout.CENTER);
            logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            barPanel.add(logoPanel);
            loginPanel.add(logoPanel, BorderLayout.NORTH);
            loginFrame.add(loginPanel);
            loginFrame.setMinimumSize(new Dimension(640, 480)); // Sets a minimum size for the frame
            loginFrame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            loginFrame.setVisible(true); //Makes the frame visible
        }
}
