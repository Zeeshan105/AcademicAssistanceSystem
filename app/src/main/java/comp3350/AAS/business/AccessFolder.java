package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.CardFolder;

public class AccessFolder {
    private DataAccess dataAccess;
    private ArrayList<CardFolder> folderList;
    private CardFolder folder;

    public AccessFolder(){
        dataAccess = Services.getDataAccess(Main.dbName);
        folderList = null;
        folder = null;
    }

    public ArrayList<CardFolder> getFolderList(){
        return dataAccess.getFolders();
    }

    public ArrayList<String> getFolderName(){
        return dataAccess.getFolderNames();
    }

    public void deleteFolder(int index){
        dataAccess.deleteFolder(index);
    }

}

