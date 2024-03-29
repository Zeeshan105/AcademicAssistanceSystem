package comp3350.AAS.tests.integration;

import junit.framework.TestCase;
import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.tests.database.DataAccessStub;
import comp3350.AAS.tests.database.DataAccessTest;

public class DataAccessHSQLDBTest extends TestCase{
    private static String dbName = Main.dbName;

    public DataAccessHSQLDBTest(String arg0){
        super(arg0);
    }

    public void testDataAccess(){
        DataAccess dataAccess;
        Services.closeDataAccess();
        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        // run with the real and stub databases
        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess(dbName);
        DataAccessTest.dataAccessTest(dataAccess);
        Services.closeDataAccess();

        Services.createDataAccess(new DataAccessStub());
        dataAccess = Services.getDataAccess(dbName);
        DataAccessTest.dataAccessTest(dataAccess);
        Services.closeDataAccess();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }

}
