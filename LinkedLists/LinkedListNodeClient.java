public class LinkedListNodeClient
{
    public static void main(String[] args)
    {
        final int NUMBER_OF_NODES = 10;

        LinkedListNode[] nodes = new LinkedListNode[NUMBER_OF_NODES];

        System.out.println("Constructing node with default constructor...");
        try
        {
            nodes[0] = new LinkedListNode();
        }
        catch (Exception e)
        {
            System.out.println("Error running default constructor");
            throw e;
        }
        System.out.println();

        System.out.println("Printing node...");
        try{
            System.out.println("Expected result > Node(0)");
            System.out.println("Actual   result > " + nodes[0]);
        }
        catch (Exception e)
        {
            System.out.println("Error running toString.");
            throw e;
        }
        System.out.println();

        System.out.println("Constructing nodes with secondary constructor...");
        for (int i = 1; i < NUMBER_OF_NODES; i++)
        {
            try
            {
                nodes[i] = new LinkedListNode(i);
            } catch (Exception e)
            {
                System.out.println("Error running secondary constructor with value " + i);
                throw e;
            }
        }
        System.out.println();

        System.out.println("Printing all constructed nodes (values 0 through " + (NUMBER_OF_NODES-1) + ")...");
        for (int i = 0; i < NUMBER_OF_NODES; i++)
        {
            try
            {
                System.out.println(nodes[i]);
            }
            catch (Exception e)
            {
                System.out.println("Error printing node with value " + i);
                throw e;
            }
        }
        System.out.println();

        System.out.println("Testing setData() method on each node.");
        for (int i = 0; i < NUMBER_OF_NODES; i++)
        {
            try
            {
                nodes[i].setData(-i);
            }
            catch (Exception e)
            {
                System.out.println("Error setting value to " + -i);
                throw e;
            }
        }
        System.out.println();

        System.out.println("Printing nodes again (should contain values 0 through " + -(NUMBER_OF_NODES-1) + ")");
        for (int i = 0; i < NUMBER_OF_NODES; i++)
        {
            try
            {
                System.out.println(nodes[i]);
            }
            catch (Exception e)
            {
                System.out.println("Error printing node with (whose value should be " + -i + ")");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Testing getData method on each node (values 0 through " + -(NUMBER_OF_NODES-1) + " should be printed)");
        for (int i = 0; i < NUMBER_OF_NODES; i++)
        {
            try
            {
                System.out.println(nodes[i].getData());
            }
            catch (Exception e)
            {
                System.out.println("Error using getData() on node whose value should be " + -i);
                throw e;
            }
        }
        System.out.println();

        System.out.println("Using setNext() to construct a linked list...");
        for (int i = 0; i < NUMBER_OF_NODES-1; i++)
        {
            try
            {
                nodes[i].setNext(nodes[i+1]);
            }
            catch (Exception e)
            {
                System.out.println("Error in setNext()");
                throw e;
            }
        }
        System.out.println();

        System.out.println("Using getNext() to traverse the list...");
        System.out.println("If the list with values from 0 through " + -(NUMBER_OF_NODES-1) + " is printed, success!");
        LinkedListNode currentNode = nodes[0];
        while (currentNode != null)
        {
            System.out.println(currentNode);
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }
}
