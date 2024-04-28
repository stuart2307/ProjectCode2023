import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PlaceAdButtonAL implements ActionListener //Implementing the ActionListener class
{
    private JPanel from;
    public PlaceAdButtonAL(JPanel switchFrom) //Taking in the current panel as a parameter
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent placeAdClicked) //Changing from the current panel to the panel for creating an ad
        {
            GUIManager.changeCreateAd(from);
        }
}
