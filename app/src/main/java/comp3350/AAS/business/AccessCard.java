package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.FlashCard;

public class AccessCard {
    private DataAccess dataAccess;
    private ArrayList<FlashCard> cardList;
    private FlashCard card;

    public AccessCard(){
        dataAccess = Services.getDataAccess(Main.dbName);
        cardList = null;
        card = null;
    }

    public void addCard(String title,String desc, String folderName){
        dataAccess.addCard(title, desc, folderName);
    }

}
