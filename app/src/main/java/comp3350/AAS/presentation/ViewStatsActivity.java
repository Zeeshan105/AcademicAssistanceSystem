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

        TextView completedQuizzes = findViewById(R.id.completed_quizzes_text);
        TextView averageGrade =  findViewById(R.id.average_grade_text);
        TextView highestGrade =  findViewById(R.id.highest_grade_text);
        TextView lowestGrade = findViewById(R.id.lowest_grade_text);

        int number=Integer.parseInt(accessQuiz.getNumCompletedQuiz());
        String numCompleted = "Number of Completed Quizzes: "+number;
        String average = "Average Quiz Grade: "+accessQuiz.getAverageGrade();
        String highest = "Highest Quiz Grade: "+accessQuiz.getHighestGrade();
        String lowest = "Lowest Quiz Grade: "+accessQuiz.getLowestGrade();

        completedQuizzes.setText(numCompleted);

        if (number > 0) {
            averageGrade.setText(average);
            highestGrade.setText(highest);
            lowestGrade.setText(lowest);
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
