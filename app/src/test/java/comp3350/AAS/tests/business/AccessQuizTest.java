package comp3350.AAS.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.tests.database.DataAccessStub;


public class AccessQuizTest extends TestCase {
    public AccessQuizTest(String arg0){
        super(arg0);
    }

    public void test(){
        AccessQuiz aq;
        ArrayList<Quiz> quizzes;
        ArrayList<Question>questions;
        Question question;

        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(Main.dbName));

        aq = new AccessQuiz();
        quizzes = aq.getQuizList();
        assertNotNull(quizzes);

        questions = quizzes.get(0).getQuestionList();
        assertNotNull(questions);
        assertEquals("Historical", quizzes.get(0).getQuizName());
        question=questions.get(0);
        assertEquals("Number of Canadians participating in World War II", question.getQuestion());
        assertEquals("A.0.5 million", question.getOption1());
        assertEquals("B.1 million", question.getOption2());
        assertEquals("C.2 million", question.getOption3());
        assertEquals("1 million", question.getKey());
        question=questions.get(1);
        assertEquals("The capital city of Canada is Vancouver", question.getQuestion());
        assertEquals("False", question.getKey());
        question=questions.get(2);
        assertEquals("The year of the founding of Canada is 1867", question.getQuestion());
        assertEquals("True", question.getKey());
        question=questions.get(3);
        assertEquals("What year was the founding of Canada?", question.getQuestion());
        assertEquals("A.1867", question.getOption1());
        assertEquals("B.1887", question.getOption2());
        assertEquals("C.1787", question.getOption3());
        assertEquals("1867", question.getKey());
        question=questions.get(4);
        assertEquals("Which aircraft manufacture belong to Canada?", question.getQuestion());
        assertEquals("A.Airbus", question.getOption1());
        assertEquals("B.Boeing", question.getOption2());
        assertEquals("C.Bombardier", question.getOption3());
        assertEquals("Bombardier", question.getKey());

        questions = quizzes.get(1).getQuestionList();
        assertNotNull(questions);
        assertEquals("Math", quizzes.get(1).getQuizName());
        question=questions.get(0);
        assertEquals("The positive result of square root of 16 is 3", question.getQuestion());
        assertEquals("False", question.getKey());
        question=questions.get(1);
        assertEquals("The positive result of square root of 25 is 5", question.getQuestion());
        assertEquals("True", question.getKey());
        question=questions.get(2);
        assertEquals("What is the positive result of square root of 36?", question.getQuestion());
        assertEquals("A.3", question.getOption1());
        assertEquals("B.6", question.getOption2());
        assertEquals("C.9", question.getOption3());
        assertEquals("6", question.getKey());
        question=questions.get(3);
        assertEquals("What is the result of 1+1?", question.getQuestion());
        assertEquals("A.0", question.getOption1());
        assertEquals("B.1", question.getOption2());
        assertEquals("C.2", question.getOption3());
        assertEquals("2", question.getKey());

        questions = quizzes.get(2).getQuestionList();
        assertNotNull(questions);
        assertEquals("Geographic", quizzes.get(2).getQuizName());
        question=questions.get(0);
        assertEquals("What is the area of Canada in square kilometers?", question.getQuestion());
        assertEquals("A.9.98 million", question.getOption1());
        assertEquals("B.9.60 million", question.getOption2());
        assertEquals("C.9.37 million", question.getOption3());
        assertEquals("9.98 million", question.getKey());
        question=questions.get(1);
        assertEquals("What is the capital city of Canada?", question.getQuestion());
        assertEquals("A.Vancouver", question.getOption1());
        assertEquals("B.Ottawa", question.getOption2());
        assertEquals("C.Toronto", question.getOption3());
        assertEquals("Ottawa", question.getKey());

        questions = quizzes.get(2).getQuestionList();
        assertNotNull(questions);
        assertEquals("Geographic", quizzes.get(2).getQuizName());
        question=questions.get(0);
        assertEquals("What is the area of Canada in square kilometers?", question.getQuestion());
        assertEquals("A.9.98 million", question.getOption1());
        assertEquals("B.9.60 million", question.getOption2());
        assertEquals("C.9.37 million", question.getOption3());
        assertEquals("9.98 million", question.getKey());
        question=questions.get(1);
        assertEquals("What is the capital city of Canada?", question.getQuestion());
        assertEquals("A.Vancouver", question.getOption1());
        assertEquals("B.Ottawa", question.getOption2());
        assertEquals("C.Toronto", question.getOption3());
        assertEquals("Ottawa", question.getKey());

        questions = quizzes.get(3).getQuestionList();
        assertNotNull(questions);
        assertEquals("add new Quiz 3", quizzes.get(3).getQuizName());
        question=questions.get(0);
        assertEquals("What is the population of Canada?", question.getQuestion());
        assertEquals("A.3759", question.getOption1());
        assertEquals("B.3770", question.getOption2());
        assertEquals("C.4029", question.getOption3());
        assertEquals("3759", question.getKey());
        question=questions.get(1);
        assertEquals("When Canada was founded?", question.getQuestion());
        assertEquals("A.1868", question.getOption1());
        assertEquals("B.1867", question.getOption2());
        assertEquals("C.1888", question.getOption3());
        assertEquals("1867", question.getKey());
    }


}
