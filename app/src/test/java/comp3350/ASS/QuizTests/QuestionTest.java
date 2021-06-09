package comp3350.ASS.QuizTests;

import junit.framework.TestCase;
import org.junit.Assert;
import comp3350.AAS.object.Question;


public class QuestionTest extends TestCase{
    public QuestionTest(String arg0){
        super(arg0);
    }

    public void testNullInput(){
        Question q=new Question(null, null, null, null, null);

        assertNull(q.getQuestion());
        assertNull(q.getOption1());
        assertNull(q.getOption2());
        assertNull(q.getOption3());
        assertNull(q.getKey());
    }

    public void testEmptyInput(){
        Question q=new Question("", "", "", "", "");

        assertEquals("", q.getQuestion());
        assertEquals("", q.getQuestion());
        assertEquals("", q.getQuestion());
        assertEquals("", q.getQuestion());
        assertEquals("", q.getQuestion());
    }

    public void testNotEmptyInput(){
        Question q=new Question("1+1=", "1", "2", "3", "2");

        assertEquals("1+1=", q.getQuestion());
        assertEquals("1", q.getOption1());
        assertEquals("2", q.getOption2());
        assertEquals("3", q.getOption3());
        assertEquals("2", q.getKey());
    }

    public void testKeyMatchButton1(){
        Question q=new Question("1+1=", "2", "1", "3", "2");
        assertEquals(q.getOption1(), q.getKey());
    }

    public void testKeyMatchButton2(){
        Question q=new Question("1+1=", "1", "2", "3", "2");
        assertEquals(q.getOption2(), q.getKey());
    }

    public void testKeyMatchButton3(){
        Question q=new Question("1+1=", "1", "3", "2", "2");
        assertEquals(q.getOption3(), q.getKey());
    }

    public void testInvalidKey(){
        Question q=new Question("1+1=?", "1", "2", "3", "-1");

        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }

    public void testNullKey(){
        Question q=new Question("1+1=?", "1", "2", "3", null);

        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }

    public void testNullOption(){
        Question q=new Question("1+1=?", null, null, null, "2");

        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }


}
