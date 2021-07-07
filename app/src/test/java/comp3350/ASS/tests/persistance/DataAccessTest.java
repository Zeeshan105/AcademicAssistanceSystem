package comp3350.ASS.tests.persistance;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import comp3350.AAS.application.Main;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.DataAccessObject;

public class DataAccessTest extends TestCase {
    private DataAccess dataAccess;

    public DataAccessTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        //System.out.println("\nStarting Persistence test DataAccess (using stub)");
        //dataAccess = new DataAccessStub();
        //dataAccess.open("Stub");

        System.out.println("\nStarting Persistence test DataAccess (using HSQLDB)");
         dataAccess = new DataAccessObject(Main.dbName);
         dataAccess.open(Main.getDBPathName());
    }

    public void tearDown() {
        //System.out.println("Finished Persistence test DataAccess (using stub)");

        dataAccess.close();
        System.out.println("Finished Persistence test DataAccess (using HSQLDB)");
    }

    public void test1() {

    }
}
