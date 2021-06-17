package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.AAS.object.Question;
import comp3350.ASS.R;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.QuizDatabase;


public class MakeQuizActivity extends AppCompatActivity {
    private String quizQuestion, optionA,optionB, optionC, answer, quizName;
    private EditText questionInput, firstOption, secondOption, thirdOption, keyOption, quizIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quiz);

        questionInput=(EditText) findViewById(R.id.question);
        firstOption=(EditText) findViewById(R.id.option_A);
        secondOption=(EditText) findViewById(R.id.option_B);
        thirdOption=(EditText) findViewById(R.id.option_C);
        keyOption=(EditText) findViewById(R.id.key);
        quizIndex=(EditText) findViewById(R.id.quiz_index);

        Button submitButton = (Button) findViewById(R.id.submit_Button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store the text into variables
                quizQuestion = questionInput.getText().toString();
                optionA = firstOption.getText().toString();
                optionB = secondOption.getText().toString();
                optionC = thirdOption.getText().toString();
                answer = keyOption.getText().toString();
                quizName = quizIndex.getText().toString();

                if(quizQuestion.equals("") || optionA.equals("") || optionB.equals("") || optionC.equals("") || answer.equals("") || quizName.equals("")){
                    showToast("Error! Must define a question and three options!");
                }else{

                    Question newQuestion=new Question(quizQuestion, optionA, optionB, optionC, answer);

                    QuizDatabase database= Services.createQuizDataAccess("QuizBase");
                    database.addQuiz(newQuestion, quizName);

                    //reset the "EditText" fields
                    questionInput.setText("");
                    firstOption.setText("");
                    secondOption.setText("");
                    thirdOption.setText("");
                    keyOption.setText("");
                    quizIndex.setText("");
                    showToast("Question Added!");
                }
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(MakeQuizActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}