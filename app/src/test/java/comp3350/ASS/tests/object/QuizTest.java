package comp3350.ASS.tests.object;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class QuizTest extends TestCase {
    private ArrayList<Question> questionList;
    private Question question;
    private Quiz quiz;

    public QuizTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        quiz=new Quiz("Quiz Name");
        questionList=new ArrayList<>();
        question=new Question("1+1=", "1", "2", "3", "2");
        assertNotNull(questionList);
        assertNotNull(question);
    }

    public void testNoQuestion(){
        assertEquals(0, questionList.size());
    }

    public void testOneQuestion(){
        questionList.add(question);
        assertEquals(1, questionList.size());
    }

    public void testTwoQuestion(){
        questionList.add(question);
        assertEquals(1, questionList.size());
        questionList.add(question);
        assertEquals(2, questionList.size());
    }


}
