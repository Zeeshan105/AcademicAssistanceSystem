package comp3350.ASS.tests.object;

import junit.framework.TestCase;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class QuizTest extends TestCase {
    private Question question;
    private Quiz quiz;

    public QuizTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        quiz=new Quiz("Quiz Name");
        assertNotNull(quiz);

        question=new Question("1+1=", "1", "2", "3", "2");
        assertNotNull(question);
    }

    public void testNoQuestion(){
        assertEquals(0, quiz.getQuestionCount());
    }

    public void testOneQuestion(){
        quiz.addQuestion(question);
        assertEquals(1, quiz.getQuestionCount());
    }

    public void testTwoQuestion(){
        quiz.addQuestion(question);
        assertEquals(1, quiz.getQuestionCount());
        quiz.addQuestion(question);
        assertEquals(2, quiz.getQuestionCount());
    }

    public void testCompleted(){
        quiz.setCompleteStatus(true);
        assertTrue(quiz.isComplete());
    }

    public void testNotCompleted(){
        quiz.setCompleteStatus(false);
        assertFalse(quiz.isComplete());
    }

    public void testScoreResult(){
        quiz.setQuizResult(0.0);
        assertEquals (0.0, quiz.getQuizResult());

        quiz.setQuizResult(-1.0);
        assertEquals (0.0, quiz.getQuizResult());

        quiz.setQuizResult(99.86);
        assertEquals (99.86, quiz.getQuizResult());
    }

    public void testQuizName(){
        quiz=new Quiz("Science Quiz");
        assertNotNull(quiz);
        assertEquals("Science Quiz", quiz.getQuizName());

        quiz=new Quiz("1234");
        assertNotNull(quiz);
        assertEquals("1234", quiz.getQuizName());

        quiz=new Quiz("");
        assertNotNull(quiz);
        assertEquals("", quiz.getQuizName());

        quiz=new Quiz(null);
        assertNotNull(quiz);
        assertNull(quiz.getQuizName());
    }

}
