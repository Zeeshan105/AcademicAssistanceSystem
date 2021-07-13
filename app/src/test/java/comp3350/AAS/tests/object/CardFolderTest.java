package comp3350.AAS.tests.object;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.object.CardFolder;


public class CardFolderTest extends TestCase {
    private CardFolder cardFolder;
    private ArrayList<CardFolder>folderArrayList;

    public CardFolderTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        folderArrayList=new ArrayList<>();
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

    public void testMultiFolderName(){
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

    public void testMultiFolderTitleAndDesc(){
        cardFolder=new CardFolder("Folder_1");
        folderArrayList.add(cardFolder);
        cardFolder=new CardFolder("Folder_2");
        folderArrayList.add(cardFolder);

        folderArrayList.get(0).addCard("Title1", "Description1");
        assertEquals("Title1", folderArrayList.get(0).getCardList().get(0).getTitle());
        assertEquals("Description1", folderArrayList.get(0).getCardList().get(0).getDescription());


        folderArrayList.get(1).addCard("Title2", "Description2");
        folderArrayList.get(1).addCard("Title3", "Description3");
        folderArrayList.get(1).addCard("Title4", "Description4");

        assertEquals("Title2", folderArrayList.get(1).getCardList().get(0).getTitle());
        assertEquals("Description2", folderArrayList.get(1).getCardList().get(0).getDescription());

        assertEquals("Title3", folderArrayList.get(1).getCardList().get(1).getTitle());
        assertEquals("Description3", folderArrayList.get(1).getCardList().get(1).getDescription());

        assertEquals("Title4", folderArrayList.get(1).getCardList().get(2).getTitle());
        assertEquals("Description4", folderArrayList.get(1).getCardList().get(2).getDescription());
    }


}
