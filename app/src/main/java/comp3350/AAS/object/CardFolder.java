package comp3350.AAS.object;

import java.util.ArrayList;

public class CardFolder {
    private ArrayList<FlashCard> cardList;
    private String folderName;

    public CardFolder(String name){
        this.folderName = name;
        cardList = new ArrayList<>();
    }

    public String getFolderName(){
        return folderName;
    }

    public void addCard(String title, String desc){
        FlashCard card = new FlashCard(title,desc);
        cardList.add(card);
    }

    public String[] getCardTitles(){
        String[] temp = new String[cardList.size()];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = cardList.get(i).getTitle();
        }

        return temp;
    }

    public String[] getCardDescription(){
        String[] temp = new String[cardList.size()];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = cardList.get(i).getDescription();
        }

        return temp;
    }

    public void removeCard(int idx){
        cardList.remove(idx);
    }

}
