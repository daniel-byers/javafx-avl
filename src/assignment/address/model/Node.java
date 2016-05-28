package assignment.address.model;

/**
 * @author Daniel Byers | 13121312
 */
public class Node<T>
{
    private T data;
    public Node<T> Left, Right;

    /**
     * Initialiser.
    * @param item Initialise node with data.
    */
    public Node (T item)
    {
        data = item;
        Left = null;
        Right = null;
    }
    
    /** 
    * @return Returns data
    */
    public T getData  () {  return data; }
    
    /**
     * @param data Data to be stored 
     */
    public void setData  (T data) { this.data = data; }
}
