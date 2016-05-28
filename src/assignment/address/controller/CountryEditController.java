package assignment.address.controller;

import assignment.address.model.Country;
import assignment.address.model.AVLTree;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class to allow the user to edit a {@link Country} in the {@link AVLTree}.
 *@author Daniel Byers | 13121312
 */
public class CountryEditController extends Controller
{
    @FXML private TextField txtName;
    @FXML private TextField txtGDPGrowth;
    @FXML private TextField txtInflation;
    @FXML private TextField txtTradeBalance;
    @FXML private TextField txtHDIRanking;
    @FXML private Label errorText;
    @FXML private TextArea txtTradePartners;
    
    private Country country;
    private Stage editDialog;
    final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    
    /**
     * Sets the {@link Stage} for the Edit Country Screen window.
     * @param editDialog The stage that contains the edit dialog.
     */
    public void setStage (Stage editDialog)
    {
        this.editDialog = editDialog;
    }
    
    /**
     * Sets the {@link Country} that is to be edited.
     * @param country {@link Country} passed in from the {@link CountryInfoController}
     */
    public void setCountry (Country country)
    {
        this.country = country;
        txtName.setText(country.getName());
        txtGDPGrowth.setText(Double.toString(country.getGDPGrowth()));
        txtInflation.setText(Double.toString(country.getInflation()));
        txtTradeBalance.setText(Double.toString(country.getTradeBalance()));
        txtHDIRanking.setText(Integer.toString(country.getHDIRanking()));
        for (String s : country.getTradePartners())
            txtTradePartners.appendText(s + ",");
    }
    
    /**
     * Validates the user's changes to the {@link Country} information.
     * @return True if input is valid, false if not.
     */
    private boolean valid ()
    {
        TextField [] tfArray = { txtName, txtGDPGrowth, txtInflation, txtTradeBalance, txtHDIRanking };
        boolean valid = true;
        
        for (TextField tf : tfArray) {
            if (tf.getText() == null || tf.getText().trim().length() == 0) {
                tf.pseudoClassStateChanged(errorClass, true);
            valid = false;
            }                
        }
        
        if (txtTradePartners.getText() == null || txtTradePartners.getText().trim().length() == 0)
        {
            txtTradePartners.pseudoClassStateChanged(errorClass, true);
            valid = false;
        }
        
        txtName.setText(txtName.getText().substring(0, 1).toUpperCase() + txtName.getText().substring(1));
        
        String[] tmpArray = txtTradePartners.getText().split(",");
        String tmpStr = "";
        for (String s : tmpArray) {
            tmpStr += (s.substring(0 , 1).toUpperCase() + s.substring(1)) + ",";
        }
        txtTradePartners.setText(tmpStr);
        
        return valid;
    }
    
    /**
     * Called when the user clicks the OK button on the dialog window.
     * Checks to see if the user input passed the validation, and if so assigns the values
     * provided by the user to the corresponding members of the {@link Country}.
     */
    @FXML
    private void handleOnOkButton ()
    {
        if (valid())
        {
            mainApp.getNamesList().set(mainApp.getNamesList().indexOf(country.getName()), txtName.getText());
            
            country.setName(txtName.getText());
            country.setGDPGrowth(Double.parseDouble(txtGDPGrowth.getText()));
            country.setInflation(Double.parseDouble(txtInflation.getText()));
            country.setTradeBalance(Double.parseDouble(txtTradeBalance.getText()));
            country.setHDIRanking(Integer.parseInt(txtHDIRanking.getText()));
            country.setTradePartners(new LinkedList<>(Arrays.asList(txtTradePartners.getText().replaceAll(" ", "").split(","))));       
                        
            mainApp.showCountryInfoScreen(country);            
            editDialog.close();
        }
        else
            errorText.setVisible(true);
    }
    
    /**
     * Called when the user clicks the Cancel button on the dialog window.
     * Closes the window.
     */
    @FXML
    private void handleOnCancelButton ()
    {
        editDialog.close();
    }
}
