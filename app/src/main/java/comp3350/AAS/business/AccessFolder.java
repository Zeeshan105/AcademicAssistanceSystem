package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.CardFolder;

public class AccessFolder {
    private DataAccess dataAccess;

    public AccessFolder(){
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public void addCard(String title,String desc, String folderName){
        dataAccess.addCard(title, desc, folderName);
    }

    public void getFolderList(ArrayList<CardFolder> folders){
        folders.clear();
        dataAccess.getFolderList(folders);
    }

    public ArrayList<String> getFolderNames(){
        return dataAccess.getFolderNames();
    }

    public void deleteFolder(int index){
        dataAccess.deleteFolder(index);
    }

    public void deleteCard(String folderName, String title){dataAccess.deleteCard(folderName,title);}

}

