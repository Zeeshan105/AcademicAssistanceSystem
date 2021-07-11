package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import comp3350.AAS.R;


public class QuizHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        Button createQuiz = findViewById(R.id.CreateQuiz);
        createQuiz.setOnClickListener(v -> createQuizzes());

        Button startQuiz = findViewById(R.id.StartQuiz);
        startQuiz.setOnClickListener(v -> doQuizzes());

        Button viewStats = findViewById(R.id.ViewStats);
        viewStats.setOnClickListener(v -> openStats());

        Button viewKey = findViewById(R.id.ViewQuizAnswer);
        viewKey.setOnClickListener(v -> viewAnswer());
    }

    public void createQuizzes(){
        Intent intent = new Intent(this, MakeQuizActivity.class);
        startActivity(intent);
    }

    public void doQuizzes(){
        Intent intent = new Intent(this, SelectQuizActivity.class);
        startActivity(intent);
    }

    public void openStats(){
        Intent intent = new Intent(this, ViewStatsActivity.class);
        startActivity(intent);
    }

    public void viewAnswer(){
        Intent intent = new Intent(this, ViewQuizAnswerActivity.class);
        startActivity(intent);
    }

}