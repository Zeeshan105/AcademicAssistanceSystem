package comp3350.AAS.presentation;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.R;


public class SelectQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);

        init();

        Button viewStats = findViewById(R.id.ViewStats);
        viewStats.setOnClickListener(v -> openStats());
    }

    public void init(){
        AccessQuiz accessQuiz = new AccessQuiz();
        ArrayList<String> quizNames = accessQuiz.getQuizNames();

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
                builder.setNegativeButton("Start", (dialog, which) -> {
                    StartQuizActivity.currPosition = position;
                    remainQuizList.remove(position + "");
                    startQuiz();
                });

                builder.setPositiveButton("Delete", (dialog, which) -> {
                    accessQuiz.deleteQuiz(position);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
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

    public void openStats(){
        Intent intent = new Intent(this, ViewStatsActivity.class);
        startActivity(intent);
    }

}