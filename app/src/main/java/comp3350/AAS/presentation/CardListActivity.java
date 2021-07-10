package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import comp3350.AAS.application.Main;
import comp3350.AAS.database.DataAccess;
import comp3350.ASS.R;
import comp3350.AAS.application.Services;
import comp3350.AAS.object.CardFolder;

import java.util.ArrayList;

public class CardListActivity extends AppCompatActivity {
    static int folderIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        //ArrayList<CardFolder> folders = Services.getCardAccess().getFolders();
        DataAccess db = Services.getDataAccess(Main.dbName);
        ArrayList<CardFolder> folders = db.getFolders();

        //change the name of the text view
        TextView textview = findViewById(R.id.textViewFolderName);
        textview.setText(folders.get(folderIndex).getFolderName());

        //get all the card info
        ArrayList<String> cardTitles = folders.get(folderIndex).getCardTitles();
        ArrayList<String> cardDescriptions = folders.get(folderIndex).getCardDescription();

        //connect the cards to the list view
        ListView listView = findViewById(R.id.cardListVIew);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cardTitles);
        listView.setAdapter(adapter);

        //make the list clickable
        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(CardListActivity.this);
            builder1.setTitle(cardTitles.get(position));
            builder1.setMessage(cardDescriptions.get(position));
            builder1.setCancelable(true);

            builder1.setNegativeButton("Delete", (dialog, which) -> {
                folders.get(folderIndex).removeCard(position);

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            });


            builder1.setNeutralButton("Edit", (dialog, which) -> {
                //edit the card


            });
            AlertDialog alert1 = builder1.create();
            alert1.show();
        });
    }



}