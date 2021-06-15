package comp3350.AAS.database;
import comp3350.AAS.object.*;

import java.util.ArrayList;

public class CardDataBase {
    ArrayList<CardFolder> listOfFolder;

    public CardDataBase(){
        listOfFolder = new ArrayList<>();
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
            CardFolder f = new CardFolder(folderName);
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

    public ArrayList<CardFolder> getFolders(){
        return listOfFolder;
    }

    public void deleteFolder(int idx) {
        listOfFolder.remove(idx);
    }
}
