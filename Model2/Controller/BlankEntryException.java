public class BlankEntryException extends Exception 
{
    public BlankEntryException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
}
