package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import comp3350.AAS.R;
import comp3350.AAS.application.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // starting startup
        Main.startUp();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flash cards button
        Button button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(v -> openFlashCards());

        //create the quizzes button
        Button button2 = findViewById(R.id.Button2);
        button2.setOnClickListener(v -> openQuizPart());
    }

    public void openFlashCards(){
        Intent intent = new Intent(this, FlashCardActivity.class);
        startActivity(intent);
    }

    public void openQuizPart(){
        Intent intent = new Intent(this, QuizHomeActivity.class);
        startActivity(intent);
    }

}