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
import com.example.academicassistancesystem.R;

public class folderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        //get the list of folder from the data base
        cardDataBase database = services.createDataAccess("cardBase");
        String[] arr = database.getFolderNames();

        ListView listview = (ListView) findViewById(R.id.folderListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardList.folderIndex = position;
                openCardList();
            }
        });

    }

    public void openCardList(){
        Intent intent = new Intent(this, cardList.class);
        startActivity(intent);
    }
}