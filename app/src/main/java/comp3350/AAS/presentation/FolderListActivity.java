package comp3350.AAS.presentation;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.database.*;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import comp3350.AAS.application.*;
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


        ListView listview = findViewById(R.id.folderListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, folderNames);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(FolderListActivity.this);
            builder1.setMessage("Would you like to view this folder or delete this folder?");
            builder1.setCancelable(true);

            builder1.setNegativeButton("View folder", (dialog, which) -> {
                CardListActivity.folderIndex = position;
                openCardList();
            });

            builder1.setPositiveButton("Delete folder", (dialog, which) -> {
                accessFolder.deleteFolder(position);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            });

            AlertDialog alert1 = builder1.create();
            alert1.show();


        });

    }

    public void openCardList(){
        Intent intent = new Intent(this, CardListActivity.class);
        startActivity(intent);
    }
}