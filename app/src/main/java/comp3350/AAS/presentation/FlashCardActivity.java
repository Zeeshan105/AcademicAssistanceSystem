package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.AAS.database.*;
import comp3350.AAS.application.*;
import comp3350.ASS.R;

public class FlashCardActivity extends AppCompatActivity {
    private String cardTitle, cardDescription,folderName;
    private EditText titleInput, descriptionInput,folderNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        //get the "EditText" fields
        titleInput = (EditText) findViewById(R.id.editTextTitle);
        descriptionInput = (EditText) findViewById(R.id.editTextDescription);
        folderNameInput = (EditText) findViewById(R.id.editTextFolderName);

        //initialize the submit button
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store the text into vars
                cardTitle = titleInput.getText().toString();
                cardDescription = descriptionInput.getText().toString();
                folderName = folderNameInput.getText().toString();

                //check if they gave a folder
                if(folderName.equals("")){
                    showToast("Error! Must define a folder name");
                }else{
                    //check if the folder already exists
                    System.out.println("yo1");

                    CardDataBase database = services.createDataAccess("cardBase");
                    System.out.println("yo2");
                    database.addCard(cardTitle,cardDescription,folderName);

                    //reset the "EditText" fields
                    titleInput.setText("");
                    descriptionInput.setText("");
                    folderNameInput.setText("");
                    showToast("Card Added!");
                }

            }
        });

        //initialize the view folders button
        Button viewFolders = (Button) findViewById(R.id.viewFoldersButton);
        viewFolders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolders();
            }
        });

    }

    public void openFolders(){
        Intent intent = new Intent(this, FolderListActivity.class);
        startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(FlashCardActivity.this,text, Toast.LENGTH_SHORT).show();
    }
}