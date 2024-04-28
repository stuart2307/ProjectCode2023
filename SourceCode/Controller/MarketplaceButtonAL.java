import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MarketplaceButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from;
    public MarketplaceButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent marketClicked) //Changing from the current panel to the marketplace panel and leaves any searches made blank to show all results 
        {
            GUIManager.changeMarketplace(from, "");
        }
}
