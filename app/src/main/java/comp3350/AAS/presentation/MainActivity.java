package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import comp3350.ASS.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flash cards button
        Button button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(v -> openFlashCards());

        //create the quizzes button
        Button button2 = findViewById(R.id.Button2);
        button2.setOnClickListener(v -> createQuizzes());

        //start quizzes button
        Button button3 = findViewById(R.id.Button3);
        button3.setOnClickListener(v -> openQuizzes());

        //view stats button
        Button button4 = findViewById(R.id.Button4);
        button4.setOnClickListener(v -> openStats());

    }

    public void openFlashCards(){
        Intent intent = new Intent(this, FlashCardActivity.class);
        startActivity(intent);
    }

    public void createQuizzes(){
        Intent intent = new Intent(this, MakeQuizActivity.class);
        startActivity(intent);
    }

    public void openQuizzes(){
        Intent intent = new Intent(this, SelectQuizActivity.class);
        startActivity(intent);
    }

    public void openStats(){
        Intent intent = new Intent(this, ViewStatsActivity.class);
        startActivity(intent);
    }

}