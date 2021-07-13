package comp3350.AAS.tests.object;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;


public class QuizTest extends TestCase {
    private Question question;
    private Quiz quiz;
    private ArrayList<Quiz>quizArrayList;

    public QuizTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        quiz=new Quiz("Quiz Name");
        assertNotNull(quiz);

        question=new Question("1+1=", "1", "2", "3", "2");
        assertNotNull(question);

        quizArrayList=new ArrayList<>();
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

    public void testMultiQuizName(){
        quiz=new Quiz("Science Quiz");
        assertNotNull(quiz);
        quizArrayList.add(quiz);
        assertEquals("Science Quiz", quizArrayList.get(0).getQuizName());

        quiz=new Quiz("1234");
        assertNotNull(quiz);
        quizArrayList.add(quiz);
        assertEquals("1234", quizArrayList.get(1).getQuizName());

        quiz=new Quiz("");
        assertNotNull(quiz);
        quizArrayList.add(quiz);
        assertEquals("", quizArrayList.get(2).getQuizName());

        quiz=new Quiz(null);
        assertNotNull(quiz);
        quizArrayList.add(quiz);
        assertNull(quizArrayList.get(3).getQuizName());
    }

    public void testMultiQuizQuestion(){
        quiz=new Quiz("Folder_1");
        quizArrayList.add(quiz);
        quiz=new Quiz("Folder_2");
        quizArrayList.add(quiz);

        question=new Question("Question1", "A", "B", "C", "A");
        quizArrayList.get(0).addQuestion(question);
        assertEquals(1, quizArrayList.get(0).getQuestionCount());
        assertEquals("Question1", quizArrayList.get(0).getQuestionList().get(0).getQuestion());
        assertEquals("A", quizArrayList.get(0).getQuestionList().get(0).getOption1());
        assertEquals("B", quizArrayList.get(0).getQuestionList().get(0).getOption2());
        assertEquals("C", quizArrayList.get(0).getQuestionList().get(0).getOption3());
        assertEquals("A", quizArrayList.get(0).getQuestionList().get(0).getKey());


        question=new Question("Question2", "A", "B", "C", "A");
        quizArrayList.get(1).addQuestion(question);
        question=new Question("Question3", "A", "B", "C", "B");
        quizArrayList.get(1).addQuestion(question);
        question=new Question("Question4", "A", "B", "C", "C");
        quizArrayList.get(1).addQuestion(question);

        assertEquals(3, quizArrayList.get(1).getQuestionCount());

        assertEquals("Question2", quizArrayList.get(1).getQuestionList().get(0).getQuestion());
        assertEquals("A", quizArrayList.get(1).getQuestionList().get(0).getOption1());
        assertEquals("B", quizArrayList.get(1).getQuestionList().get(0).getOption2());
        assertEquals("C", quizArrayList.get(1).getQuestionList().get(0).getOption3());
        assertEquals("A", quizArrayList.get(1).getQuestionList().get(0).getKey());

        assertEquals("Question3", quizArrayList.get(1).getQuestionList().get(1).getQuestion());
        assertEquals("A", quizArrayList.get(1).getQuestionList().get(1).getOption1());
        assertEquals("B", quizArrayList.get(1).getQuestionList().get(1).getOption2());
        assertEquals("C", quizArrayList.get(1).getQuestionList().get(1).getOption3());
        assertEquals("B", quizArrayList.get(1).getQuestionList().get(1).getKey());

        assertEquals("Question4", quizArrayList.get(1).getQuestionList().get(2).getQuestion());
        assertEquals("A", quizArrayList.get(1).getQuestionList().get(2).getOption1());
        assertEquals("B", quizArrayList.get(1).getQuestionList().get(2).getOption2());
        assertEquals("C", quizArrayList.get(1).getQuestionList().get(2).getOption3());
        assertEquals("C", quizArrayList.get(1).getQuestionList().get(2).getKey());

    }


}
