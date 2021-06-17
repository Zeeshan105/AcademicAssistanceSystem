package comp3350.AAS.application;

import comp3350.AAS.database.CardDataBase;
import comp3350.AAS.database.QuizDatabase;

public class Services {

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
}
