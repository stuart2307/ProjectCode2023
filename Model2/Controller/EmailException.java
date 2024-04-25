public class EmailException extends Exception {
    public EmailException(String msg) //Creating an exception specifically for blank entries
    {
        super(msg); //invoking the constructor of the superclass
    }
}