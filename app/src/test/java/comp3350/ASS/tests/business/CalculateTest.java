package comp3350.ASS.tests.business;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.AAS.business.Calculate;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class CalculateTest extends TestCase{
    private Calculate calculate ;
    private Question question;
    private ArrayList<Quiz> quizList;
    private Quiz quiz1, quiz2, quiz3, quiz4;

    public CalculateTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        quizList = new ArrayList<>();
        calculate = new Calculate();
    }

    public void testCompletedQuiz(){
        assertEquals("0", calculate.numberCompletedQuizzes(quizList));

        quiz1=new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quizList.add(quiz1);
        assertEquals("1", calculate.numberCompletedQuizzes(quizList));

        quiz2=new Quiz("quiz2");
        quiz2.setCompleteStatus(true);
        quizList.add(quiz2);
        assertEquals("2", calculate.numberCompletedQuizzes(quizList));

        quiz3=new Quiz("quiz3");
        quiz3.setCompleteStatus(true);
        quizList.add(quiz3);
        assertEquals("3", calculate.numberCompletedQuizzes(quizList));
    }

    public void testOneZeroGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("0.00%", calculate.getAverageGrade(quizList));
        assertEquals("0.00%", calculate.getHighestGrade(quizList));
        assertEquals("0.00%", calculate.getLowestGrade(quizList));
    }

    public void testOneTypicalGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(0.6);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("60.00%", calculate.getAverageGrade(quizList));
        assertEquals("60.00%", calculate.getHighestGrade(quizList));
        assertEquals("60.00%", calculate.getLowestGrade(quizList));
    }

    public void testOneFullGrade(){
        quiz1 = new Quiz("quiz1");
        quiz1.setCompleteStatus(true);
        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("100.00%", calculate.getAverageGrade(quizList));
        assertEquals("100.00%", calculate.getHighestGrade(quizList));
        assertEquals("100.00%", calculate.getLowestGrade(quizList));
    }

    public void testTwoZeroQuizGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("0.00%", calculate.getAverageGrade(quizList));
        assertEquals("0.00%", calculate.getHighestGrade(quizList));
        assertEquals("0.00%", calculate.getLowestGrade(quizList));
    }

    public void testTwoTypicalGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(0.8);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0.5);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("65.00%", calculate.getAverageGrade(quizList));
        assertEquals("80.00%", calculate.getHighestGrade(quizList));
        assertEquals("50.00%", calculate.getLowestGrade(quizList));
    }

    public void testTwoFullGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);

        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(1);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        assertEquals("100.00%", calculate.getAverageGrade(quizList));
        assertEquals("100.00%", calculate.getHighestGrade(quizList));
        assertEquals("100.00%", calculate.getLowestGrade(quizList));
    }

    public void testMultiZeroQuizGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(0);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(0);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(0);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("0.00%", calculate.getAverageGrade(quizList));
        assertEquals("0.00%", calculate.getHighestGrade(quizList));
        assertEquals("0.00%", calculate.getLowestGrade(quizList));
    }

    public void testMultiTypicalGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(0.2);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(0.75);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(0.98);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(0.5);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("60.75%", calculate.getAverageGrade(quizList));
        assertEquals("98.00%", calculate.getHighestGrade(quizList));
        assertEquals("20.00%", calculate.getLowestGrade(quizList));
    }

    public void testMultiFullGrade(){
        quiz1=new Quiz("quiz1");
        quiz2=new Quiz("quiz2");
        quiz3=new Quiz("quiz3");
        quiz4=new Quiz("quiz4");

        quiz1.setCompleteStatus(true);
        quiz2.setCompleteStatus(true);
        quiz3.setCompleteStatus(true);
        quiz4.setCompleteStatus(true);

        quiz1.setQuizResult(1);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        quiz2.setQuizResult(1);
        quiz2.addQuestion(question);
        quizList.add(quiz2);

        quiz3.setQuizResult(1);
        quiz3.addQuestion(question);
        quizList.add(quiz3);

        quiz4.setQuizResult(1);
        quiz4.addQuestion(question);
        quizList.add(quiz4);

        assertEquals("100.00%", calculate.getAverageGrade(quizList));
        assertEquals("100.00%", calculate.getHighestGrade(quizList));
        assertEquals("100.00%", calculate.getLowestGrade(quizList));
    }

    public void testInvalidGrade(){
        quiz1=new Quiz("quiz1");

        quiz1.setCompleteStatus(true);

        quiz1.setQuizResult(-999);
        quiz1.addQuestion(question);
        quizList.add(quiz1);

        assertEquals("0.00%", calculate.getAverageGrade(quizList));
        assertEquals("0.00%", calculate.getHighestGrade(quizList));
        assertEquals("0.00%", calculate.getLowestGrade(quizList));
    }

    public void testIsCorrectQuestion(){

        question=new Question("1+1=?", "0", "1", "2", "2");
        assertTrue(calculate.isCorrectAnswer(question, "2"));

        question=new Question("10+1=?", "0", "11", "12", "11");
        assertTrue(calculate.isCorrectAnswer(question, "11"));

        question=new Question("(-1)+1=?", "0", "2", "4", "0");
        assertTrue(calculate.isCorrectAnswer(question, "0"));

    }

    public void testUpdateGradeAllCorrect(){
        quiz1=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz1, question1, "2");
        assertEquals(1.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question2, "1");
        assertEquals(2.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question3, "0");
        assertEquals(3.0, quiz1.getQuizResult());
    }

    public void testUpdateGradePartialCorrect(){
        quiz1=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz1, question1, "2");
        assertEquals(1.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question2, "2");
        assertEquals(1.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question3, "0");
        assertEquals(2.0, quiz1.getQuizResult());
    }

    public void testUpdateGradeNonCorrect(){
        quiz1=new Quiz("quiz1");
        Question question1=new Question("1+1=?", "0", "1", "2", "2");
        Question question2=new Question("1+0=?", "0", "1", "2", "1");
        Question question3=new Question("1+(-1)=?", "0", "1", "2", "0");

        calculate.updateGrade(quiz1, question1, "0");
        assertEquals(0.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question2, "2");
        assertEquals(0.0, quiz1.getQuizResult());

        calculate.updateGrade(quiz1, question3, "1");
        assertEquals(0.0, quiz1.getQuizResult());
    }

}
