import java.util.ArrayList;

public class BookShelf
{
    private Book[][] shelves;

    /**
     * Constructs a new bookshelf. If the number of shelves
     * of number of books per shelf is less than 1, the
     * offending value should be set to 1. Then, the
     * the Book[][] shelves declared above should be
     * initialized with default (null) elements and the
     * shape specified. shelves[i] should be a single
     * Book[] with shelfCapacity spaces for books.
     *
     * @param numberOfShelves - the number of shelves
     * @param shelfCapacity - the number of books that can be stored on each shelf
     */
    public BookShelf(int numberOfShelves, int shelfCapacity)
    {

    }

    /**
     * Adds a book to the bookshelf, if there is room.
     * The book should be added on the top-most shelf
     * with an empty slot, in its left-most empty slot.
     * @param book - the book to be added to the shelf
     * @return true if the book was successfully added, false otherwise
     */
    public boolean add(Book book)
    {
        return false; // this is a stub, replace it with a valid solution
    }

    /**
     * Checks if the specified book is on the shelf.
     * @param book - the book to search for.
     * @return true if the specified book is found, false otherwise.
     */
    public boolean searchForBook(Book book)
    {
        return false; // stub
    }

    /**
     * Removes the first copy of the specified book from the shelf.
     * Should check the topmost shelf first, and the left-most book
     * on each shelf first. "Removing" the book means setting the
     * location where it was previously stored to null. Should only
     * remove a single copy; once such a copy is found, the method
     * should return true.
     * @param book - the book to be removed
     * @return the removed book, or null if none was found
     */
    public Book remove(Book book)
    {
        return null; // stub
    }

    /**
     * Finds all books with the specified author with unique ISBNs.
     * Recall, only author's last names are stored.
     * @param author - The author to search for
     * @return - an ArrayList of all unique books written by the specified author
     */
    public ArrayList<Book> searchForAuthor(String author)
    {
        return null; // stub
    }

    /**
     * Find all books with the specified title with unique ISBNs.
     * Very similar to searchForAuthor.
     * @param title - the title to search for
     * @return an ArrayList of all unique books with the specified title.
     */
    public ArrayList<Book> searchForTitle(String title)
    {
        return null; // stub
    }

    /**
     * A pretty toString Method.
     * Don't worry, it's already complete.
     * Try running the BookShelfClient to see its output!
     * @return a String representing the bookshelf
     */
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        final int MAX_STRING_LENGTH = 25;
        for (int i = 0; i < (MAX_STRING_LENGTH + 3) * shelves[0].length + 1; i++)
        {
            result.append('-');
        }
        result.append('\n');
        for (Book[] shelf : shelves)
        {
            result.append("| ");
            for (Book book : shelf)
            {
                String bookString = "";
                if (book != null)
                    bookString = book.getISBN();
                constructBookShelfStringComponent(result, MAX_STRING_LENGTH, bookString);
            }
            result.append("\n");

            result.append("| ");
            for (Book book : shelf)
            {
                String bookString = "";
                if (book != null)
                    bookString = book.getTitle();
                constructBookShelfStringComponent(result, MAX_STRING_LENGTH, bookString);
            }
            result.append("\n");

            result.append("| ");
            for (Book book : shelf)
            {
                String bookString = "";
                if (book != null)
                    bookString = book.getAuthor();
                constructBookShelfStringComponent(result, MAX_STRING_LENGTH, bookString);
            }
            result.append("\n");

            for (int i = 0; i < (MAX_STRING_LENGTH + 3) * shelves[0].length + 1; i++)
            {
                result.append('-');
            }
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * A helper to avoid repeating work in the toString method.
     * You can safely forget that this exists.
     * @param result
     * @param MAX_STRING_LENGTH
     * @param bookString
     */
    private void constructBookShelfStringComponent(StringBuilder result, int MAX_STRING_LENGTH, String bookString)
    {
        if (bookString.length() > MAX_STRING_LENGTH)
        {
            result.append(bookString, 0, MAX_STRING_LENGTH);
        }
        else
        {
            int left = MAX_STRING_LENGTH - bookString.length();
            int right = left / 2;
            left -= right;
            for (int i = 0; i < left; i++)
            {
                result.append(" ");
            }
            result.append(bookString);
            for (int i = 0; i < right; i++)
            {
                result.append(" ");
            }
        }
        result.append(" | ");
    }
}
