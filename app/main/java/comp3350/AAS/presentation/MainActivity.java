package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import comp3350.AAS.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flash cards button
        Button button1 = findViewById(R.id.Button1);
        button1.setOnClickListener(v -> openFlashCards());

        //
        Button button2 = findViewById(R.id.Button2);
        button2.setOnClickListener(v -> createQuizzes());

        //setup the quizzes button
        //quizzes button
        Button button3 = findViewById(R.id.Button3);
        button3.setOnClickListener(v -> openQuizzes());

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

}