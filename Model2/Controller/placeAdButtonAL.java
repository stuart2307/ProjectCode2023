import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class placeAdButtonAL implements ActionListener
{
    JPanel from;
    public placeAdButtonAL(JPanel switchFrom)
        {
            from = switchFrom;
        }   
    public void actionPerformed(ActionEvent market)
        {
            GUIManager.changeCreateAd(from);
        }
}
