package assignment.address.controller;

import assignment.address.model.AVLTree;
import assignment.address.model.Country;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller class to allow the user to view information about any {@link Country} in the {@link AVLTree}.
 * @author Daniel Byers | 13121312
 */
public class CountryInfoController extends Controller
{
    @FXML private Label infoTitle;
    @FXML private Label countryName;
    @FXML private Label gdpGrowth;
    @FXML private Label inflation;
    @FXML private Label tradeBalance;
    @FXML private Label hdiRanking;
    @FXML private ListView mainTradePartners;
    
    private Country country;
    
    /**
     * Method to populate the fields on the GUI with the {@link Country} information.
     * Calls the public accessors on the {@link Country} returned from the search and uses that information
     * to populate the fields.
     * @param searchCountry Result from the search of the {@link AVLTree}, passed in from {@link SearchScreenController}
     */
    public void setCountryInfo (Country searchCountry)
    {
        country = searchCountry;
        
        countryName.setText(searchCountry.getName());
        gdpGrowth.setText(Double.toString(searchCountry.getGDPGrowth()));
        inflation.setText(Double.toString(searchCountry.getInflation()));
        tradeBalance.setText(Double.toString(searchCountry.getTradeBalance()));
        hdiRanking.setText(Integer.toString(searchCountry.getHDIRanking()));
        ObservableList<String> tradePartners =  FXCollections.observableList(searchCountry.getTradePartners());
        mainTradePartners.setItems(tradePartners);
    }
    
    /**
     * Called when the user clicks the Edit button on the GUI.
     * Calls the method from MainApp to display the edit dialog.
     */
    @FXML
    public void handleOnEditButtonClick ()
    {
        mainApp.showCountryEditDialog(country);
    }
    
    /**
     * Called when the user clicks the Remove button on the GUI.
     * Removes the {@link Country} from the {@link AVLTree} by calling the
     * {@link AVLTree#RemoveItem(java.lang.Comparable) RemoveItem} method, and then
     * removes the reference of it from the list of names. Finally shows the main screen.
     */
    @FXML
    public void handleOnRemoveButtonClick ()
    {       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Remove");
        alert.setHeaderText("You are about to delete " + country.getName() +  ".");
        alert.setContentText("This country cannot be added again manually. Are you sure you wish to delete it?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            mainApp.getAvlTree().RemoveItem(country);
            mainApp.getNamesList().remove(country.getName());
            mainApp.showMainScreen();
        }
    }
    
    /**
     * Called when the user clicks the Home button on the GUI.
     * Shows the main screen.
     */
    @FXML
    public void handleOnHomeButtonClick ()
    {
        mainApp.showMainScreen();
    }
}
