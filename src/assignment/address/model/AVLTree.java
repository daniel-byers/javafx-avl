package assignment.address.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Main AVL Tree data model. 
 *  @author Daniel Byers | 13121312
 * @param <T> Type-safe argument for comparison.
 */

public class AVLTree<T extends Comparable<T>>
{
    private Node<T> root;
    /**
    *   To access root from outside of this class.
    *   @return Root node of {@link AVLTree}
    */
    public Node<T> getRoot () { return root; }
	
    /** 
    *   Empty initialiser.
    *   Sets default root to null
    */
    public AVLTree ()
    {
        root = null; 
    }

    /** 
    *   Initialiser .
    *   Sets default root to parameter.
    *   @param node Sets root as pre-defined node
    */
    public AVLTree (Node<T> node)
    {
        root = node;
    }

    /** 
    *   Public method to insert item and update root. Protects private member root 
    *   from being directly altered from outside of this class.
    *   @param item Item being passed into function to be inserted into tree.
    */
    public void InsertItem (T item)
    {
        root = insertItem(item, root);
    }
    
    /**
    *   Private method to insert item. First checks to make sure the current node
    *   is not null. If the current node is null, then a new node is created and item
    *   is passed in as a constructor. The second check is to see if the item being
    *   being inserted is smaller or larger than the current node's data. If it is smaller
    *   then the function is called recursively to traverse the left-sub tree until the 
    *   node is null. The same thing happens if the item is larger, but the right sub-tree
    *    is traversed. Within the functions is code to balance the tree at insert if necessary.
    *   If the tree isn't null, and the item isn't smaller or larger than the current node's data
    *   then it must be equal, and AVL tree's do not allow duplicate entries.
    *   @param item passed in from public accessors method.
    *   @param tree root node of current tree.
    *   @return returns the current node to be saved.
    */
    private Node<T> insertItem (T item, Node<T> tree)
    {
        if  (tree == null) 
            tree = new Node<>(item);			
			
        else if  (item.compareTo(tree.getData())  <  0)
        {
            tree.Left = insertItem(item, tree.Left);
			
            if (height(tree.Left) - height(tree.Right) == 2) 
            {
                if ((item.compareTo(tree.Left.getData()) < 0))
                    tree = rotateRight(tree);   // Left Left case.
                else
                    tree = doubleRotateRight(tree); // Left Right case.
            }
        }
        else if  (item.compareTo(tree.getData()) > 0)
        {
            tree.Right = insertItem(item, tree.Right);
            
            if (height(tree.Left) - height(tree.Right) == -2)
            {
                if ((item.compareTo(tree.Right.getData()) > 0))
                    tree = rotateLeft(tree);    //  Right Right case.
                else
                    tree = doubleRotateLeft(tree);  //  Right Left case.
            }
        }
        else 
            System.out.println("Duplicate Entry");
        return tree;		
    }
    
     /**
    *   Public method to remove item and update root. Protects private member root 
    *   from being directly altered from outside of this class.
    *   @param item  item being passed into function to be removed from the tree.
    */
    public void RemoveItem (T item)
    {
        root = removeItem(item, root);
    }
    
    /**
    *   Private method to remove item. First checks to make sure the current node
    *   is not null. If the current node is null, then the tree is empty or the item isn't
    *   in it. Then checks to see if item is smaller or larger than current node, if it's
    *   neither then it's must be equal so checks to see how many children that node
    *   has. If it has two then a the value of the node is swapped with the value of the
    *   smallest item in it's right-sub tree, then removeItem is called again. but this time
    *   on the value in newRoot. A new node is created to represent this and the
    *   returned value of leastItem() is assigned to it. If the node has less than two children
    *   another check is made: if the left child is not null, the value of the node is equal to
    *   that value, otherwise it's equal to the value of the right child. If both children are null
    *   then the node is a leaf. Finally, the node is rebalanced at the end.
    *   @param item Item to remove.
    *   @param tree Root passed in from public accessors.
    *   @return Returns node to be saved.
    */
    private Node<T> removeItem (T item, Node<T> tree)
    {
        if  (tree == null)
            return null;
        if  (item.compareTo(tree.getData()) < 0)
            tree.Left = removeItem(item, tree.Left); 
         else if (item.compareTo(tree.getData()) > 0)
            tree.Right = removeItem(item, tree.Right);
        else if (tree.Left != null && tree.Right != null)
        {
            Node<T> newRoot = leastItem(tree.Right); 
            tree.setData(newRoot.getData());
            tree.Right = removeItem(tree.getData(), tree.Right); 
        }
        else 
            tree = (tree.Left != null) ? tree.Left  : tree.Right;
        tree = rebalance(tree);
        return tree;
    }
	
