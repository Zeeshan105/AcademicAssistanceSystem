package comp3350.AAS.database;

import comp3350.AAS.object.*;

import java.util.ArrayList;

public class CardDataBase {
    ArrayList<CardFolder> listOfFolder;

    public CardDataBase(){
        listOfFolder = new ArrayList<>();
    }

    public void addCard(String title,String desc, String folderName){

        int index = -1; // Place holder index to check if the flashcard belongs to an existing or new flashcard folder.

        for(int i = 0; i < listOfFolder.size();i++){
            if(listOfFolder.get(i).getFolderName().equals(folderName)){
                index = i;
                break;
            }
        }

        if(index == -1){
            CardFolder newFolder = new CardFolder(folderName);
            newFolder.addCard(title,desc);
            listOfFolder.add(newFolder);

        } else {
            listOfFolder.get(index).addCard(title, desc);
        }

    }

    public ArrayList<String> getFolderNames(){

        ArrayList<String> folderNames = new ArrayList<>();

        for (int i = 0; i < listOfFolder.size(); i++) {
            folderNames.add(listOfFolder.get(i).getFolderName());
        }
        return folderNames;

    }

    public ArrayList<CardFolder> getFolders(){
        return listOfFolder;
    }

    public void deleteFolder(int index) {
        listOfFolder.remove(index);
    }
}
