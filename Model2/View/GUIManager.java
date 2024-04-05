import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIManager 
{
    private static JFrame frame = new JFrame("Crocodeal");  //Creates the frame to hold all the panels
    private static JPanel lastScreen;
    //INSTANTIATES AND INITIALISES ALL SCREENS
    private static MarketPlaceGUI marketplace = new MarketPlaceGUI();
    private static Login login = new Login();
    private static SignUp signup = new SignUp();
    private static AdPanel createAd = new AdPanel();
    private static ViewAccount viewAccount = new ViewAccount();
    private static ViewAd viewAd = new ViewAd();

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
            lastScreen = switchFrom;
            marketplace.setVisible(true);                           //Sets the marketplace's panel to be visible
            frame.add(marketplace);                                 //Adds the marketplace's panel to the Frame
        }
        public static void changeLogin(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            lastScreen = switchFrom;
            login.setVisible(true);                                 //Sets the login screen's panel to be visible
            frame.add(login);                                       //Adds the login screen's panel to the Frame
        }
        public static void changeSignup(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            lastScreen = switchFrom;
            signup.setVisible(true);                                //Sets the sign up screen's panel to be visible
            frame.add(signup);                                      //Adds the sign up screen's panel to the Frame
        }
        public static void changeCreateAd(JPanel switchFrom)
        {
            switchFrom.setVisible(false);                           //Sets the current screen's panel to be invisible
            frame.remove(switchFrom);                               //Removes the current screen's panel from the frame
            lastScreen = switchFrom;
            createAd.setVisible(true);                              //Sets the ad creation panel to be visible
            frame.add(createAd);                                    //Adds the ad creation panel to the Frame
        }
        public static void changeAccount(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen = switchFrom;
            viewAccount.setVisible(true);
            frame.add(viewAccount);
        }
        public static void changeViewAd(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen = switchFrom;
            viewAd.setVisible(true);
            frame.add(viewAd);
        }
        public static void goBack(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen.setVisible(true);
            frame.add(lastScreen);
        }
        public static void loggedIn()
        {
            marketplace.signUpButton.setVisible(false);
            marketplace.loginSignupPanel.remove(marketplace.signUpButton);
            marketplace.loginButton.setVisible(false);
            marketplace.loginSignupPanel.remove(marketplace.loginButton);
            marketplace.placeAdButton.setVisible(true);
            marketplace.loginSignupPanel.add(marketplace.placeAdButton);
            marketplace.accountButton.setVisible(true);
            marketplace.loginSignupPanel.add(marketplace.accountButton);
            marketplace.logoutButton.setVisible(true);
            marketplace.loginSignupPanel.add(marketplace.logoutButton);
        }
        public static void loggedOut()
        {
            marketplace.logoutButton.setVisible(false);
            marketplace.loginSignupPanel.remove(marketplace.logoutButton);
            marketplace.accountButton.setVisible(false);
            marketplace.loginSignupPanel.remove(marketplace.accountButton);
            marketplace.placeAdButton.setVisible(false);
            marketplace.loginSignupPanel.remove(marketplace.placeAdButton);
            marketplace.signUpButton.setVisible(true);
            marketplace.loginSignupPanel.add(marketplace.signUpButton);
            marketplace.loginButton.setVisible(true);
            marketplace.loginSignupPanel.add(marketplace.loginButton);
        }
}
