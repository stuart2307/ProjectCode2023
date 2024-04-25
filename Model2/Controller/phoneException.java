public class phoneException extends Exception {
    public phoneException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
}
