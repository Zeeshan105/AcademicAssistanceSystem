package comp3350.ASS.tests.object;

import junit.framework.TestCase;
import comp3350.AAS.object.FlashCard;


public class FlashCardTest extends TestCase{
    private FlashCard flashCard;

    public FlashCardTest(String arg0){
        super(arg0);
    }

    public void testNullInput(){
        flashCard=new FlashCard(null, null);

        assertNotNull(flashCard);
        assertNull(flashCard.getTitle());
        assertNull(flashCard.getDescription());
    }

    public void testEmptyInput(){
        flashCard=new FlashCard("", "");

        assertNotNull(flashCard);
        assertEquals("", flashCard.getTitle());
        assertEquals("", flashCard.getDescription());
    }

    public void testNullDescription(){
        flashCard=new FlashCard("", null);

        assertNotNull(flashCard);
        assertEquals("", flashCard.getTitle());
        assertNull(flashCard.getDescription());
    }

    public void testNullTitle(){
        flashCard=new FlashCard(null, "");

        assertNotNull(flashCard);
        assertNull(flashCard.getTitle());
        assertEquals("", flashCard.getDescription());
    }

    public void testTypical(){
        flashCard=new FlashCard("My Flashcard Title", "Here is a description.");

        assertNotNull(flashCard);
        assertEquals("My Flashcard Title", flashCard.getTitle());
        assertEquals("Here is a description.", flashCard.getDescription());
    }
    public void testMessyCode(){
        flashCard=new FlashCard("$%^&*((@#$%^&*(#$%^&*(PSKD:PX{CZ", ")*@_)#_SKLSDM MKWSO)DPW()@_# +:::ASD");

        assertNotNull(flashCard);
        assertEquals("$%^&*((@#$%^&*(#$%^&*(PSKD:PX{CZ", flashCard.getTitle());
        assertEquals(")*@_)#_SKLSDM MKWSO)DPW()@_# +:::ASD", flashCard.getDescription());
    }

}
