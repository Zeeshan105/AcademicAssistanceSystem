package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.AAS.database.*;

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
                CardListActivity.folderIndex = position;
                openCardList();
            }
        });

    }

    public void openCardList(){
        Intent intent = new Intent(this, CardListActivity.class);
        startActivity(intent);
    }
}