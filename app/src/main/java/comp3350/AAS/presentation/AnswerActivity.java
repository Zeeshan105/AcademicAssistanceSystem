package comp3350.AAS.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.AAS.R;
import comp3350.AAS.object.Quiz;

public class AnswerActivity extends AppCompatActivity {
    static Quiz quiz = null;
    static int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView question = findViewById(R.id.viewQuestion);
        question.setText("Question: "+quiz.getQuestionList().get(num).getQuestion());

        TextView answer = findViewById(R.id.viewSolution);
        answer.setText("Answer: "+quiz.getQuestionList().get(num).getKey());

        Button button = findViewById(R.id.viewNextAnswerButton);
        button.setOnClickListener(v -> nextQuestion());

    }

    private void nextQuestion(){
        if(num +1 >= quiz.getQuestionList().size()){
            showToast("No more Questions!");
        }else{
            num++;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void showToast(String text){
        Toast.makeText(AnswerActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}