    /**
    *   Rotates the tree left using the right child as the pivot point. Right Right case.
    *   Creates a new node which is equal to the old root's right sub-tree, assigns
    *   old root's right sub-tree to the new root's left sub-tree, then old root
    *   becomes new root's left sub-tree. Old root is declared equal to new root.
    *   @param tree Node to be balanced.
    *   @return Returns current node to be saved.
    */
    private Node<T> rotateLeft(Node<T> tree)
    {
        Node<T> newRoot = tree.Right; 
        tree.Right = newRoot.Left;
        newRoot.Left = tree;
        tree = newRoot;
        return tree;
    }
	
    /**
    *   Rotates the tree right before rotating it left. Right Left case.
    *   @param tree Node to be rotated.
    *   @return Returns the result of calling rotateLeft on node.
    */
    private Node<T> doubleRotateLeft(Node<T> tree)
    {
        tree.Right = rotateRight(tree.Right);
        return rotateLeft(tree);
    }
	
    /**
    *   Rotates the node right using the left child as the pivot point. Left Left case.
    *   Creates a new node which is equal to the old root's left sub-tree, assigns
    *   old root's left sub-tree to the new root's right sub-tree, then old root
    *   becomes new root's right sub-tree. Old root is declared equal to new root.
    *   @param tree Node to be balanced.
    *   @return Returns current node to be saved.
    */
    private Node<T> rotateRight(Node<T> tree)
    {
        Node<T> newRoot = tree.Left;
        tree.Left = newRoot.Right;
        newRoot.Right = tree;
        tree = newRoot;
        return tree;
    }
	
    /**
    *   Rotates the tree left before rotating it right. Right Right case.
    *   @param tree Node to be rotated.
    *   @return Returns the result of calling rotateLeft on node.
    */
    private Node<T> doubleRotateRight(Node<T> tree)
    {
        tree.Left = rotateLeft(tree.Left);
        return rotateRight(tree);
    }

    /**
    *   Reblances the node by comparing the heights of it's children.
    *   First check is the balance factor of the node. BF of 2 or greater means tree
    *   is unbalanced on the left side, -2 or lower means it's unbalanced on the right
    *   side. Second check is to see if a single or double rotation is needed to balance
    *   the node.
    *   @param tree Node to be balanced.
    *   @return Returns tree to be saved.
    */
    private Node<T> rebalance (Node<T> tree)
    {
        if (BalanceFactor(tree) >= 2) //	if the bF is equal to or greater than 2, then the node is unbalanced so requires rotation.
        {
            if (height(tree) > 0) //	if height is positive.
                tree = rotateRight(tree); //	Left Left case.
            else
                tree = doubleRotateRight(tree); //	Left Right case.
        }
        else if (BalanceFactor(tree) <= -2) //	likewise, if the bF is less than or equal to -2, then the tree is unbalanced.
        {
            if (height(tree) < 0) //	if height positive
                tree = rotateLeft(tree); //	Right Right case.
            else
                tree = doubleRotateLeft(tree); //	Right Left case.
        }
        return tree;
    }
    
    /**   
    *   Public accessor for preOrder().
    * @return String containing the result of the PreOrer traversal.
    */
    public String PreOrder()
    {
        String preOrder = "";
        return preOrder(root, preOrder);
    }

