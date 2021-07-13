package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.Question;

public class Validate {

    public boolean isValidInput(String question, String optionA, String optionB, String optionC, String answer, String quizName){
        if(question.trim().isEmpty() || optionA.trim().isEmpty() || optionB.trim().isEmpty() || optionC.trim().isEmpty() || answer.trim().isEmpty() || quizName.trim().isEmpty())
            return false;
        else
            return true;
    }

    public boolean containsAnswer(String optionA, String optionB, String optionC, String answer){
        if (answer.equals(optionA) || answer.equals(optionB) || answer.equals(optionC))
            return true;
        else
            return false;
    }

}
