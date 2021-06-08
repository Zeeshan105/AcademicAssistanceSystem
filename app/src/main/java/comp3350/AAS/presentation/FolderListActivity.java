package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.AAS.database.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import comp3350.AAS.application.*;
import comp3350.ASS.R;

public class FolderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        //get the list of folder from the data base
        CardDataBase database = services.createDataAccess("cardBase");
        String[] arr = database.getFolderNames();

        ListView listview = findViewById(R.id.folderListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FolderListActivity.this);
                builder1.setMessage("Would you like to view this folder or delete this folder?");
                builder1.setCancelable(true);

                builder1.setNegativeButton("View folder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CardListActivity.folderIndex = position;
                        openCardList();
                    }
                });

                builder1.setPositiveButton("Delete folder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        database.deleteFolder(position);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                AlertDialog alert1 = builder1.create();
                alert1.show();


            }
        });

    }

    public void openCardList(){
        Intent intent = new Intent(this, CardListActivity.class);
        startActivity(intent);
    }
}