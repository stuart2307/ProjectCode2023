import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GridBagLayout;
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
    private JPanel loginFieldPanel;
    private Font titleFont = new Font("Arial", Font.BOLD, 30);
    private JLabel title = new JLabel("Crocodeal");

    private JLabel username = new JLabel("Username");
    private JLabel password = new JLabel("Password");
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Color green = new Color(44,238,144);                                                // Primary menu colour
    public Color white = new Color(255,255,255);                                               // Title text colour
    public Color grey = new Color(220,220,220);                                                // Primary background colour

    public Login()
        {
            loginFrame = new JFrame("Log In");
            loginPanel = new JPanel(new GridBagLayout());
            loginFieldPanel = new JPanel(new FlowLayout(0, 800, 20));
            logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            barPanel = new JPanel(new GridLayout(1, 3));

            usernameField = new JTextField(30);
            passwordField = new JPasswordField(30);

            loginFieldPanel.add(username);
            loginFieldPanel.add(usernameField);
            loginFieldPanel.add(password);
            loginFieldPanel.add(passwordField);

            loginPanel.setBackground(grey);
            title.setFont(titleFont);
            title.setForeground(white);
            logoPanel.setBackground(green);
            logoPanel.add(title, BorderLayout.CENTER);
            logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            barPanel.add(logoPanel);

            loginPanel.add(loginFieldPanel, BorderLayout.CENTER);

            loginPanel.add(logoPanel, BorderLayout.NORTH);
            loginFrame.add(loginPanel);
            loginFrame.setMinimumSize(new Dimension(640, 480)); // Sets a minimum size for the frame
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            loginFrame.setVisible(true); //Makes the frame visible
        }
}
