import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIManager 
{
    private static JFrame frame = new JFrame("Crocodeal");  //Creates the frame to hold all the panels
    //INSTANTIATES AND INITIALISES ALL SCREENS
    private static MarketPlaceGUI marketplace = new MarketPlaceGUI();
    private static AccountPage accountpage = new AccountPage();
    private static Login login = new Login();
    private static SignUp signup = new SignUp();
    private static AdPanel createad = new AdPanel();

    public static void prepareManager()
        {
            frame.add(marketplace);                                 //Adds the marketplace as the default panel
            frame.setMinimumSize(new Dimension(640, 480));          //Sets a minimum size for the JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Makes it so that the program will terminate upon closing the frame
            frame.setTitle("Crocodeal");                            //Sets the frame's title to Crocodeal
            frame.pack();                                           //Sets the frame to adjust to the size of its components
            frame.setVisible(true);                                 //Makes the frame visible
        }
        

    public static void changeMarketplace(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            marketplace.setVisible(true);                           //Sets the marketplace's panel to be visible
            frame.add(marketplace);                                 //Adds the marketplace's panel to the Frame
        }
        public static void changeLogin(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            login.setVisible(true);                                 //Sets the login screen's panel to be visible
            frame.add(login);                                       //Adds the login screen's panel to the Frame
        }
        public static void changeSignup(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            signup.setVisible(true);                                //Sets the sign up screen's panel to be visible
            frame.add(signup);                                      //Adds the sign up screen's panel to the Frame
        }
        public static void changeCreateAd(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            createad.setVisible(true);                              //Sets the ad creation panel to be visible
            frame.add(createad);                                    //Adds the ad creation panel to the Frame
        }
        public static void changeAccount(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            accountpage.setVisible(true);                           //Sets the account view's panel to be visible
            frame.add(accountpage);                                 //Adds the account view's panel to the Frame
        }
}
