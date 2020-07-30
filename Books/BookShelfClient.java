import java.util.Random;

public class BookShelfClient
{
    public static void main(String[] args) throws InvalidISBNException
    {
        // some inputs to make Books with
        String[][] bookConstructorInputs = {
                {
                        "An Introduction to the Analysis of Algorithms",
                        "Michael Soltys",
                        "981323590X"
                },
                {
                        "Harry Potter and the Half-Blood Prince",
                        "J.K. Rowling",
                        "0439784549"
                },
                {
                        "The Left Hand of Darkness",
                        "Ursula K. Le Guin",
                        "0441478123"
                },
                {
                        "So Long, and Thanks for All the Fish",
                        "Douglas Adams",
                        "0345391837"
                },
                {
                        "The Gates of Rome",
                        "Conn Iggulden",
                        "0385343019"
                }
        };

        // make the books, store in an array
        Book[] books = new Book[bookConstructorInputs.length];
        for (int i = 0; i < bookConstructorInputs.length; i++)
        {
            books[i] = new Book(
                    bookConstructorInputs[i][0],
                    bookConstructorInputs[i][1],
                    bookConstructorInputs[i][2]
            );
        }

        // create a bookshelf with 5 shelves, each of which can hold 3 books
        BookShelf bookShelf = new BookShelf(5, 3);

        // add 15 books to the bookshelf, each selected randomly from the books array
        Random generator = new Random();
        for (int i = 0; i < 15; i++)
        {
            int bookIndex = generator.nextInt(books.length);
            bookShelf.add(books[bookIndex]);
        }

        // display the bookshelf
        System.out.println(bookShelf);
    }
}
