package comp3350.AAS.object;

import java.util.ArrayList;

public class cardFolder {
    ArrayList<flashCard> cardList;
    private String folderName;

    public cardFolder(String name){
        this.folderName = name;
        cardList = new ArrayList<>();
    }

    public String getFolderName(){
        return folderName;
    }

    public void addCard(String title, String desc){
        flashCard card = new flashCard(title,desc);
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
