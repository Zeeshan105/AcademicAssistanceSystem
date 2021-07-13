package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.AAS.R;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.object.Quiz;

public class ViewQuizAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quiz_answer);

        //get all the quizzes
        AccessQuiz accessQuiz = new AccessQuiz();
        ArrayList<String> quizNames = accessQuiz.getQuizNames();
        ArrayList<Quiz> quizzes = accessQuiz.getQuizList();

        //find the list view
        ListView listView  = findViewById(R.id.answersListVIew);

        //connect the list view adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            AnswerActivity.quiz = quizzes.get(position);
            AnswerActivity.num = 0;
            openAnswer();
        });
    }

    public void openAnswer(){
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(ViewQuizAnswerActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}