package comp3350.AAS.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.business.Calculate;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.R;
import comp3350.AAS.object.Quiz;

public class ViewStatsActivity extends AppCompatActivity{
//    private Calculate cal = new Calculate();
    private AccessQuiz accessQuiz;
    private ArrayList<Quiz> quizArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_stats);

        init();

        Button back = findViewById(R.id.backToQuizHome);
        back.setOnClickListener(v -> resetQuizList());
    }

    public void init() {
        accessQuiz = new AccessQuiz();
        quizArrayList = accessQuiz.getQuizList();

        ArrayList<String> completedQuizzesList = accessQuiz.getGradeQuizList();
        ListView listView= findViewById(R.id.quizListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, completedQuizzesList);
        listView.setAdapter(adapter);

        TextView completedQuizzes = findViewById(R.id.completed_quizzes);
        TextView averageGrade =  findViewById(R.id.average_grade);
        TextView highestGrade =  findViewById(R.id.highest_grade);
        TextView lowestGrade = findViewById(R.id.lowest_grade);

        completedQuizzes.setText(accessQuiz.getNumCompletedQuiz());

        int number=Integer.parseInt(accessQuiz.getNumCompletedQuiz());

        if (number > 0) {
            averageGrade.setText(accessQuiz.getAverageGrade());
            highestGrade.setText(accessQuiz.getHighestGrade());
            lowestGrade.setText(accessQuiz.getLowestGrade());
        }
    }

    public void resetQuizList(){
        for (int i = 0; i < quizArrayList.size(); i++) {
            accessQuiz.updateStatus(quizArrayList.get(i).getQuizName(), "FALSE");
            accessQuiz.updateGrade(quizArrayList.get(i).getQuizName(), 0);
        }

        Intent intent = new Intent(this, QuizHomeActivity.class);
        startActivity(intent);
    }

}
