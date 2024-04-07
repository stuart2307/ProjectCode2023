import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIManager 
{
    private static JFrame frame = new JFrame("Crocodeal");  //Creates the frame to hold all the panels
    private static JPanel lastScreen;
    //INSTANTIATES AND INITIALISES ALL SCREENS
    private static MarketPlaceGUI marketplace;
    private static Login login;
    private static SignUp signup;
    private static AdPanel createAd;
    private static ViewAccount viewAccount;
    private static EditAccount editAccount;
    private static ViewAd viewAd;

    public static void prepareManager()
        {
            if (DatabaseManager.connection == null)
                {
                    NoConnection errorPanel = new NoConnection();
                    frame.add(errorPanel, "Center");
                }
            else
                {
                    marketplace = new MarketPlaceGUI();
                    login = new Login();
                    signup = new SignUp();
                    createAd = new AdPanel();
                    viewAccount = new ViewAccount();
                    editAccount = new EditAccount();
                    viewAd = new ViewAd();
                    frame.add(marketplace);   //Adds the marketplace as the default panel
                }     
            frame.setMinimumSize(new Dimension(640, 480));          //Sets a minimum size for the JFrame
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            marketplace.generateAds();
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
            viewAccount.populatePage();
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen = switchFrom;
            viewAccount.setVisible(true);
            frame.add(viewAccount);
        }
        public static void changeEditAccount(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen = switchFrom;
            editAccount.setVisible(true);
            frame.add(editAccount);
        }
        public static void changeViewAd(JPanel switchFrom, int adId)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            lastScreen = switchFrom;
            viewAd.populateScreen(adId);
            viewAd.setVisible(true);
            frame.add(viewAd);
        }
        public static void backButton(JPanel switchFrom)
        {
            switchFrom.setVisible(false);
            frame.remove(switchFrom);
            if (lastScreen == marketplace)
                {
                    marketplace.generateAds();
                }
            lastScreen.setVisible(true);
            frame.add(lastScreen);
        }
        public static void loggedIn()
        {
            marketplace.loginSignupPanel.remove(marketplace.signUpButton);
            marketplace.loginSignupPanel.remove(marketplace.loginButton);
            marketplace.loginSignupPanel.add(marketplace.placeAdButton);
            marketplace.loginSignupPanel.add(marketplace.accountButton);
            marketplace.loginSignupPanel.add(marketplace.logoutButton);
        }
        public static void loggedOut()
        {
            marketplace.loginSignupPanel.remove(marketplace.logoutButton);
            marketplace.loginSignupPanel.remove(marketplace.accountButton);
            marketplace.loginSignupPanel.remove(marketplace.placeAdButton);
            marketplace.loginSignupPanel.add(marketplace.signUpButton);
            marketplace.loginSignupPanel.add(marketplace.loginButton);
        }
}
