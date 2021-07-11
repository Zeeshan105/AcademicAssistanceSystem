package comp3350.AAS.application;

public class services {

    private static CardDataBase dataAccessService = null;
    private static QuizDatabase quizDataAccessService = null;

    public static CardDataBase createDataAccess(String name){
        if(dataAccessService == null){
            dataAccessService = new CardDataBase();
        }
        return dataAccessService;
    }

    public static QuizDatabase createQuizDataAccess(String name){
        if(quizDataAccessService == null){
            quizDataAccessService = new QuizDatabase();
        }
        return quizDataAccessService;
    }
}