    /**
    *   Pre-Order traversal of the AVL tree.
    *   Prints current node's data and balance factor to the console.
    *   Calls itself passing in the node's left child as parameter.
    *   Calls itself passing in the node's right child as parameter.
    *   @param tree Current node being passed in.
    */
    private String preOrder(Node<T> tree, String preOrder)
    {		
        if (tree != null)
        {
            Country tempCountry = (Country) tree.getData();
            preOrder += tempCountry.getName() + " [bF:" + BalanceFactor(tree) + "], ";
            preOrder = preOrder(tree.Left, preOrder);
            preOrder = preOrder(tree.Right, preOrder);
        }
        return preOrder;
    }

    /**
    *   Public accessor for inOrder().
    * @return String containing the result of the InOrer traversal.
    */
    public String InOrder()
    {
        String inOrder = "";
        return inOrder(root, inOrder);
    }

    /**
    *   In-Order traversal of the AVL tree.   
    *   Calls itself passing in the node's left child as parameter.
    *   Prints current node's data and balance factor to the console.
    *   Calls itself passing in the node's right child as parameter.
    *   @param tree Current node being passed in.
    */
    private String inOrder(Node<T> tree, String inOrder)
    {
        if (tree != null)
        {	
            Country tempCountry = (Country) tree.getData();
            inOrder = inOrder(tree.Left, inOrder);
            inOrder += tempCountry.getName() + " [bF:" + BalanceFactor(tree) + "], ";
            inOrder = inOrder(tree.Right, inOrder);
        }
        return inOrder;
    }

    /**
    *   Public accessor for postOrder().
    * @return String containing the result of the PostOrer traversal.
    */
    public String PostOrder()
    {
        String postOrder = ""; 
        return postOrder(root, postOrder);
    }

    /**
    *   Post-Order traversal of the AVL tree.
    *   Calls itself passing in the node's left child as parameter.
    *   Calls itself passing in the node's right child as parameter.
    *   Prints current node's data and balance factor to the console.
    *   @param tree Current node being passed in.
    */
    private String postOrder(Node<T> tree, String postOrder)
    {
        if (tree != null)
        {
            Country tempCountry = (Country) tree.getData();
            postOrder = postOrder(tree.Left, postOrder);
            postOrder = postOrder(tree.Right, postOrder);
            postOrder += tempCountry.getName() + " [bF:" + BalanceFactor(tree) + "], ";
        }
        return postOrder;
    }
	
    /**
    *   Checks first if node is null, if not see return.
    *   @param node Current node to be balanced.
    *   @return Returns result of calling height on node's left and right children.
    */
    public int BalanceFactor (Node<T> node)
    {
        if (node == null)
            return 0;
        else
            return (height(node.Left) - height(node.Right));
    }
    
    /**
    *   Calculates the height of the node using it's leaves.
    *   Initial check to see if node is null, which means it has a height of 0.
    *   @param tree Node to be checked.
    *   @return Returns the result of max() added to 1 as an integer.
    */
    private Integer height(Node<T> tree)
    {
        if (tree == null)
            return 0;
        else
            return 1 + max(height(tree.Left), height(tree.Right));
    }
	
    /** 
     *  Private function to work out the greater of two integers.
    *   @param x First number.
    *   @param y Second number.
    *   @param return Returns the higher number.
    */
    private int max(int x, int y)
    {
        return x > y ? x : y;
    }
	
    /**
    *   Used for merging sub-trees when removing a node
    *   that has two children. Looks for smallest node in
    *   tree's right sub-tree.
    *   @param tree Root of node that is to be removed right sub-tree.
    *   @return returns the smallest node in the tree
    */
    private Node<T> leastItem(Node<T> tree)
    {
        if (tree == null)
            return null;
        if (tree.Left == null)
            return tree;
        return leastItem(tree.Left);
    }
    
    public int DepthOfTree ()
    {
        return depthOfTree(root, 0);
    }
    
    private int depthOfTree (Node<T> tree, int depth)
    {
        if (tree == null)
            return 0;
        return depth + max(height(tree.Left), height(tree.Right));
    }    
    
    /**
     * Public accessor to return the number of nodes in the tree.
     * Initial check to see if tree is empty, if not;
     * @return Number of nodes in the AVL Tree.
     */
    public int NumberOfNodes()
    {
        if (root == null)
            return 0;
        else
            return numberOfNodes(root);
    }
    
