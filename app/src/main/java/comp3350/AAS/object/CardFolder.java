package comp3350.AAS.object;

import java.util.ArrayList;

public class CardFolder {

    private ArrayList<FlashCard> cardList;
    private String folderName;

    public CardFolder(String folderName){
        this.folderName = folderName;
        cardList = new ArrayList<>();
    }

    public String getFolderName(){
        return folderName;
    }

    public void addCard(String title, String desc){
        FlashCard card = new FlashCard(title,desc);
        cardList.add(card);
    }

    public ArrayList<String> getCardTitles(){

        ArrayList<String> cardTitles = new ArrayList<>();

        for (int i = 0; i < cardList.size(); i++) {
            cardTitles.add(cardList.get(i).getTitle());
        }

        return cardTitles;
    }

    public ArrayList<String> getCardDescription(){

        ArrayList<String> cardDescriptions = new ArrayList<>();

        for (int i = 0; i < cardList.size(); i++) {
            cardDescriptions.add(cardList.get(i).getDescription());
        }

        return cardDescriptions;
    }

    public void removeCard(int index){
        cardList.remove(index);
    }

}
