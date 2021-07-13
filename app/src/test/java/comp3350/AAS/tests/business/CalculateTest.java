package comp3350.AAS.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.AAS.business.Calculate;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class CalculateTest extends TestCase{
    private Calculate calculate ;
    private Question question;
    private ArrayList<Quiz> quizList;
    private Quiz quiz;

    public CalculateTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        quizList = new ArrayList<>();
        calculate = new Calculate();
    }

    public void testIsCorrectQuestion(){
        question=new Question("1+1=?", "0", "1", "2", "2");
        assertFalse(calculate.isCorrectAnswer(question, "0"));
        assertFalse(calculate.isCorrectAnswer(question, "1"));
        assertTrue(calculate.isCorrectAnswer(question, "2"));

        question=new Question("10+1=?", "0", "11", "12", "11");
        assertFalse(calculate.isCorrectAnswer(question, "0"));
        assertTrue(calculate.isCorrectAnswer(question, "11"));
        assertFalse(calculate.isCorrectAnswer(question, "12"));

        question=new Question("(-1)+1=?", "0", "2", "4", "0");
        assertTrue(calculate.isCorrectAnswer(question, "0"));
        assertFalse(calculate.isCorrectAnswer(question, "2"));
        assertFalse(calculate.isCorrectAnswer(question, "4"));
    }

    public void testUpdateGradeAllCorrect(){
        quiz=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz, question1, "2");
        assertEquals(1.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question2, "1");
        assertEquals(2.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question3, "0");
        assertEquals(3.0, quiz.getQuizResult());
    }

    public void testUpdateGradePartialCorrect(){
        quiz=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz, question1, "2");
        assertEquals(1.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question2, "2");
        assertEquals(1.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question3, "0");
        assertEquals(2.0, quiz.getQuizResult());
    }

    public void testUpdateGradeNonCorrect(){
        quiz=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz, question1, "0");
        assertEquals(0.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question2, "2");
        assertEquals(0.0, quiz.getQuizResult());

        calculate.updateGrade(quiz, question3, "1");
        assertEquals(0.0, quiz.getQuizResult());
    }

}
