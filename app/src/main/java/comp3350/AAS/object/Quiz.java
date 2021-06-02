package comp3350.AAS.object;

public class Quiz {
    private String quizQuestion;
    private String quizOption1;
    private String quizOption2;
    private String quizOption3;
    private String quizKey;

    public Quiz(String question, String option1, String option2, String option3, String key){
        quizQuestion=question;
        quizOption1=option1;
        quizOption2=option2;
        quizOption3=option3;
        quizKey=key;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }
    public String getQuizOption1() {
        return quizOption1;
    }
    public String getQuizOption2() {
        return quizOption2;
    }
    public String getQuizOption3() {
        return quizOption3;
    }
    public String getQuizKey() {
        return quizKey;
    }
}
