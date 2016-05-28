package assignment.address.controller;

import assignment.address.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller to populate of the main screen. 
 * @author Daniel Byers | 13121312
 */
public class MainScreenController extends Controller
{
    private final CountryChartDataController ccdc = new CountryChartDataController();
    final private ObservableList<String> countryNames = FXCollections.observableArrayList();
    
    @FXML private Label countryWithHighestPotential;
    @FXML private BarChart barChart;
    @FXML private CategoryAxis xAxis;  
    @FXML private Label countryLabel;
    @FXML private ListView countryList;
    @FXML private Label numOfCountries;
    @FXML private Label depthOfTree;
    @FXML private Label biggestTradePotential;
        
    /**
     * Method to populate the list of countries and set the details.
     * @param namesList List of {@link Country} names passed in to populate labels/list.
     */
    public void showCountries (ObservableList<String> namesList)
    {        
        countryList.setItems(namesList);
        numOfCountries.setText(Integer.toString(mainApp.getAvlTree().NumberOfNodes()));
        depthOfTree.setText(Integer.toString(mainApp.getAvlTree().DepthOfTree()));
        biggestTradePotential.setText(mainApp.getAvlTree().BiggestTradePotential().getName());
    }
    
    /**
     * Method to control the population and display of the {@link BarChart}.
     */
    public void setChartData ()
    {
        countryNames.addAll(mainApp.getNamesList());
        xAxis.setCategories(countryNames);
        ccdc.setMainApp(mainApp);
        barChart.getData().add(ccdc.getChartData());
    }
    
    /**
     * Called when the user clicks the Search button.
     * Opens the Search screen.
     */
    @FXML
    public void handleOnSearchButtonClicked ()
    {
        mainApp.showSearchScreen();
    }    
    
    /**
     * Called when the user clicks the menu item 'Tools' then 'Traversals'.
     * Displays the traversals dialog window.
     */
    @FXML
    public void handleOnTraversalsClicked ()
    {
        mainApp.displayTraversals();
    }
    
    /**
     * Called when the user clicks the Close button.
     * Exits the application.
     */
    @FXML
    public void handleOnCloseClicked ()
    {
        mainApp.exit();
    }
    
    /**
     * Called when the user clicks the About button.
     * Displays the information page.
     */
    @FXML
    public void handleOnAboutClicked ()
    {
        mainApp.displayAbout();
    }
}
