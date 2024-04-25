public class UsernameNotFoundException extends Exception
{
    public UsernameNotFoundException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
    
}
