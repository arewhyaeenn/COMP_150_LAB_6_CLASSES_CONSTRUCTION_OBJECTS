public class BookClient
{
    public static void main(String[] args) throws Exception
    {
        testConstructionAndToString();

        testBadISBNS();

        testAccessors();

        testComparisonMethods();
    }

    private static void testConstructionAndToString() throws Exception
    {
        System.out.println("*****************************************************************************************************");
        System.out.println("************************ TESTING CONSTRUCTOR (with valid ISBNs) AND TOSTRING ************************");
        System.out.println("*****************************************************************************************************");
        System.out.println("These constructions should work; if errors are thrown, the Book class needs to be fixed.");
        System.out.println("toString outputs should match the expected outputs EXACTLY.");
        System.out.println("Discrepancies may be caused either in construction or in toString.");
        System.out.println("The constructor and toString should be fully debugged before moving on to later tests.");

        String[][] validConstructorInputs = {
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

        for (String[] input : validConstructorInputs)
        {
            System.out.println();
            Book book;
            try
            {
                book = new Book(
                        input[0],
                        input[1],
                        input[2]
                );
            } catch (Exception E)
            {
                constructorError(input);
                throw E;
            }

            System.out.println("\tExpected toString output > " + input[2]+ " : \"" +
                    input[0] + "\" by " + processName(input[1]));
            System.out.println("\tActual   toString output > " + book);
        }
        System.out.println();
    }

    private static void testBadISBNS() throws Exception
    {
        System.out.println("*****************************************************************************************************");
        System.out.println("****************************** TESTING CONSTRUCTOR WITH INVALID ISBNS *******************************");
        System.out.println("*****************************************************************************************************");
        System.out.println("Problems here are caused by incorrect handling of bad ISBNs.");
        System.out.println("Bad ISBN inputs should cause an InvalidISBNException to be thrown.");
        System.out.println("This exception will be caught if it is thrown; they are correct.");
        System.out.println("If other exceptions are thrown, you will be informed and the error will be allowed to occur.");

        String[][] invalidConstructorInputs = {
                {
                        "An Introduction to the Analysis of Algorithms",
                        "Michael Soltys",
                        "981323590X4"
                },
                {
                        "Harry Potter and the Half-Blood Prince",
                        "J.K. Rowling",
                        "043978454"
                },
                {
                        "The Left Hand of Darkness",
                        "Ursula K. Le Guin",
                        "0asdf312121"
                },
                {
                        "So Long, and Thanks for All the Fish",
                        "Douglas Adams",
                        "0345391832"
                },
                {
                        "The Gates of Rome",
                        "Conn Iggulden",
                        "0385343016"
                }
        };
        for (String[] input : invalidConstructorInputs)
        {
            System.out.println();
            Book book;
            boolean success = false;
            try
            {
                book = new Book(
                        input[0],
                        input[1],
                        input[2]
                );
            } catch (Exception E)
            {
                if (E.getClass() == InvalidISBNException.class)
                {
                    System.out.println("\tInvalid ISBN \"" + input[2] + "\" caught. CORRECT!");
                    success = true;
                }
                else
                {
                    constructorError(input);
                    throw E;
                }
            }
            if (!success)
            {
                System.out.println("\tInvalid ISBN \"" + input[2] + "\" let through. INCORRECT!");
            }
        }
        System.out.println();
    }

    private static void testAccessors() throws Exception
    {
        System.out.println("*****************************************************************************************************");
        System.out.println("************************** TESTING ACCESSORS (getTitle, getAuthor, getISBN) *************************");
        System.out.println("*****************************************************************************************************");
        System.out.println("Outputs should match EXACTLY.");
        System.out.println("Discrepancies can be caused by the accessors themselves.");
        System.out.println("Discrepancies can also be caused by the values assigned during construction.");
        System.out.println("If an exception occurs, you will be informed which book and which method led to it,");
        System.out.println("then the exception itself will be thrown.");

        String[][] validConstructorInputs = {
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

        for (String[] input : validConstructorInputs)
        {
            System.out.println();
            Book book;
            try
            {
                book = new Book(
                        input[0],
                        input[1],
                        input[2]
                );
            } catch (Exception E)
            {
                constructorError(input);
                throw E;
            }
            System.out.println("\t Successfully constructed book > " + book);
            System.out.println();
            try{
                System.out.println("\t\tExpected getTitle output > " + input[0]);
                System.out.println("\t\tActual   getTitle output > " + book.getTitle());
                System.out.println();
            }
            catch (Exception E)
            {
                System.out.println("Error in getTitle for this book > " + book);
                throw E;
            }
            try{
                System.out.println("\t\tExpected getAuthor output > " + processName(input[1]));
                System.out.println("\t\tActual   getAuthor output > " + book.getAuthor());
                System.out.println();
            }
            catch (Exception E)
            {
                System.out.println("Error in getAuthor for this book > " + book);
                throw E;
            }
            try{
                System.out.println("\t\tExpected getISBN output > " + input[2]);
                System.out.println("\t\tActual   getISBN output > " + book.getISBN());
                System.out.println();
            }
            catch (Exception E)
            {
                System.out.println("Error in getISBN for this book > " + book);
                throw E;
            }
        }
        System.out.println();
    }

    private static void testComparisonMethods() throws Exception
    {
        System.out.println("*****************************************************************************************************");
        System.out.println("************************ TESTING COMPARISONS (equals, hasTitle, isWrittenBy) ************************");
        System.out.println("*****************************************************************************************************");
        System.out.println();
        System.out.println();

        String[][] validConstructorInputs = {
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

        Book[] books = new Book[validConstructorInputs.length];
        String[] titles = new String[validConstructorInputs.length];
        String[] authors = new String[validConstructorInputs.length];

        for (int i = 0; i < validConstructorInputs.length; i++)
        {
            books[i] = new Book(
                    validConstructorInputs[i][0],
                    validConstructorInputs[i][1],
                    validConstructorInputs[i][2]
            );
            titles[i] = validConstructorInputs[i][0];
            authors[i] = processName(validConstructorInputs[i][1]);
        }

        System.out.println("\t***TESTING EQUALS***");
        for (int i = 0; i < validConstructorInputs.length; i++)
        {
            for (int j = 0; j < validConstructorInputs.length; j++)
            {
                try
                {
                    System.out.println();
                    System.out.println("\t\tComparing these books:");
                    System.out.println("\t\t\t" + books[i]);
                    System.out.println("\t\t\t" + books[j]);

                    boolean correct = i == j;
                    boolean result = books[i].equals(books[j]);
                    correct = correct == result;

                    System.out.println("\t\tThe books are equal : " + result + " > " + (correct ? "CORRECT" : "INCORRECT"));
                }
                catch (Exception E)
                {
                    System.out.println("\t\tError using equals with these two books:");
                    System.out.println("\t\t\t" + books[i]);
                    System.out.println("\t\t\t" + books[j]);
                    throw E;
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("\t***TESTING HASTITLE***");
        for (int i = 0; i < validConstructorInputs.length; i++)
        {
            for (int j = 0; j < validConstructorInputs.length; j++)
            {
                try
                {
                    System.out.println();
                    System.out.println("\t\tComparing:");
                    System.out.println("\t\t\tBook  > " + books[i]);
                    System.out.println("\t\t\tTitle > " + titles[j]);

                    boolean correct = i == j;
                    boolean result = books[i].hasTitle(titles[j]);
                    correct = correct == result;

                    System.out.println("\t\tThe book has the specified title : " + result + " > " + (correct ? "CORRECT" : "INCORRECT"));
                }
                catch (Exception E)
                {
                    System.out.println("\t\tError using hasTitle with this book and title:");
                    System.out.println("\t\t\t" + books[i]);
                    System.out.println("\t\t\t" + titles[j]);
                    throw E;
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("\t***TESTING ISWRITTENBY***");
        for (int i = 0; i < validConstructorInputs.length; i++)
        {
            for (int j = 0; j < validConstructorInputs.length; j++)
            {
                try
                {
                    System.out.println();
                    System.out.println("\t\tComparing:");
                    System.out.println("\t\t\tBook   > " + books[i]);
                    System.out.println("\t\t\tAuthor > " + authors[j]);

                    boolean correct = i == j;
                    boolean result = books[i].isWrittenBy(authors[j]);
                    correct = correct == result;

                    System.out.println("\t\tThe book has the specified author : " + result + " > " + (correct ? "CORRECT" : "INCORRECT"));
                }
                catch (Exception E)
                {
                    System.out.println("\t\tError using isWrittenBy with this book and author:");
                    System.out.println("\t\t\t" + books[i]);
                    System.out.println("\t\t\t" + titles[j]);
                    throw E;
                }
            }
        }
        System.out.println();
    }

    private static void constructorError(String[] input)
    {
        System.out.println("\tError in constructing book with the following inputs:");
        System.out.println("\t\tTitle  > " + input[0]);
        System.out.println("\t\tAuthor > " + input[1]);
        System.out.println("\t\tISBN   > " + input[2]);
        System.out.println("\tThis error is in the constructor, or a method called from it.");
    }

    private static String processName(String name)
    {
        int i = name.lastIndexOf(' ');
        if (i == -1)
        {
            return name;
        }
        return name.substring(i+1);
    }
}
