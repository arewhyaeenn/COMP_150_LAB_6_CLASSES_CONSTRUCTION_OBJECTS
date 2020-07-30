public class LinkedList
{
    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int length = 0;

    /**
     * Default constructor
     * Do nothing; the list is created but remains empty.
     * Thus, the length stays 0 (the initialization value above).
     * Similarly, the head and tail stay null
     */
    public LinkedList()
    {

    }

    /**
     * Construct a list containing the values from the input array.
     * HINT: implement "append" first, then come back and implement this one.
     * @param array
     */
    public LinkedList(int[] array)
    {
        //TODO
    }

    /**
     * basic accessor method
     * @return the head of the list
     */
    public LinkedListNode getHead()
    {
        //TODO
        return null;
    }

    /**
     * basic accessor method
     * @return the tail of the list
     */
    public LinkedListNode getTail()
    {
        //TODO
        return null;
    }

    /**
     * basic accessor method
     * Questions for your consideration:
     *  - Why is the length attribute private?
     *  - Why not just make it public and skip this method?
     *  - What could clients do to the length attribute if it was public?
     *  - Are you as annoyed as I am by the convention to just call it "length()" instead of "getLength()"?
     * @return the length of the list
     */
    public int length()
    {
        //TODO
        return 0;
    }

    /**
     * Add a new node to the end of the list.
     * Don't forget to:
     *  - update the length
     *  - set the old tail's next reference to the new node
     *  - update the tail attribute so it references the new node
     *  - deal with the fringe case where the list is empty (so head and tail start out null)
     * @param value the value of the new node
     */
    public void append(int value)
    {
        //TODO
    }

    /**
     * Add a new node to the beginning of the list.
     * Don't forget to:
     *  - update the length
     *  - set the new head's next reference to the old head
     *  - update the head attribute to reference the new node
     *  - deal with the case where the list is empty
     * @param value the value of the new node
     */
    public void prepend(int value)
    {
        //TODO
    }

    /**
     * Return the value of the element at the specified index.
     * The elements aren't explicitly indexed. Remove that one that would
     * be at the specified index in this list's array equivalent.
     * (HINT: use a loop and getNext() to get to the specified element)
     * Don't forget to:
     *  - throw an IndexOutOfBoundsException if the index is out of bounds
     *  - make sure you don't inadvertently change the list.
     *      (reassigning the head reference is a common error here)
     * @param index the "index" of the node who's value should be returned
     * @return the value at the specified "index"
     */
    public int get(int index)
    {
        //TODO
        return 0;
    }

    /**
     * Remove the first element from the list, and return its value.
     * Don't forget to:
     *  - update the length
     *  - move the head to reference the new head
     *  - deal with the cases where:
     *      - the list is empty before popLeft is called (return null)
     *      - the list has one element before popLeft is called (so the tail reference needs to be updated too)
     * Questions for your consideration:
     *  - Why aren't we implementing a popRight method too?
     *  - What would the new tail be after a popRight method completed?
     *  - How would we have to get to this new tail node? (We don't have a "previous" reference)
     *  - Why do we return an Integer instead of an int? Which possibility does this help us deal with?
     *      (HINT: can int type be null?)
     * @return the value of the removed node
     */
    public Integer popLeft()
    {
        //TODO
        return 0;
    }

    /**
     * Remove the element at the specified "index" and return its value.
     * Traverse the list from the start, like you did in "get", but also
     * remove the specified node after getting its value.
     * Don't forget to:
     *  - throw an IndexOutOfBoundsException if the index is invalid
     *  - update the length
     *  - link the node before the removed node up to the one after it (so the list is still intact)
     *  - deal with the cases in which:
     *      - the head is the removed node (update the head reference)
     *      - the tail is the removed node (update the tail reference)
     *      - the head and tail are both removed (there was only 1 element, so the list is now empty)
     * @param index the index of the element to be removed.
     * @return the value of the removed node
     */
    public int popAtIndex(int index)
    {
        //TODO
        return 0;
    }

    /**
     * Insert a new node at the specified "index".
     * Don't forget to:
     *  - update the length
     *  - set the previous node's "next" to the new node
     *  - set the new node's "next" to the node that used to be at the specified index
     *  - throw an IndexOutOfBoundsException if the index is invalid
     *      (unlike other methods, the index can == length before length is incremented,
     *       this is the case when adding to the end of the list)
     *  - deal with the cases in which:
     *      - the list is empty before insertion
     *      - the new element is inserted at index 0 (it is the new head)
     *      - the new element is inserted at index length (before length is updated) (it is the new tail)
     * @param value the value of the new node
     * @param index the "index" that the new node should have after insertion
     */
    public void insertAtIndex(int value, int index)
    {
        //TODO
    }

    /**
     * Construct and return a new array, containing the values in the list.
     * Don't change any array attributes or update any nodes; the list should remain intact.
     * @return an array containing the same values as the linked list.
     */
    public int[] toArray()
    {
        //TODO
        return new int[] {};
    }


    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("List{ ");
        if (length > 0)
        {
            builder.append("(");
            builder.append(head.getData());
            builder.append(")");
            LinkedListNode currentNode = head.getNext();
            while (currentNode != null)
            {
                builder.append(" -> ");
                builder.append("(");
                builder.append(currentNode.getData());
                builder.append(")");
                currentNode = currentNode.getNext();
            }
        }
        builder.append(" }");

        return builder.toString();
    }
}
