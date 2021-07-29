package comp3350.AAS.business;

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

}
