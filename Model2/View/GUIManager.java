import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIManager 
{
    private static JFrame frame = new JFrame("Crocodeal");
    private static MarketPlaceGUI marketplace = new MarketPlaceGUI();
    private static AccountPage accountpage = new AccountPage();
    private static Login login = new Login();
    private static SignUp signup = new SignUp();
    private static AdPanel createad = new AdPanel();

    public static void prepareManager()
        {
            frame.add(marketplace);
            frame.setMinimumSize(new Dimension(640, 480));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("MarketPlace");
            frame.pack();
            frame.setVisible(true); 
        }
        

    public static void changeMarketplace(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            marketplace.setVisible(true);
            frame.add(marketplace);
        }
        public static void changeLogin(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            login.setVisible(true);
            frame.add(login);
        }
        public static void changeSignup(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            signup.setVisible(true);
            frame.add(signup);
        }
        public static void changeCreateAd(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            createad.setVisible(true);
            frame.add(createad);
        }
        public static void changeAccount(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            accountpage.setVisible(true);
            frame.add(accountpage);
        }
}
