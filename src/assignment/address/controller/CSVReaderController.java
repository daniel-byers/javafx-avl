package assignment.address.controller;

import assignment.address.model.AVLTree;
import assignment.address.model.CSVReader;
import assignment.address.model.Country;
import javafx.collections.ObservableList;

/**
 * @author Daniel Byers | 13121312
 */
public class CSVReaderController extends Controller
{
    final private CSVReader csvReader = new CSVReader();
    
    /**
     * Accessor for related method in {@link CSVReader#OpenFile(java.lang.String) OpenFile}.
     * @param file_path Path to file on disk
     * @return {@link AVLTree} of type {@link Country}
     */
    public AVLTree<Country> openFIle(String file_path)
    {
        return csvReader.OpenFile(file_path);
    }
    
    /**
     * Accessor for related method {@link CSVReader#getArray() getArray}.
     * @return Array of type String relating to the countries in the {@link AVLTree}
     */
    public String[] getArray()
    {
        return csvReader.getArray();
    }
    
    /**
     * Accessor for related method in {@link CSVReader#getSortedArray() getSortedArray}.
     * @return Sorted array of type String relating to the countries in the {@link AVLTree}
     */
    public String[] getSortedArray()
    {
        return csvReader.getSortedArray();
    }
    
    /**
     * Accessor for related method in {@link CSVReader#getList() getList}.
     * @return ObservableList of type String relating to the countries in the {@link AVLTree}
     */
    public ObservableList<String> getList()
    {
        return csvReader.getList();
    }
}
