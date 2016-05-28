package assignment.address;

import assignment.address.controller.CountryEditController;
import assignment.address.controller.CountryInfoController;
import assignment.address.controller.MainScreenController;
import assignment.address.controller.SearchScreenController;
import assignment.address.controller.TradePartnersController;
import assignment.address.controller.TraversalsScreenController;
import assignment.address.controller.WelcomeScreenController;
import assignment.address.model.AVLTree;
import assignment.address.model.Country;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main controller for the Application.
 * Contains the methods needed to pass information between the controllers
 * and create the user experience,
 * @author Daniel Byers | 13121312
 */
public class MainApp extends Application 
{    
    private Stage primaryStage;
    private AVLTree<Country> avlTree;   
    private String[] namesArray;
    private ObservableList<String> namesList;
    private Image ico;
    
    /**
     * To access the main stage from outside the App.
     * @return main stage
     */
    public Stage getStage () { return primaryStage; }
    
    /**
     * Public accessor to get the AVL Tree.
     * @return AVL Tree
     */
     public AVLTree<Country> getAvlTree()
     {
        return avlTree;
    }
    
     /**
     * Public accessor to set the AVL Tree.
     * @param avlTree Assign new AVL Tree as parameter value
     */
    public void setAVLTree(AVLTree<Country> avlTree)
    {
        this.avlTree = avlTree;
        System.out.println("AVL Tree saved");
    }
    
    /**
     * Public accessor to set the Names Array.
     * @param namesArray Array of the country names to be assigned
     */
    public void setNamesArray(String[] namesArray)
    {
        this.namesArray = namesArray; 
    }
    
    /**
     * Public accessor to get the Names Array.
     * @return Array of the country names as they currently exist
     */
    public ObservableList<String> getNamesList ()
    {
        return namesList;
    }
    
    /**
     * Method used to set the list of country names associated 
     * to the data that was added to the AVL Tree.
     * @param namesList Passed in to set the list of {@link Country} names.
     */
    public void setNamesList(ObservableList<String> namesList)
    {
        this.namesList = namesList;
    }
        
