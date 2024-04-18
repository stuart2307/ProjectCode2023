import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class marketplaceButtonAL implements ActionListener
{
    JPanel from;
    public marketplaceButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeMarketplace(from, "");
        }
}
