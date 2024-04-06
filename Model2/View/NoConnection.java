import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoConnection extends JPanel
{
    private JLabel error = new JLabel("No Connection :(");
    public NoConnection()
        {
            setBackground(MarketPlaceGUI.green);
            setLayout(new BorderLayout());
            error.setFont(MarketPlaceGUI.titleFont);
            add(error, BorderLayout.CENTER);
        }
}