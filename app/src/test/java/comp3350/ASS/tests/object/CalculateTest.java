package comp3350.ASS.tests.object;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.business.Calculate;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class CalculateTest extends TestCase{
    private Calculate calculate ;
    private ArrayList<Question> questionList;
    private Question question;
    private ArrayList<Quiz> quizList;
    private Quiz quiz;

    public CalculateTest(String arg0){
        super(arg0);
    }

    public void setUp(){


    }
    public void testOneRight(){
        quizList = new ArrayList<>();
        calculate = new Calculate();
        quiz = new Quiz("quiz");
        quiz.setCompleteStatus(true);
        quiz.setQuizResult(1);
        quiz.addQuestion(question);
        quizList.add(quiz);

        assertEquals("1",calculate.numberCompletedQuizzes(quizList));
        assertEquals("100.00%",calculate.getAverageGrade(quizList));
        assertEquals("100.00%",calculate.getHighestGrade(quizList));
        assertEquals("100.00%",calculate.getLowestGrade(quizList));

    }
    public void testOneWrong(){
        quizList = new ArrayList<>();
        calculate = new Calculate();
        quiz = new Quiz("quiz");
        quiz.setCompleteStatus(true);
        quiz.setQuizResult(0);
        quiz.addQuestion(question);

        quizList.add(quiz);

        assertEquals("1",calculate.numberCompletedQuizzes(quizList));
        assertEquals("0.00%",calculate.getAverageGrade(quizList));
        assertEquals("0.00%",calculate.getHighestGrade(quizList));
        assertEquals("0.00%",calculate.getLowestGrade(quizList));

    }
    public void testMulQuestion(){
        quizList = new ArrayList<>();
        calculate = new Calculate();
        quiz = new Quiz("quiz");
        quiz.setCompleteStatus(true);
        quiz.setQuizResult(0);
        quiz.addQuestion(question);
        quizList.add(quiz);

        quiz.setCompleteStatus(true);
        quiz.setQuizResult(1);
        quiz.addQuestion(question);
        quizList.add(quiz);

        assertEquals("2",calculate.numberCompletedQuizzes(quizList));
        assertEquals("50.00%",calculate.getAverageGrade(quizList));
        assertEquals("100.00%",calculate.getHighestGrade(quizList));
        assertEquals("100.00%",calculate.getLowestGrade(quizList));

    }
















}
