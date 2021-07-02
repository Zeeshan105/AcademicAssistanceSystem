package comp3350.AAS.presentation;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import comp3350.AAS.application.Services;
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
        QuizDatabase database= Services.createQuizDataAccess("QuizBase");
        ArrayList<String> quizNames = database.getAllQuizName();

        ArrayList<String> remainQuizList=new ArrayList<>();
        for (int i=0; i<quizNames.size(); i++) {
            remainQuizList.add(i+"");
        }

        ListView listView= findViewById(R.id.quizListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(SelectQuizActivity.this);

            if (remainQuizList.contains(position+"")) {
                builder.setNegativeButton("Start this quiz", (dialog, which) -> {
                    StartQuizActivity.currPosition = position;
                    remainQuizList.remove(position + "");
                    startQuiz();
                });
            }else {
                builder.setNegativeButton("This quiz is done!", (dialog, which) -> {});
            }

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    private void startQuiz(){
        Intent intent = new Intent(this, StartQuizActivity.class);
        startActivity(intent);
    }

}