    /**
     * 'The main entry point for all JavaFX applications. 
     * The start method is called after the init method has returned, and after the 
     * system is ready for the application to begin running.'
     * - Oracle Documentation (https://docs.oracle.com/javafx/2/api/javafx/application/Application.html#start(javafx.stage.Stage))'
     * @param primaryStage Initial stage passed in from OS.
     * @throws Exception Some Exception
     * @see Exception
     */
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        ico = new Image(MainApp.class.getResource("view/pics/globe.png").toExternalForm());
        primaryStage.getIcons().add(ico);
        showWelcomeScreen();
    }
    
    /**
     * Displays the Welcome Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link AnchorPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link WelcomeScreenController}) 
     * is declared and initialised to control the user's interaction with the Welcome Screen.     * 
     */
    public void showWelcomeScreen ()
    {
        try
        {
            FXMLLoader welcomeLoader = new FXMLLoader();
            welcomeLoader.setLocation(MainApp.class.getResource("view/WelcomeScreenLayout.fxml"));
            AnchorPane myPane = (AnchorPane) welcomeLoader.load();
            
            primaryStage.setTitle("Welcome");          
            
            Scene scene = new Scene(myPane, 750, 300);                     
            primaryStage.setScene(scene);
            
            WelcomeScreenController wsc = (WelcomeScreenController) welcomeLoader.getController();
            wsc.setMainApp(this);            
            
            primaryStage.show();
            primaryStage.centerOnScreen();
            System.out.println("showWelcomeScreen: Success");
        }
         catch (IOException e) { System.out.println("showWelcomeScreen failed. Error: " + e.getMessage()); }
    }
    
    /**
     * Displays the Main Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link BorderPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link MainScreenController}) 
     * is declared and initialised to control the user's interaction with the Main Screen. The list of countries is
     * populated and chart data is set by calling the related functions from the controller.
     */
    public void showMainScreen ()
    {
        try
        {
            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(MainApp.class.getResource("view/MainLayout.fxml"));        
            BorderPane mainLayout = (BorderPane) mainLoader.load();
            
            MainScreenController mslc = (MainScreenController) mainLoader.getController();
            mslc.setMainApp(this);
            mslc.showCountries(namesList);
            mslc.setChartData();
            
            Scene mainScreen = new Scene(mainLayout, 1400, 800);     
            primaryStage.setScene(mainScreen);
            primaryStage.centerOnScreen();
            System.out.println("showMainScreen: Success");
        }
        catch (IOException e) { System.out.println("showMainScreen failed. Error:"); e.printStackTrace(); }
    }
    
    /**
     * Displays the Search Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link AnchorPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link SearchScreenController}) 
     * is declared and initialised to control the user's interaction with the Search Screen. The list of countries is
     * passed in as a parameter of setNamesList() to set the contents of the combo box in the Search Screen.
     */
    public void showSearchScreen ()
    {
        try
        {
            FXMLLoader searchLoader = new FXMLLoader();
            searchLoader.setLocation(MainApp.class.getResource("view/SearchScreenLayout.fxml"));
            AnchorPane searchLayout = (AnchorPane) searchLoader.load();
            
            SearchScreenController ssc = (SearchScreenController) searchLoader.getController();
            ssc.setMainApp(this);
            ssc.setNamesList(namesList);
            
            Scene searchSceen = new Scene(searchLayout, 1000, 500);
            primaryStage.setScene(searchSceen);
            primaryStage.centerOnScreen();
            System.out.println("showSearchScreen: Success");
        }
        catch (IOException e) { System.out.println("showSearchScreen failed. Error: " + e.getMessage()); }
    }
    
    /**
     * Displays the Country Information Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link AnchorPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link CountryInfoController}) 
     * is declared and initialised to control the user's interaction with the Country Information Screen. The
     * {@link Country} is passed in from the {@link SearchScreenController} as a result from the search.
     * @param searchCountry The country associated with the search parameter entered by the user
     */
    public void showCountryInfoScreen (Country searchCountry)
    {
        try
        {
            FXMLLoader countryInfoLoader = new FXMLLoader();
            countryInfoLoader.setLocation(MainApp.class.getResource("view/CountryInfoLayout.fxml"));
            AnchorPane CountryInfoLayout = (AnchorPane) countryInfoLoader.load();
            
            CountryInfoController cic = (CountryInfoController) countryInfoLoader.getController();
            cic.setMainApp(this);
            cic.setCountryInfo(searchCountry);
                        
            Scene CountryInfoScreen = new Scene(CountryInfoLayout);
            primaryStage.setScene(CountryInfoScreen);
            primaryStage.centerOnScreen();
            System.out.println("showCountryInfoScreen: Success");
        }
        catch (IOException e) { System.out.println("showCountryInfoScreen failed. Error: " + e.getMessage()); }
    }
    
    /**
     * Displays the Country Edit Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link AnchorPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link CountryEditController}) 
     * is declared and initialised to control the user's interaction with the Country Edit Screen. The
     * {@link Country} is passed in from the {@link CountryInfoController} when the user clicks the Edit button.
     * @param country The country associated with the information screen
     */
    public void showCountryEditDialog (Country country)
    {
        try
        {            
            FXMLLoader editLoader = new FXMLLoader();
            editLoader.setLocation(MainApp.class.getResource("view/CountryEditLayout.fxml"));
            AnchorPane editLayout = (AnchorPane) editLoader.load();
            Scene editScreen = new Scene(editLayout);
            
            CountryEditController cec = (CountryEditController) editLoader.getController();
            cec.setMainApp(this);
            cec.setCountry(country);
            
            Stage editDialog = new Stage();
            editDialog.setTitle("Edit Country Information");
            editDialog.initModality(Modality.WINDOW_MODAL);
            editDialog.initOwner(primaryStage);
            editDialog.setScene(editScreen);
                        
            cec.setStage(editDialog);
            
            editDialog.showAndWait();
            System.out.println("showEditDialog: Success");
        }
        catch (IOException e) { System.out.println("showEditDialog failed. I/O Error: " + e.getMessage()); }
    }
    
    /**
     * Displays the Trade Partners Screen.
     * Creates and initialises a {@link FXMLLoader} to get the layout from the XML file, then creates
     * a {@link AnchorPane} equal to it. Once that is done, a new  {@link Scene} is created and added 
     * to the primary {@link Stage}. An instance of the controller class ({@link TradePartnersController}) 
     * is declared and initialised to control the user's interaction with the Trade Partners Screen. The
     * {@link Country} is passed in from the {@link SearchScreenController} when the user clicks the Edit button.
     * @param searchCountry The country associated with the user's choice from the combo box on the search screen
     */
    public void showSearchByTradePartner (Country searchCountry)
    {
        try
        {            
            FXMLLoader searchtpLoader = new FXMLLoader();
            searchtpLoader.setLocation(MainApp.class.getResource("view/TradePartnersScreenLayout.fxml"));
            AnchorPane tradePartenersLayout = (AnchorPane) searchtpLoader.load();
            Scene tradePartnersScreen = new Scene(tradePartenersLayout);
            
            TradePartnersController tpc = (TradePartnersController) searchtpLoader.getController();
            tpc.setMainApp(this);
            tpc.setCountry(searchCountry);
            
            primaryStage.setScene(tradePartnersScreen);
            primaryStage.centerOnScreen();
            System.out.println("showEditDialog: Success");
        }
        catch (IOException e) { System.out.println("showEditDialog failed. I/O Error: " + e.getMessage()); }
    }
    
    /**
     * Opens the dialog box which displays the AVL Tree traversals.
     */
    public void displayTraversals ()
    {
        try
        {            
            FXMLLoader traversalLoader = new FXMLLoader();
            traversalLoader.setLocation(MainApp.class.getResource("view/TraversalsScreenLayout.fxml"));
            AnchorPane traversalLayout = (AnchorPane) traversalLoader.load();
            Scene traversalScreen = new Scene(traversalLayout);
            
            TraversalsScreenController tsc = (TraversalsScreenController) traversalLoader.getController();
            tsc.setMainApp(this);
            tsc.displayTraversals();
            
            Stage traversalsDialog = new Stage();
            traversalsDialog.setTitle("Traversals");
            traversalsDialog.initModality(Modality.WINDOW_MODAL);
            traversalsDialog.initOwner(primaryStage);
            traversalsDialog.setScene(traversalScreen);
            traversalsDialog.getIcons().add(ico);
                        
            tsc.setStage(traversalsDialog);
            traversalsDialog.showAndWait();
            
            System.out.println("showTraversalsDialog: Success");
        }
        catch (IOException e) { System.out.println("showTraversalsDialog. I/O Error: "); e.printStackTrace(); }
    }
    
    /**
     * Opens the information window.
     */
    public void displayAbout () 
    {
        try
        {            
            FXMLLoader aboutLoader = new FXMLLoader();
            aboutLoader.setLocation(MainApp.class.getResource("view/AboutScreenLayout.fxml"));
            AnchorPane aboutLayout = (AnchorPane) aboutLoader.load();
            Scene aboutlScreen = new Scene(aboutLayout);
            
            Stage aboutDialog = new Stage();
            aboutDialog.setTitle("About");
            aboutDialog.initModality(Modality.WINDOW_MODAL);
            aboutDialog.initOwner(primaryStage);
            aboutDialog.setScene(aboutlScreen);
            aboutDialog.getIcons().add(ico);
            aboutDialog.showAndWait();
            
            System.out.println("showTraversalsDialog: Success");
        }
        catch (IOException e) { System.out.println("showTraversalsDialog. I/O Error: "); e.printStackTrace(); }
    }
    
    /**
     * Closes the application.
     */
    public void exit ()
    {
        primaryStage.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
        launch(args);
    }
}
