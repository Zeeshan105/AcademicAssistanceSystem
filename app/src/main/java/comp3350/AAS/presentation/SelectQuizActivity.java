package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import comp3350.AAS.application.services;
import comp3350.AAS.database.QuizDatabase;
import comp3350.ASS.R;


public class SelectQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);

        init();
    }

    public void init(){
        QuizDatabase database= services.createQuizDataAccess("QuizBase");
        String[] names = database.getAllQuizName();
        ListView listView= findViewById(R.id.quizListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            startQuiz();
        });
    }

    private void startQuiz(){
        Intent intent = new Intent(this, StartQuizActivity.class);
        startActivity(intent);
    }

}