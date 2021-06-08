package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import comp3350.ASS.R;
import comp3350.AAS.application.services;
import comp3350.AAS.object.cardFolder;

import java.util.ArrayList;

public class CardListActivity extends AppCompatActivity {
    static int folderIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        ArrayList<cardFolder> folders = services.createDataAccess("cardBase").getFolders();

        //change the name of the text view
        TextView textview = (TextView) findViewById(R.id.textViewFolderName);
        textview.setText(folders.get(folderIndex).getFolderName());

        //get all the card info
        String[] titles = folders.get(folderIndex).getCardTitles();
        String[] descriptions = folders.get(folderIndex).getCardDescription();

        //connect the cards to the list view
        ListView listView = (ListView)findViewById(R.id.cardListVIew);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(adapter);

        //make the list clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(CardListActivity.this);
                builder1.setMessage(descriptions[position]);
                builder1.setCancelable(true);

                builder1.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        folders.get(folderIndex).removeCard(position);

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
}