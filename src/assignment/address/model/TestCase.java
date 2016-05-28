package assignment.address.model;

import junit.framework.Test;

/**
 *
 * @author Daniel Byers | 13121312
 */

public abstract class TestCase implements Test { 
    
    private final String fName;
    
    public TestCase (String name) { 
        fName= name; 
    }

    public void run () {
        setUp(); 
        runTest(); 
        tearDown();
    }
    
    protected void runTest () { 
        
    }
    
    protected void setUp () { 
        
    }
    
    protected void tearDown () { 
    
    }
}


