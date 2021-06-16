package comp3350.AAS.business;

import java.util.ArrayList;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.Question;

public class Calculate {
    double quizGrade;


//    public double calculateGrade (){
//
//    }

    public boolean isCorrectAnswer(Question question, String selectedAnswer) {
        boolean isCorrect = false;

        if (selectedAnswer.equals(question.getKey())) {
            isCorrect = true;
        }

        return isCorrect;
    }

    public void updateGrade(Quiz selectedQuiz, Question selectedQuestion, String selectedAnswer) {
        double currentGrade = selectedQuiz.getQuizResult();
        if (isCorrectAnswer(selectedQuestion, selectedAnswer)) {
            currentGrade++;
        }
        selectedQuiz.setQuizResult(currentGrade);
    }

    public String[] getCompletedQuizzes(ArrayList<Quiz> allQuizzes) {
        String[] completedQuizzes = new String[allQuizzes.size()];
        for (int i = 0; i < allQuizzes.size(); i++) {
            if (allQuizzes.get(i).isComplete()) {
                completedQuizzes[i] = allQuizzes.get(i).getQuizName();
            }
        }
        return completedQuizzes;
    }

    public String numberCompletedQuizzes(ArrayList<Quiz> allQuizzes) {
        int numberCompleted = 0;
        for (int i = 0; i < allQuizzes.size(); i++) {
            if (allQuizzes.get(i).isComplete()) {
                numberCompleted++;
            }
        }
        return "" + numberCompleted;
    }

    public String getAverageGrade(ArrayList<Quiz> allQuizzes) {
        double totalGrade = 0;
        int numCompleted = 0;
        for (int i = 0; i < allQuizzes.size(); i++) {
            if (allQuizzes.get(i).isComplete()) {
                totalGrade += allQuizzes.get(i).getQuizResult() / allQuizzes.get(i).getQuestionCount();
                numCompleted++;
            }
        }
        double averageGrade = totalGrade / numCompleted;
        return "" + averageGrade * 100;
    }

    public String getLowestGrade(ArrayList<Quiz> allQuizzes) {
        double lowestGrade = Double.POSITIVE_INFINITY;
        for (int i = 0; i < allQuizzes.size(); i++) {
            if (allQuizzes.get(i).isComplete() && allQuizzes.get(i).getQuizResult() / allQuizzes.get(i).getQuestionCount() < lowestGrade) {
                lowestGrade = allQuizzes.get(i).getQuizResult() / allQuizzes.get(i).getQuestionCount();
            }
        }
        return "" + lowestGrade * 100;
    }

    public String getHighestGrade(ArrayList<Quiz> allQuizzes) {
        double highestGrade = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < allQuizzes.size(); i++) {
            if (allQuizzes.get(i).isComplete() && allQuizzes.get(i).getQuizResult() / allQuizzes.get(i).getQuestionCount() > highestGrade) {
                highestGrade = allQuizzes.get(i).getQuizResult() / allQuizzes.get(i).getQuestionCount();
            }
        }
        return "" + highestGrade * 100;
    }
}
