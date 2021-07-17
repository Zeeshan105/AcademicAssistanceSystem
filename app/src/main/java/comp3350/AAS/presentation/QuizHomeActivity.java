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

        Button createMcq = findViewById(R.id.CreateMcqQuiz);
        createMcq.setOnClickListener(v -> createMcqQuizzes());

        Button createTf = findViewById(R.id.CreateTfQuiz);
        createTf.setOnClickListener(v -> createTfQuizzes());

        Button startQuiz = findViewById(R.id.StartQuiz);
        startQuiz.setOnClickListener(v -> doQuizzes());

        Button viewKey = findViewById(R.id.ViewQuizAnswer);
        viewKey.setOnClickListener(v -> viewAnswer());

        Button home = findViewById(R.id.homePage);
        home.setOnClickListener(v -> backToHome());
    }

    public void createMcqQuizzes(){
        Intent intent = new Intent(this, MakeMcqQuizActivity.class);
        startActivity(intent);
    }

    public void createTfQuizzes(){
        Intent intent = new Intent(this, MakeTfQuizActivity.class);
        startActivity(intent);
    }

    public void doQuizzes(){
        Intent intent = new Intent(this, SelectQuizActivity.class);
        startActivity(intent);
    }

    public void viewAnswer(){
        Intent intent = new Intent(this, ViewQuizAnswerActivity.class);
        startActivity(intent);
    }

    public void backToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}