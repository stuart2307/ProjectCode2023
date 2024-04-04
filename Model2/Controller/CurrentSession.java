public class CurrentSession 
{
    private static int CurrentUserID = 0;
    private static Boolean isLoggedIn = false;
    
    public CurrentSession() 
        {

        }
    public static void logUserIn(int UserID)
        {
            CurrentUserID = UserID;
            isLoggedIn = true;
        }
    public static void logUserOut()
        {
            CurrentUserID = 0;
            isLoggedIn = false;
        }
    public static Boolean getLoginStatus()
        {
            return isLoggedIn;
        }
    public static int getUserID()
        {
            return CurrentUserID;
        }
}