import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PlaceAdButtonAL implements ActionListener
{
    JPanel from;
    public PlaceAdButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeCreateAd(from);
        }
}
