package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import comp3350.AAS.business.AccessFolder;
import comp3350.AAS.R;
import comp3350.AAS.business.Validate;

public class CardHomeActivity extends AppCompatActivity {
    private String cardTitle, cardDescription,typedFolderName, selectedFolderName;
    private EditText titleInput, descriptionInput,folderNameInput;
    private AccessFolder accessFolder;
    private Validate val;
    private ArrayList<String> folderNameList;
    private boolean folderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_home);
        //get the "EditText" fields
        titleInput = findViewById(R.id.editTextTitle);
        descriptionInput = findViewById(R.id.editTextDescription);
        folderNameInput = findViewById(R.id.editTextFolderName);

        val = new Validate();
        accessFolder = new AccessFolder();
        folderNameList = accessFolder.getFolderNames();

        Spinner spinnerFolderName = findViewById(R.id.spinner_folder_name);
        ArrayAdapter<String> adapterFolderName = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, folderNameList);

        spinnerFolderName.setAdapter(adapterFolderName);
        spinnerFolderName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFolderName = parent.getItemAtPosition(position).toString();
                folderSelected = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {folderSelected = false;}
        });

        //initialize the submit button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            //store the text into vars
            cardTitle = titleInput.getText().toString();
            cardDescription = descriptionInput.getText().toString();
            typedFolderName = folderNameInput.getText().toString();


            //check if they gave a folder
            if(!folderSelected) {
                if (!val.isValidCardInput(cardTitle, cardDescription, typedFolderName)) {
                    showToast("Error! Fields cannot be empty!");
                }
                //            else if (!val.isValidCardInput(cardTitle,cardDescription) && folderSelected){
                //                showToast("Error! the title and description fields cannot be empty!");
                //            }
                else if (typedFolderName.length() > 33) {
                    showToast("Error! The folder name is too long!");
                } else if (cardTitle.length() > 70) {
                    showToast("Error! The flashcard title is too long!");
                } else if (cardDescription.length() > 280) {
                    showToast("Error! The flashcard description is too long!");
                } else {
                    AccessFolder accessCard = new AccessFolder();
                    accessCard.addCard(cardTitle, cardDescription, typedFolderName);

                    //reset the "EditText" fields
                    titleInput.setText("");
                    descriptionInput.setText("");
                    folderNameInput.setText("");
                    showToast("Card Added!");
                    refresh();
                }
            }

            else{
                if (!val.isValidCardInput(cardTitle,cardDescription)){
                    showToast("Error! the title and description fields cannot be empty!");
                }
                else if(typedFolderName.isEmpty()){
                    AccessFolder accessCard = new AccessFolder();
                    accessCard.addCard(cardTitle, cardDescription, selectedFolderName);

                    //reset the "EditText" fields
                    titleInput.setText("");
                    descriptionInput.setText("");
                    folderNameInput.setText("");
                    showToast("Card Added!");
                    refresh();
                }
                else{
                    if (folderNameList.contains(typedFolderName)){
                        folderNameInput.setText("");
                        showToast("Error! This folder already exists, select it using the dropdown menu");
                    }
                    else{
                        AccessFolder accessCard = new AccessFolder();
                        accessCard.addCard(cardTitle, cardDescription, typedFolderName);

                        //reset the "EditText" fields
                        titleInput.setText("");
                        descriptionInput.setText("");
                        folderNameInput.setText("");
                        showToast("Card Added!");
                        refresh();
                    }

                }
            }
        });

        //initialize the view folders button
        Button viewFolders = findViewById(R.id.viewFoldersButton);
        viewFolders.setOnClickListener(v -> openFolders());

    }

    public void openFolders(){
        Intent intent = new Intent(this, FolderListActivity.class);
        startActivity(intent);
    }

    public void refresh(){
        Intent intent = new Intent(this, CardHomeActivity.class);
        startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(CardHomeActivity.this,text, Toast.LENGTH_SHORT).show();
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openMain();
        super.onBackPressed();
    }
}