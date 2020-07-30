public class InvalidISBNException extends Exception
{
    public InvalidISBNException(String badISBN)
    {
        super("Invalid ISBN \"" + badISBN + "\"");
    }
}
