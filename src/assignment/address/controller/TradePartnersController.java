package assignment.address.controller;

import assignment.address.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 * Controller class to enable the user to view all the {@link Country}'s trade partners.
 * @author Daniel Byers | 13121312
 */
public class TradePartnersController extends Controller
{
    @FXML private Label lblTitle;
    @FXML private TilePane tradePartnerTiles;
    @FXML private Button btnHome;
    
    private Country country;
    
    /**
     * Sets the {@link Country} that has been chosen by the user.
     * First set's the label text to the associated {@link Country} name, then retrieves a list of all of the 
     * countries that trade with the chosen {@link Country}. Once it has the list, it iterates through each
     * element and creates a {@link Button} that resembles a {@link Country} that when clicked will take
     * the user to the country information screen.
     * @param searchCountry {@link Country} passed in from the {@link SearchScreenController}.
     */
    public void setCountry (Country searchCountry)
    {
        country = searchCountry;
        lblTitle.setText(country.getName() + "'s Trade Partners:");
        ObservableList<String> tradePartners = FXCollections.observableList(mainApp.getAvlTree().RetrievePartners(country.getName()));
        
        for (String s : tradePartners)
        {           
            Country searchResult = mainApp.getAvlTree().RetrieveCountryByName(s);
            Button btn = new Button();
            btn.setText(searchResult.getName());
            btn.setMaxHeight(Double.MAX_VALUE);
            btn.setMaxWidth(Double.MAX_VALUE);
            tradePartnerTiles.getChildren().add(btn);            
            
            btn.setOnAction(new EventHandler<ActionEvent> ()
            {
                @Override
                public void handle (ActionEvent event)
                {       
                    mainApp.showCountryInfoScreen(searchResult);
                }
            });       
            
        }
    }
    
    /**
     * Called when the user clicks the 'Home' button.
     * Shows the main screen.
     */
    @FXML
    public void handleOnHomeButtonClick ()
    {
        mainApp.showMainScreen();
    }
}
