import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MarketplaceButtonAL implements ActionListener
{
    JPanel from;
    public MarketplaceButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeMarketplace(from, "");
        }
}
