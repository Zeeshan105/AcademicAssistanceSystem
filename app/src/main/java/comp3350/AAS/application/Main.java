package comp3350.AAS.application;


import android.util.Log;

public class Main {
    //public static final String dbName="cardBase";
    public static final String dbName = "DB";
    private static String dbPathName = "database/DB";


    public static void main(String[] args){
        startUp();
        //shutDown();
    }

    public static void startUp(){
        Log.i("main","before startup");
        Services.TempDataAccess(dbName);
        Services.createDataAccess("cardBase");
        Services.createQuizDataAccess("QuizBase");
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

}
