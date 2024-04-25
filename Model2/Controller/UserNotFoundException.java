public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    } 
}