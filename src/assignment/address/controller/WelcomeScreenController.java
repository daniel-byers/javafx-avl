package assignment.address.controller;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller to greet the user and allow them to import a comma separated valued file.
 *@author Daniel Byers | 13121312
 */
public class WelcomeScreenController extends Controller
{
    @FXML
    private Text welcomeText;
    @FXML
    private Text importText;
    @FXML
    private Button importButton;
    private Stage prevStage;
    
    /**
    * Called when the user clicks the 'Import' button.
    * Opens a {@link FileChooser} to obtain a file path, which is then used to parse a .csv file
    * for data. Once that file path has been determined, then the {@link CSVReaderController#openFIle(java.lang.String) openFile}
    * method is called, passing in the file path as it's parameter. On success, the main screen is shown.
    */
    @FXML
    private void handleImportButtonAction ()
    {
        FileChooser openFile = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        openFile.getExtensionFilters().add(filter);
        openFile.setTitle("Open a CSV File.");
        openFile.setInitialDirectory(new File("./"));
        File file = openFile.showOpenDialog(mainApp.getStage());        
        if (file != null)
        {
            String file_path = file.getAbsolutePath();
            CSVReaderController reader = new CSVReaderController();
            mainApp.setAVLTree(reader.openFIle(file_path));
            mainApp.setNamesArray(reader.getSortedArray());
            mainApp.setNamesList(reader.getList());
            mainApp.showMainScreen();
        }
    }
}
