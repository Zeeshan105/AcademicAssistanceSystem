package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import comp3350.AAS.application.Main;
import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.R;
import comp3350.AAS.application.Services;
import comp3350.AAS.object.CardFolder;

import java.util.ArrayList;

public class CardListActivity extends AppCompatActivity {
    static int folderIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        AccessFolder accessFolder = new AccessFolder();
        ArrayList<CardFolder> folders=new ArrayList<>();
        accessFolder.getFolderList(folders);


        //change the name of the text view
        TextView textview = findViewById(R.id.textViewFolderName);
        textview.setText(folders.get(folderIndex).getFolderName());


        //get all the card info
        ArrayList<String> cardTitles = folders.get(folderIndex).getCardTitles();
        ArrayList<String> cardDescriptions = folders.get(folderIndex).getCardDescription();
        int cardImage = R.drawable.flash_card;


        //connect the cards to the list view
        ListView listView = findViewById(R.id.cardListVIew);
        FlashCardAdapter adapter = new FlashCardAdapter(this,cardTitles,cardDescriptions,cardImage,folderIndex);
        listView.setAdapter(adapter);

    }

    public void openFolders(){
        Intent intent = new Intent(this, FolderListActivity.class);
        startActivity(intent);
    }

    public void openCardList(){
        Intent intent = new Intent(this, CardListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openFolders();
        super.onBackPressed();

    }
}