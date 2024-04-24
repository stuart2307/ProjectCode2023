import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NoConnection extends JPanel
{
    private JLabel error = new JLabel("No Connection :(");
    public NoConnection()
        {
            setBackground(MarketPlaceGUI.green);
            setLayout(new BorderLayout());
            error.setFont(MarketPlaceGUI.titleFont);
            error.setHorizontalAlignment(SwingConstants.CENTER);
            add(error, BorderLayout.CENTER);
        }
}