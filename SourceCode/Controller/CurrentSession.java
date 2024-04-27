public class CurrentSession 
{
    private static int CurrentUserID = 0;
    private static Boolean isLoggedIn = false;
    
    private CurrentSession() {} // Private constructor, to prevent instantiation
    public static void logUserIn(int UserID)
        {
            CurrentUserID = UserID; //Sets current user id to the given id
            isLoggedIn = true;      //Sets the user as logged in
        }
    public static void logUserOut()
        {
            CurrentUserID = 0;      //Resets the current user id
            isLoggedIn = false;     //Sets the user as logged out
        }
    public static Boolean getLoginStatus()
        {
            return isLoggedIn;      //Returns the current login status of the user
        }
    public static int getUserID()
        {
            return CurrentUserID;   //Returns the current users id, being 0 if user is logged out
        }
}