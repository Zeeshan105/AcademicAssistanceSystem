package comp3350.AAS.application;

public class Main {
    public static final String dbName="cardBase";

    public static void main(String[] args){
        startUp();
    }

    public static void startUp(){
        services.createDataAccess(dbName);
    }


}
