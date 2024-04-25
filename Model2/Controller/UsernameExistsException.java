public class UsernameExistsException extends Exception {
    public UsernameExistsException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
}
