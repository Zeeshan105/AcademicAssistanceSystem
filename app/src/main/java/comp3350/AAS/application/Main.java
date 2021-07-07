package comp3350.AAS.application;

public class Main {
    //public static final String dbName="cardBase";
    public static final String dbName = "QQ";
    private static String dbPathName = "database/QQ";

    public static void main(String[] args){
        startUp();
        shutDown();
    }

    public static void startUp(){
        Services.createDataAccess(dbName);
    }


    public static void shutDown(){
        Services.closeDataAccess();
    }
    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }


}
