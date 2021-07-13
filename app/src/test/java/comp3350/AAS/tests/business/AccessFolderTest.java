package comp3350.AAS.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.tests.database.DataAccessStub;


public class AccessFolderTest extends TestCase {

    public AccessFolderTest(String arg0){
        super(arg0);
    }

    public void test(){
        AccessFolder af;
        ArrayList<CardFolder>cardFolders;
        ArrayList<FlashCard> flashCards;
        FlashCard flashCard;

        Services.closeDataAccess();

        Services.createDataAccess(new DataAccessStub(Main.dbName));

        af = new AccessFolder();
        cardFolders=new ArrayList<>();
        af.getFolderList(cardFolders);


        flashCards = cardFolders.get(0).getCardList();
        assertNotNull(flashCards);
        assertEquals("Geographic_Knowledge", cardFolders.get(0).getFolderName());
        flashCard=flashCards.get(0);
        assertEquals("National Land Area Descending Order", flashCard.getTitle());
        assertEquals("Russia>Canada>China>America>Brazil...", flashCard.getDescription());
        flashCard=flashCards.get(1);
        assertEquals("Temperature Distributed", flashCard.getTitle());
        assertEquals("Decreasing from low latitude to high latitude", flashCard.getDescription());

        flashCards = cardFolders.get(1).getCardList();
        assertNotNull(flashCards);
        assertEquals("Historical_Event", cardFolders.get(1).getFolderName());
        flashCard=flashCards.get(0);
        assertEquals("First World War", flashCard.getTitle());
        assertEquals("The World War I break out in 1914", flashCard.getDescription());
        flashCard=flashCards.get(1);
        assertEquals("Second World War", flashCard.getTitle());
        assertEquals("The World War II break out in 1939", flashCard.getDescription());

        flashCards = cardFolders.get(2).getCardList();
        assertEquals("Math_Practice", cardFolders.get(2).getFolderName());
        assertNotNull(flashCards);
        flashCard=flashCards.get(0);
        assertEquals("Addition and subtraction within 10", flashCard.getTitle());
        assertEquals("1+1=2, 2+2=4", flashCard.getDescription());
        flashCard=flashCards.get(1);
        assertEquals("Calculate square root", flashCard.getTitle());
        assertEquals("sqrt(1)=+-1, sqrt(16)=+-4, sqrt(36)=+-6, sqrt(-1)=undefined", flashCard.getDescription());
    }


}