    /**
     * Recursive function to traverse AVL tree and return number of nodes.
     * @param tree Currently focused node.
     * @return Sum of nodes sub-trees plus one for current node.
     */
    private int numberOfNodes (Node<T> tree)
    {
        if (tree == null)
            return 0;
        return 1 + numberOfNodes(tree.Left) + numberOfNodes(tree.Right);
    }
    
    /**
    *   Public accessor for retrieveCountryByName
    *   Creates a temporary country to return which is equal to the result
    *   of calling retrieveCountryByName () passing in the pre-defined String
    *   and the root node to begin search from. Cast's the data from the returned
    *   node to type Country so it can be passed out.
    *   @param name Pre-defined String to search for.
    *   @return Country that was associated with String, or null.
    */	
    public Country RetrieveCountryByName (String name)
    {
        Country tempCountry = (Country) retrieveCountryByName(name, root).getData();
        return (tempCountry != null) ? tempCountry : null;
    }
    
    /**
    *   Private method retrieve country by name defined in a String. 
    *   This will be implemented as a search function to retrieve specific
    *   country from within the tree. Uses a while loop to keep iterating until a null 
    *   node is reached (so therefore item cannot be in the tree) or item has
    *   been found. Creates a temporary country equal to the current node for
    *   comparison. Calls the compareToByString method from the Country class
    *   to compare if item is smaller or larger than the Country's name. If it's equal
    *   then while loop is ended.
    *   @param item Pre-defined String to search AVL tree for.
    *   @param tree Current node, initially root.
    *   @return Returns node that corresponds to a pre-defined String or null
    *   if node wasn't found.
    */
    private Node<T> retrieveCountryByName (String item, Node<T> tree)
    {		
        Boolean found = false;  // only insane people carry on looking for something once they've found it...
        while ( !found && tree != null) 
        {		
            Country tempCountry = (Country) tree.getData();
            if (tempCountry.compareToByString(item) > 0)
                tree = tree.Left;
            else if (tempCountry.compareToByString(item) < 0)
                tree = tree.Right;
            else
            {
                found = true;
                break;
            }
        }
        if (found == true)
            return tree;
        else
            return null;
    }
    
    /**
     * Public accessor to retrieve all trade partners of a selected  {@link Country}.
     * Creates a string which is constructed from the {@link AVLTree#retrievePartners(java.lang.String, java.lang.String, assignment.address.model.Node) retrievePartners}
     * method. Following this a {@link List} is created and populated from the returned String. This {@link List} now contains all the Countries
     * whose list of trading partners contained the  {@link Country}'s name. After that, a check is done for the  {@link Country} in question's
     * trade partners to see if any are missing and a validation is done to make sure the first link in the list isn't null.
     * @param countryName Chosen {@link Country} to search for.
     * @return {@link List} of all the Countries who trade with the chosen  {@link Country}.
     */
    public List<String> RetrievePartners (String countryName)
    {
       String temp = "";
       String tradePartners = retrievePartners(countryName, temp, root);
       List<String> tpList = new LinkedList<>(Arrays.asList(tradePartners.split(",")));
       
       Country tempCountry = RetrieveCountryByName(countryName);
       for(String s : tempCountry.getTradePartners())
       {
           if (tpList.contains(s) == false)
               tpList.add(s);
       }
       
       Collections.sort(tpList);
       if (tpList.get(0) == null || tpList.get(0).equals(""))
           tpList.remove(0);
       
       return tpList;       
    }
    
    /**
     * Recursive method to populate a {@link String} containing all the Countries who trade with a chosen  {@link Country}.
     * The {@link Country} is first retrieved and cast to a temporary  {@link Country} object. This {@link Country} then has it's trade partners
     * iterated through and checked to see if the {@link Country} name matches any of those listed. If it does, then it's name is added to the {@link String}
     * @param countryName {@link Country} being searched for.
     * @param temp {@link String} of current matches.
     * @param tree Current {@link Node} in the {@link AVLTree}.
     * @return 
     */
    private String retrievePartners(String countryName, String temp, Node<T> tree)
    {
        Country tempCountry = (Country) tree.getData();        
        temp = "";
        
        for (String s : tempCountry.getTradePartners())
        {
            if (countryName.equals(s))
                temp += tempCountry.getName() + ",";
        }
        
        if (tree.Left != null)
            temp += retrievePartners(countryName, temp, tree.Left);
        if (tree.Right != null)
            temp += retrievePartners(countryName, temp, tree.Right);
        
        return temp;
    }
    
