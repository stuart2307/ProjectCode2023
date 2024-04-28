public class WrongPasswordException extends Exception
{
    public WrongPasswordException(String msg) //Creating an exception specifically for blank entries
    { 
        super(msg); //invoking the constructor of the superclass
    }
}
