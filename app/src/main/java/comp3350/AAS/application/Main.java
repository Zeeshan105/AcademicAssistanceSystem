package comp3350.AAS.application;

public class Main {
    public static final String dbUsage = "real";   //which database to use, "real" or "stub"
    public static final String dbName = "DB";
    private static String dbPathName = "database/DB";


    public static void main(String[] args){
        startUp();
        System.out.println("Program started....");
        //shutDown();
    }

    public static void startUp(){
        //Dependency Injection - There are 2 createDataAccess methods
        //each with a different signature.
        //You can switch between the real and stub dataBase by
        //providing the signature for the other createDataAccess method
        Services.createDataAccess(dbName);
    }

    public static void shutDown(){
        Services.closeDataAccess();
    }

    public static String getDbPathName() {
        if (dbPathName == null) {
            return dbName;
        } else {
            return dbPathName;
        }
    }

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
