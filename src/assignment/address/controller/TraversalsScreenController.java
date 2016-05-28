package assignment.address.controller;

import assignment.address.model.AVLTree;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller to display the traversals of the {@link AVLTree} in a dialog window.
 * @author Daniel Byers | 13121312
 */
public class TraversalsScreenController extends Controller
{
    private Stage traversalsDialog;
    
    @FXML private TextArea txtOutput;
    
     /**
     * Sets the {@link Stage} for the traversals window.
     * @param traversalsDialog  The stage that contains the traversals dialog.
     */
    public void setStage (Stage traversalsDialog)
    {
        this.traversalsDialog = traversalsDialog;
    }
    
    /**
     * Method to call the traversals of the {@link AVLTree}
     * {@link AVLTree#PreOrder() PreOrder}
     * {@link AVLTree#InOrder() InOrder}
     * {@link AVLTree#PostOrder() PostOrder}
     */
    public void displayTraversals ()
    {
        txtOutput.setText("PreOrder:\t" + mainApp.getAvlTree().PreOrder());
        txtOutput.appendText("\nInOrder:\t\t" + mainApp.getAvlTree().InOrder());
        txtOutput.appendText("\nPostOrder:\t" + mainApp.getAvlTree().PostOrder());
    }
}
