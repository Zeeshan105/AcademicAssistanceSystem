package comp3350.AAS.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.business.Validate;


public class ValidateTest extends TestCase{

    private Validate validate ;

    public ValidateTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        validate = new Validate();
    }

    public void testMcqInput(){
        assertFalse(validate.isValidMcqInput("","","","","",""));
        assertFalse(validate.isValidMcqInput(" ","","   "," ","","  "));
        assertFalse(validate.isValidMcqInput(" a","","   "," ","","  "));
        assertFalse(validate.isValidMcqInput("Valid Question","","","","",""));
        assertFalse(validate.isValidMcqInput("","Valid Option A","","","",""));
        assertFalse(validate.isValidMcqInput("Valid Question","","Valid Option B","","",""));
        assertFalse(validate.isValidMcqInput("Valid Question","Valid Option A","Valid Option B","Valid Option C","",""));
        assertFalse(validate.isValidMcqInput("Valid Question","Valid Option A","Valid Option B","Valid Option C","Valid Answer",""));
        assertFalse(validate.isValidMcqInput("Valid Question","Valid Option A","Valid Option B","Valid Option C","Valid Answer","       "));
        assertTrue(validate.isValidMcqInput("Valid Question","Valid Option A","Valid Option B","Valid Option C","Valid Option A","Valid Quiz Name"));
        assertTrue(validate.isValidMcqInput("Valid Question","Valid Option A","Valid Option B","Valid Option C","Invalid Answer","Valid Quiz Name"));
    }

    public void testTfInput(){
        //TODO
    }

    public void testValidAnswer(){
        assertTrue(validate.containsAnswer("Valid Option A","Valid Option B","Valid Option C","Valid Option A"));
        assertFalse(validate.containsAnswer("Valid Option A","Valid Option B","Valid Option C","Invalid Answer"));
    }

}
