package assignment.address.model;

import java.util.*;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Daniel Byers | 13121312
 */
public class CSVReader
{
    private AVLTree<Country> avlTree;   // create an AVL Tree to store the countries in.
    private String[] namesArray;     //create a String array to hold a copy of the names.
    private ObservableList<String> namesList;
    
    /**
     * Initialiser.
     * Creates instance of an {@link AVLTree} and assigns is to avlTree
     */
    public CSVReader()
    {
        avlTree = new AVLTree<Country>();
    }
    
    /**
     * Public accessor to open a Comma Separated Values file.
     * @param file_path Location of .csv file on disk.
     * @return Returns a fully populated and balanced AVL Tree of type {@link Country}.
     */
    public AVLTree<Country> OpenFile(String file_path)
    {
        openFile(file_path);
        return avlTree;
    }
    
    /**
     * Private method to read in a csv file and create an AVL Tree from it. 
     * Declares a {@link BufferedReader } to read the lines of the file. Initially fileLength()
     * is called on the file to be read to calculate the size of the namesArray. This is created
     * and populated the same time as the tree so there is minimal overhead involved and 
     * provides a list of all the countries added to the tree. The {@link BufferedReader } scans each line
     * stopping at the line break, removing all square brackets first, then splitting the data into 
     * an array at each comma. A third array is used to further split the trade partners by the
     * semicolon allowing them to be individually added to the LinkedList created for them. A
     * new {@link Country } is then created using the elements in the arrays populated from the line read  in
     * the file. Each index corresponds to the correct parameter in the class's constructor. That
     * country is then inserted into avlTree. The name of that country is then added to namesArray.
     * Any errors in the file are caught and output to the console. The BufferedReader is then closed.
     * @param path Location of file forwarded from public
     */    
    private void openFile(String path)
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(path));
            String inputLine;
            int fileLength = (fileLength(path) - 1);
            namesArray = new String[fileLength];
            namesList = FXCollections.observableArrayList();
            int counter = 0;
            
            while ((inputLine = br.readLine()) != null)
            {
                if (!inputLine.startsWith("Country"))
                {
                    LinkedList<String> partners = new LinkedList<>();
                    String[] colums = inputLine.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("_", " ").split(",");
                    String[] parts = colums[5].split(";");
                    partners.addAll(Arrays.asList(parts));
                    Country newCountry = new Country (colums[0], Double.parseDouble(colums[1]), Double.parseDouble(colums[2]), Double.parseDouble(colums[3]), Integer.parseInt(colums[4]), partners);
	  avlTree.InsertItem(newCountry);
                    namesArray[counter] = colums[0];
                    counter++;
                }
            }
        }
        catch (IOException e) { System.err.println(e.getMessage()); }
        finally
        { 
            if (br != null)
            {
                try { br.close(); }
                catch (IOException e) { System.err.println(e.getMessage()); }
            }
        }	
    }
    
    /**
     * Private method to calculate the amount of lines in the file.
     * Creates a BufferedReader using a FileReader that takes the file path
     * as the location to look for the file. This reader then iterates through
     * each line in the file using a while loop, and increasing the counter by
     * one each time the end of the line is reached. When the reader reaches
     * the end of the file, the total is returned.
     * @param file_path Location of the file to be read.
     * @return Returns the length of the file (by line count) as an int.
     */
    private int fileLength (String file_path)
    {
        int lineCount = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(file_path));
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                lineCount++;
        }
        catch (IOException e) { e.printStackTrace(); }
        finally
        {
            if (br != null)
            {
                try { br.close(); }
                catch (IOException e) { System.err.println(e.getMessage()); }
            }
        }
        return lineCount;
    }
    
    /**
     * To retrieve namesArray from outside of this class.
     * @return namesArray
     */
    public String[] getArray()
    {
        return namesArray;
    }
    
    /**
     * To retrieve array from outside class pre-sorted using Selection Sort.
     * For the length of the array (minus one) {@code i}, initialise index to 0 each iteration 
     *  so the first element is the start of the comparison. Compares each element in the array to 
     * index 0, starting at index 1 {@code j}, and using a for loop to iterate until the end of the
     *  array. The value of {@code index} is only updated if the value of the current element is 
     *  greater than it; where it is changed to the current value of {@code j}.  At the end of the 
     * for loop, the  value of index refers to the element that is largest. That element is then saved
     *  to the array at the point signified by {@code i} (another for loop counting down from the  
     *   value of the array length to terminate the iteration when it reaches less-than or equal-to 0).
     * @return namesArray
     */
    public String[] getSortedArray()
    {
        int i, j, index;
        String temp;
       // long startTime = System.currentTimeMillis();
        for (i = namesArray.length - 1; i > 0 ; i--)
        {
            index = 0;
            for(j = 1; j <= i; j++ )
            {
                if (namesArray[j].compareTo(namesArray[index]) > 0)
                    index = j;
            }
            temp = namesArray[index];
            namesArray[index] = namesArray[i];
            namesArray[i] = temp;
        }
        /*
        long finishTime = System.currentTimeMillis();
        long timeTaken = finishTime - startTime;
        System.out.println("Sort started at : " + startTime + ", finished at : " + finishTime + ", taking " + timeTaken + " ms.");
         */
        return namesArray;
    }
    
    public ObservableList<String> getList ()
    {
        namesList.addAll(Arrays.asList(namesArray));
        return namesList;
    }
}
