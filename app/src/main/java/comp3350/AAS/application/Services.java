package comp3350.AAS.application;

import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.DataAccessObject;
import comp3350.AAS.database.DataAccessStub;


public class Services {
    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName){
        if(Main.dbUsage.equals("real")) {
            if (dataAccessService == null) {
                dataAccessService = new DataAccessObject(dbName);
                dataAccessService.open(Main.getDbPathName());
            }
            return dataAccessService;
        }else if(Main.dbUsage.equals("stub")){
            if(dataAccessService == null){
                dataAccessService = new DataAccessStub("stubDB");
                dataAccessService.open("stubDB");
            }
            return dataAccessService;
        }
        return null;
    }

    public static DataAccess getDataAccess(String dbName) {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
