package assignment.address.controller;

import assignment.address.model.AVLTree;
import assignment.address.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Controller to allow the user to search the {@link AVLTree} for either
 * a specific {@link Country}, or by partners.
 * @author Daniel Byers | 13121312S
 */
public class SearchScreenController extends Controller
{
    private ObservableList<String> namesList;
    @FXML private GridPane gridPane;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField searchField;
    @FXML private ComboBox searchBox;
    @FXML private Label errorText;
    @FXML private ListView autoSearchField;
    
    /**
     * Sets the list of {@link Country} names.
     * @param namesList Passed in to set the list.
     */
    public void setNamesList (ObservableList<String> namesList)
    {
        this.namesList = namesList;
        searchBox.getItems().clear();
        searchBox.getItems().addAll(namesList);
    }
    
    /**
     * Called when the user clicks the Search button next to the text field.
     * Retrieves the {@link AVLTree} from MainApp and calls the {@link AVLTree#RetrieveCountryByName(java.lang.String) RetrieveCountryByName}
     * method after getting the text from the text field as the search parameter.
     */
    @FXML
    public void handleOnTextSearch ()
    {
        String search = searchField.getText();
        AVLTree<Country> avlTree = mainApp.getAvlTree();
        
        try
        {            
            Country searchResult = avlTree.RetrieveCountryByName(search);
            mainApp.showCountryInfoScreen(searchResult);
        }
        catch (NullPointerException e)
        { 
            errorText.setVisible(true);
            System.out.println("Couldn't find: " + search);
        }
    }
    
    /**
     * Called when the user clicks the Search button next to the combo box.
     * Retrieves the {@link AVLTree} from MainApp and calls the {@link AVLTree#RetrieveCountryByName(java.lang.String) RetrieveCountryByName}
     * method after getting the text from the combo box selection and using it  as the search parameter.
     */
    @FXML
    public void handleOnComboSearch ()
    {
        String search = searchBox.getSelectionModel().getSelectedItem().toString();
        System.out.println("Searching for: " + search);
        AVLTree<Country> avlTree = mainApp.getAvlTree();
        
        try
        {
            Country searchResult = avlTree.RetrieveCountryByName(search);
            mainApp.showSearchByTradePartner(searchResult);
        }
        catch (NullPointerException e)
        { 
            errorText.setVisible(true);
            e.printStackTrace();
        }
    }
    
    /**
     * Called when the user types in the text field.
     * Retrieves the {@link AVLTree} from MainApp and calls the {@link AVLTree#PredictiveSearch(java.lang.String) PredictiveSearch}
     * method. It updates the list of results each time the user presses a new key.
     */
    @FXML
    public void handlePartialSearch ()
    {        
        String search = searchField.getText();
        AVLTree<Country> avlTree = mainApp.getAvlTree();
        
        try
        {
            ObservableList<String> matches = FXCollections.observableList(avlTree.PredictiveSearch(search));
            System.out.println("predictive search results: " + matches);
            autoSearchField.setItems(matches);
        }
        catch (NullPointerException npe) { autoSearchField.setItems(null); }
    }
    
    /**
     * Called when the user clicks one of the results from the {@link AVLTree#PredictiveSearch(java.lang.String) PredictiveSearch} list.
     * Assigns the text in the text field to the chosen {@link Country}'s name.
     */
    @FXML
    public void handleListViewChoice ()
    {   
        try
        {
            searchField.setText(autoSearchField.getSelectionModel().getSelectedItem().toString());
            handlePartialSearch();
        }
        catch (NullPointerException npe) { }
    }
    
    @FXML
    public void handleHomeClicked ()
    {
        mainApp.showMainScreen();
    }
}
