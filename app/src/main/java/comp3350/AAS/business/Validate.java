package comp3350.AAS.business;


public class Validate {

    public boolean isValidMcqInput(String question, String optionA, String optionB, String optionC, String answer, String quizName){
        if(question.trim().isEmpty() || optionA.trim().isEmpty() || optionB.trim().isEmpty() || optionC.trim().isEmpty() || answer.trim().isEmpty() || quizName.trim().isEmpty())
            return false;
        else
            return true;
    }

    public boolean isValidCardInput(String cardTitle, String description, String folderName){
        if(cardTitle.trim().isEmpty() || description.trim().isEmpty() || folderName.trim().isEmpty())
            return false;
        else
            return true;
    }

    public boolean isValidCardInput(String cardTitle, String description){
        if(cardTitle.trim().isEmpty() || description.trim().isEmpty())
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

    public boolean isValidTrueOrFalseInput(String question, String answer, String quizName){
        if(question.trim().isEmpty() || answer.trim().isEmpty() || quizName.trim().isEmpty())
            return false;
        else
            return true;
    }
}
