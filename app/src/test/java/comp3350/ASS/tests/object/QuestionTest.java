package comp3350.ASS.tests.object;

import junit.framework.TestCase;
import org.junit.Assert;
import comp3350.AAS.object.Question;


public class QuestionTest extends TestCase{
    private Question q;

    public QuestionTest(String arg0){
        super(arg0);
    }

    public void testNullInput(){
        q=new Question(null, null, null, null, null);

        assertNotNull(q);
        assertNull(q.getQuestion());
        assertNull(q.getOption1());
        assertNull(q.getOption2());
        assertNull(q.getOption3());
        assertNull(q.getKey());
    }

    public void testEmptyInput(){
        q=new Question("", "", "", "", "");

        assertNotNull(q);
        assertEquals("", q.getQuestion());
        assertEquals("", q.getOption1());
        assertEquals("", q.getOption2());
        assertEquals("", q.getOption3());
        assertEquals("", q.getKey());
    }

    public void testNotEmptyInput(){
        q=new Question("1+1=", "1", "2", "3", "2");

        assertNotNull(q);
        assertEquals("1+1=", q.getQuestion());
        assertEquals("1", q.getOption1());
        assertEquals("2", q.getOption2());
        assertEquals("3", q.getOption3());
        assertEquals("2", q.getKey());
    }

    public void testKeyMatchButton1(){
        q=new Question("1+1=", "2", "1", "3", "2");

        assertNotNull(q);
        assertEquals(q.getOption1(), q.getKey());
    }

    public void testKeyMatchButton2(){
        q=new Question("1+1=", "1", "2", "3", "2");

        assertNotNull(q);
        assertEquals(q.getOption2(), q.getKey());
    }

    public void testKeyMatchButton3(){
        q=new Question("1+1=", "1", "3", "2", "2");

        assertNotNull(q);
        assertEquals(q.getOption3(), q.getKey());
    }

    public void testInvalidKey(){
        q=new Question("1+1=?", "1", "2", "3", "-1");

        assertNotNull(q);
        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }

    public void testNullKey(){
        q=new Question("1+1=?", "1", "2", "3", null);

        assertNotNull(q);
        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }

    public void testNullOption(){
        q=new Question("1+1=?", null, null, null, "2");

        assertNotNull(q);
        Assert.assertNotEquals(q.getOption1(), q.getKey());
        Assert.assertNotEquals(q.getOption2(), q.getKey());
        Assert.assertNotEquals(q.getOption3(), q.getKey());
    }


}
