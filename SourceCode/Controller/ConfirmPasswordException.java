public class ConfirmPasswordException extends Exception 
{
    public ConfirmPasswordException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
}
