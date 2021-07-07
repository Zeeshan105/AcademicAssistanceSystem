package comp3350.AAS.application;

import comp3350.AAS.database.CardDataBase;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.QuizDatabase;
import comp3350.AAS.database.DataAccessObject;

public class Services {
    private  static DataAccess dataAccessService = null;

    private static CardDataBase cardDataAccessService = null;
    private static QuizDatabase quizDataAccessService = null;

    public static CardDataBase createDataAccess(String name){
        if(cardDataAccessService == null){
            cardDataAccessService = new CardDataBase();
        }
        return cardDataAccessService;
    }

    public static QuizDatabase createQuizDataAccess(String name){
        if(quizDataAccessService == null){
            quizDataAccessService = new QuizDatabase();
        }
        return quizDataAccessService;
    }
    public static DataAccess TempDataAccess(String dbName){
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }
    public static DataAccess getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
