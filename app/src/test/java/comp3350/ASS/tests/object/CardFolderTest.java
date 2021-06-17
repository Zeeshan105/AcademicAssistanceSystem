package comp3350.ASS.tests.object;

import junit.framework.TestCase;
import comp3350.AAS.object.CardFolder;


public class CardFolderTest extends TestCase {
    private CardFolder cardFolder;

    public CardFolderTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        cardFolder=new CardFolder("Name");

        assertNotNull(cardFolder);
        cardFolder.addCard("title", "description");
        cardFolder.addCard("123", "123456");
        cardFolder.addCard("", "");
        cardFolder.addCard(null, "");
        cardFolder.addCard("", null);
        cardFolder.addCard(null, null);
    }

    public void testCardTitle(){
        assertEquals("title", cardFolder.getCardTitles().get(0));
        assertEquals("123", cardFolder.getCardTitles().get(1));
        assertEquals("", cardFolder.getCardTitles().get(2));
        assertEquals(null, cardFolder.getCardTitles().get(3));
        assertEquals("", cardFolder.getCardTitles().get(4));
        assertEquals(null, cardFolder.getCardTitles().get(5));
    }

    public void testCardDescription(){
        assertEquals("description", cardFolder.getCardDescription().get(0));
        assertEquals("123456", cardFolder.getCardDescription().get(1));
        assertEquals("", cardFolder.getCardDescription().get(2));
        assertEquals("", cardFolder.getCardDescription().get(3));
        assertEquals(null, cardFolder.getCardDescription().get(4));
        assertEquals(null, cardFolder.getCardDescription().get(5));
    }

    public void testRemoveCard(){
        cardFolder.removeCard(0);
        assertEquals("123", cardFolder.getCardTitles().get(0));
        assertEquals("123456", cardFolder.getCardDescription().get(0));

        cardFolder.removeCard(0);
        assertEquals("", cardFolder.getCardTitles().get(0));
        assertEquals("", cardFolder.getCardDescription().get(0));

        cardFolder.removeCard(0);
        assertEquals(null, cardFolder.getCardTitles().get(0));
        assertEquals("", cardFolder.getCardDescription().get(0));

        cardFolder.removeCard(0);
        assertEquals("", cardFolder.getCardTitles().get(0));
        assertEquals(null, cardFolder.getCardDescription().get(0));

        cardFolder.removeCard(0);
        assertEquals(null, cardFolder.getCardTitles().get(0));
        assertEquals(null, cardFolder.getCardDescription().get(0));
    }

    public void testFolderName(){
        cardFolder=new CardFolder("Folder_One");
        assertNotNull(cardFolder);
        assertEquals("Folder_One", cardFolder.getFolderName());

        cardFolder=new CardFolder("00");
        assertNotNull(cardFolder);
        assertEquals("00", cardFolder.getFolderName());

        cardFolder=new CardFolder("");
        assertNotNull(cardFolder);
        assertEquals("", cardFolder.getFolderName());

        cardFolder=new CardFolder(null);
        assertNotNull(cardFolder);
        assertNull(cardFolder.getFolderName());
    }


}
