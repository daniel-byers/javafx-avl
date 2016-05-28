package assignment.address.controller;

import assignment.address.MainApp;

/**
 * Controller superclass.
 * @author Daniel Byers | 13121312
 */
public class Controller
{    
    protected MainApp mainApp;
    
    /**
    * Creates a link to main application controller so references can be made.
    * @param mainApp passed in from Main
    */      
    public void setMainApp (MainApp mainApp)
    {
        this.mainApp = mainApp;
    }
}
