package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.Question;

public class Calculate {
    // Helper method for updateGrade to update grades according to users answer.
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

    public String getAverageGrade(ArrayList<Quiz> allQuizzes) {
        double totalGrade = 0;
        int quizzesCompleted = 0;

        for (int i = 0; i < allQuizzes.size(); i++) {
            Quiz selectedQuiz = allQuizzes.get(i);

            if (selectedQuiz.isComplete()) {
                totalGrade += selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();
                quizzesCompleted++;
            }
        }

        double averageGrade = (totalGrade / quizzesCompleted) * 100.0;
        String rounded = String.format("%.2f", averageGrade);
        return rounded + "%";
    }

    public String getHighestGrade(ArrayList<Quiz> allQuizzes) {
        double highestGrade = Double.NEGATIVE_INFINITY; // Place holder value that is updated if there is a completed quiz.

        for (int i = 0; i < allQuizzes.size(); i++) {
            Quiz selectedQuiz = allQuizzes.get(i);
            double newGrade = selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();

            if (selectedQuiz.isComplete() && newGrade > highestGrade) {
                highestGrade = newGrade;
            }
        }

        String rounded = String.format("%.2f", highestGrade * 100.0);
        return rounded + "%";
    }

    public String getLowestGrade(ArrayList<Quiz> allQuizzes) {
        double lowestGrade = Double.POSITIVE_INFINITY; // Place holder value that is updated if there is a completed quiz.

        for (int i = 0; i < allQuizzes.size(); i++) {
            Quiz selectedQuiz = allQuizzes.get(i);
            double newGrade = selectedQuiz.getQuizResult() / selectedQuiz.getQuestionCount();

            if (allQuizzes.get(i).isComplete() && newGrade < lowestGrade) {
                lowestGrade = newGrade;
            }
        }

        String rounded = String.format("%.2f", lowestGrade * 100.0);
        return rounded + "%";
    }

}