    /**
     * Method to calculate the total trade potential for any given {@link Country}.
     * Uses the {@link AVLTree#RetrievePartners(java.lang.String) RetrievePartners} method to retrieve a fully populated list of  {@link Country}
     * names. Each of these names is checked to make sure it's not null, then has it's GDP Growth added to the sub-total.
     * @param country {@link Country} to calculate trade potential for.
     * @return Sum of partners trade potential.
     */
    private double calculateTradePotential (Country country)
    {
        double subTotal = 0;
        List<String> tempList = RetrievePartners(country.getName());
        for (String s : tempList)
        {
            try // when a search of the tree is done by string, null is returned if the country isn't found. Null pointer caught here.
            {
                Country tempCountry = (Country) retrieveCountryByName(s, root).getData();
                subTotal += tempCountry.getGDPGrowth();
            } catch (NullPointerException npe) { }
        }
        return subTotal;
    }
    
    /**
     * Public accessor to return the {@link Country} with the largest trade potential.
     * @return {@link Country} who has the biggest trade potential.
     */
    public Country BiggestTradePotential ()
    {
        Country tmp = new Country();
        return biggestTradePotential(root, tmp);
    }
    
    /**
     * Recursive method to traverse the {@link AVLTree} to find the {@link Country} with the biggest
     * potential to trade.
     * This is decided by calculating the sum of all of the countries trade partners and saving the largest number,
     * @param tree Current {@link Node} of the {@link AVLTree}
     * @param currentHighest {@link Country} with the current highest trade potential.
     * @return {@link Country} that has the biggest trade potential.
     */
    private Country biggestTradePotential (Node<T> tree, Country currentHighest)
    {
        Country tempCountry = (Country) tree.getData();
        
            tempCountry.setTradePotential(calculateTradePotential(tempCountry));
        
            if (tempCountry.getTradePotential() > currentHighest.getTradePotential())
                currentHighest = tempCountry;
            if (tree.Left != null)
                currentHighest = biggestTradePotential(tree.Left, currentHighest);
            if (tree.Right != null)
                currentHighest = biggestTradePotential(tree.Right, currentHighest);
        return currentHighest;
    }
    
    /**
     * Public method used to return a list of {@link Country} names to be used in predictive search.
     * Initial check to see if the search string isn't null, then populates a {@link List} of {@link Country} names that are
     * retrieved as a {@link String} from {@link AVLTree#predictiveSearch(assignment.address.model.Node, java.lang.String) predictiveSearch}.
     * The list is sorted before it's returned.
     * @param prefix Search parameter passed in from UI.
     * @return {@link List} of results.
     */
    public List<String> PredictiveSearch (String prefix)
    {
        if (prefix == null || prefix.isEmpty())
            return null;
        List<String> results = Arrays.asList(predictiveSearch(root, prefix.toLowerCase()).split(","));
        Collections.sort(results);
        return results;
    }
    
    /**
     * Recursive method to traverse the {@link AVLTree} to find matches to search {@link String}.
     * The {@link String] passed in is used as a prefix to see if any {@link Country} names start with those letters. If
     * they match then that {@link Country} is appended to the {@link String} of matches.
     * @param tree Current {@link Node} of the {@link AVLTree}.
     * @param prefix {@link String} to search for, passed in from UI.
     * @return {@link String} of results (if any) that match the search parameter.
     */
    private String predictiveSearch (Node<T> tree, String prefix)
    {
        Country tempCountry = (Country) tree.getData();
        String results = "";
        if (tempCountry.getName().toLowerCase().startsWith(prefix))
            results += tempCountry.getName() + ",";
        if (tree.Left != null)
            results += predictiveSearch(tree.Left, prefix);
        if (tree.Right != null)
            results += predictiveSearch(tree.Right, prefix);        
        return results;
    }
}

