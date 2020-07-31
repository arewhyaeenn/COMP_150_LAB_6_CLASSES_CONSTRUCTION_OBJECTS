public class LinkedListClient
{
    public static void main(String[] args) throws MissingExceptionException
    {
        System.out.println("*****************************************************************************************");
        System.out.println("**************************** DEFAULT CONSTRUCTOR + ACCESSORS ****************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        System.out.println("Running default constructor...");
        LinkedList list = new LinkedList();
        System.out.println("Resulting list:");
        displayListAndParams(list);
        System.out.println();

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("**************************************** APPEND *****************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        int[] appendArray = {1,2,3,4,5};
        for (int value : appendArray)
        {
            System.out.println("Appending (" + value + ")...");
            list.append(value);
            displayListAndParams(list);
            System.out.println();
        }

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("********************************* SECONDARY CONSTRUCTOR *********************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        System.out.println("Testing secondary constructor to with input > " + intArrayToString(appendArray));
        list = new LinkedList(appendArray);
        displayListAndParams(list);
        System.out.println();

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("**************************************** PREPEND ****************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        int[] prependArray = {0, -1, -2, -3, -4, -5};
        for (int value : prependArray)
        {
            System.out.println("Prepending (" + value + ")...");
            list.prepend(value);
            displayListAndParams(list);
            System.out.println();
        }

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("****************************************** GET ******************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        for (int i = 0; i < prependArray.length + appendArray.length; i++)
        {
            System.out.println("Getting value at index " + i + " of\n" + list);
            int value = list.get(i);
            System.out.println("\tResult > (" + value + ")");
            System.out.println();
        }

        System.out.println("Getting value at index " + -1 + " of\n" + list);
        try
        {
            list.get(-1);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Getting value at index " + (prependArray.length + appendArray.length) +
                " of\n" + list);
        try
        {
            list.get(prependArray.length + appendArray.length);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Check that array metadata is still intact:");
        displayListAndParams(list);
        System.out.println();

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("**************************************** POPLEFT ****************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        System.out.println("Before first pop:");
        displayListAndParams(list);
        System.out.println();
        for (int i = 0; i <= prependArray.length + appendArray.length; i++)
        {
            System.out.println("Popping...");
            Integer value = list.popLeft();
            System.out.println("\tPopped value > (" + value + ")");
            System.out.println("Remaining list:");
            displayListAndParams(list);
            System.out.println();
        }

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("************************************** POPATINDEX ***************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        System.out.println("Constructing new list from array " + intArrayToString(appendArray) + "...");
        list = new LinkedList(appendArray);
        displayListAndParams(list);
        System.out.println();

        System.out.println("Popping value at index " + -1 + " of\n" + list);
        try
        {
            list.popAtIndex(-1);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Popping value at index " + appendArray.length +
                " of\n" + list);
        try
        {
            list.popAtIndex(appendArray.length);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        int[] popIndexes = {4, 0, 1, 0, 0};
        for (int popIndex : popIndexes)
        {
            System.out.println("Popping value at index " + popIndex + " of\n" + list);
            int value = list.popAtIndex(popIndex);
            System.out.println("\tPopped value > (" + value + ")");
            System.out.println("Remaining list:");
            displayListAndParams(list);
            System.out.println();
        }

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("************************************* INSERTATINDEX *************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        System.out.println("Inserting value 0 at index " + -1 + " of\n" + list);
        try
        {
            list.insertAtIndex(0, -1);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Inserting value 0 at index " + 1 + " of\n" + list);
        try
        {
            list.insertAtIndex(0, 1);
            System.out.println("IndexOutOfBoundsException was NOT thrown, it should have been!");
            throw new MissingExceptionException();
        }
        catch (Exception e)
        {
            if (e.getClass() == IndexOutOfBoundsException.class)
            {
                System.out.println("IndexOutOfBoundsException was properly thrown!");
            }
            else if(e.getClass() == MissingExceptionException.class)
            {
                throw e;
            }
            else
            {
                System.out.println("Incorrect exception thrown for invalid index. Throwing said exception...");
                throw e;
            }
        }
        System.out.println();

        int[] insertIndexes = {0, 0, 2, 1};
        int[] insertValues = {3, 1, 4, 2};
        for(int i = 0; i < insertIndexes.length; i++)
        {
            System.out.println("Inserting value " + insertValues[i] + " at index " +
                    insertIndexes[i] + " of" + list);
            list.insertAtIndex(insertValues[i], insertIndexes[i]);
            System.out.println("Resulting list:");
            displayListAndParams(list);
            System.out.println();
        }

        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("**************************************** TOARRAY ****************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

        int[][] testArrays = {
                {},
                {1},
                {1, 2, 3, 4}
        };
        for (int[] testArray : testArrays)
        {
            System.out.println("Converting " + intArrayToString(testArray) + " to a list and back...");
            int[] hopefullyCopy = new LinkedList(testArray).toArray();
            System.out.println("Result > " + intArrayToString(hopefullyCopy));
            System.out.println();
        }
    }

    private static void displayListAndParams(LinkedList list)
    {
        System.out.println("\tList   > " + list);
        System.out.println("\tLength > " + list.length());
        System.out.println("\tHead   > " + list.getHead());
        System.out.println("\tTail   > " + list.getTail());
    }

    private static String intArrayToString(int[] array)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int value : array)
        {
            builder.append(" ");
            builder.append(value);
        }
        builder.append(" }");
        return builder.toString();
    }

    static class MissingExceptionException extends Exception{
        public MissingExceptionException()
        {
            super("An Exception should have occurred in this test case! It didnt!");
        }
    }
}
