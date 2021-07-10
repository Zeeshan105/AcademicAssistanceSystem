package comp3350.AAS.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.Calculate;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.QuizDatabase;
import comp3350.ASS.R;

public class ViewStatsActivity extends AppCompatActivity{

    private final Calculate cal = new Calculate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_stats);

        init();
    }

    public void init() {
        QuizDatabase quizDatabase = Services.getQuizAccess();
//        DataAccess quizDatabase= Services.getDataAccess(Main.dbName);

        ArrayList<String> completedQuizzesList = quizDatabase.generateQuizGradesList();

        ListView listView= findViewById(R.id.quizListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, completedQuizzesList);
        listView.setAdapter(adapter);

        TextView completedQuizzes = findViewById(R.id.completed_quizzes);
        TextView averageGrade =  findViewById(R.id.average_grade);
        TextView highestGrade =  findViewById(R.id.highest_grade);
        TextView lowestGrade = findViewById(R.id.lowest_grade);

        completedQuizzes.setText(cal.numberCompletedQuizzes(quizDatabase.getQuizList()));
        if (quizDatabase.getCompletedQuizzes() > 0) {
            averageGrade.setText(cal.getAverageGrade(quizDatabase.getQuizList()));
            highestGrade.setText(cal.getHighestGrade(quizDatabase.getQuizList()));
            lowestGrade.setText(cal.getLowestGrade(quizDatabase.getQuizList()));
        }
    }
}
