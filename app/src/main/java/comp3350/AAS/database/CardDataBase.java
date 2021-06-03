package comp3350.AAS.database;
import comp3350.AAS.object.*;

import java.util.ArrayList;

public class CardDataBase {
    ArrayList<cardFolder> listOfFolder;
    private static int folderIndex;

    public CardDataBase(){
        listOfFolder = new ArrayList<>();
        folderIndex = -1;
    }

    public boolean contains(String name){
        for(int i = 0; i < listOfFolder.size();i++){
            cardFolder f = listOfFolder.get(i);
            if(f.getFolderName().equals(name)){
                folderIndex = i;
                return true;
            }
        }
        return false;
    }

    public void addCard(String title,String desc, String folderName){
        int idx = -1;
        for(int i = 0; i < listOfFolder.size();i++){
            if(listOfFolder.get(i).getFolderName().equals(folderName)){
                idx = i;
                break;
            }
        }

        if(idx == -1){
            cardFolder f = new cardFolder(folderName);
            f.addCard(title,desc);
            listOfFolder.add(f);

        }else {
            listOfFolder.get(idx).addCard(title, desc);
        }

    }

    public String[] getFolderNames(){
        String[] names = new String[listOfFolder.size()];
        for (int i = 0; i < listOfFolder.size(); i++) {
            names[i] = listOfFolder.get(i).getFolderName();
        }
        return names;
    }

    public ArrayList<cardFolder> getFolders(){
        return listOfFolder;
    }
}
