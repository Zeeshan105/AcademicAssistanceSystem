package comp3350.AAS.presentation;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.AAS.business.AccessFolder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import comp3350.AAS.R;
import comp3350.AAS.object.CardFolder;

public class FolderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        //get the list of folder from the data base
        AccessFolder accessFolder = new AccessFolder();
        ArrayList<String> folderNames= new ArrayList<>();
        ArrayList<CardFolder> folders = new ArrayList<>();
        accessFolder.getFolderList(folders);

        for (int i = 0; i < folders.size(); i++) {
            folderNames.add(folders.get(i).getFolderName());
        }

        int folderImage = R.drawable.ic_baseline_folder_24;

        ListView listview = findViewById(R.id.folderListView);
        CardFolderAdapter adapter = new CardFolderAdapter(this, folderNames, folderImage);
        listview.setAdapter(adapter);

    }

    public void openCardList(){
        Intent intent = new Intent(this, CardListActivity.class);
        startActivity(intent);
    }

    public void openFlashCards(){
        Intent intent = new Intent(this, CardHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openFlashCards();
        super.onBackPressed();
    }
}