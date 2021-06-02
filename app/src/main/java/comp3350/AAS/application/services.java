package comp3350.AAS.application;

import comp3350.AAS.database.cardDataBase;

public class services {

    private static cardDataBase dataAccessService = null;

    public static cardDataBase createDataAccess(String name){
        if(dataAccessService == null){
            dataAccessService = new cardDataBase();
        }
        return dataAccessService;
    }
}
