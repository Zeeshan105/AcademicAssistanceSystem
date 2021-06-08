package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import comp3350.ASS.R;
import comp3350.AAS.application.services;
import comp3350.AAS.database.QuizDatabase;


public class MakeQuizActivity extends AppCompatActivity {
    private String quizQuestion, optionA,optionB, optionC, answer;
    private EditText questionInput, firstOption, secondOption, thirdOption, keyOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quiz);

        questionInput=(EditText) findViewById(R.id.question);
        firstOption=(EditText) findViewById(R.id.option_A);
        secondOption=(EditText) findViewById(R.id.option_B);
        thirdOption=(EditText) findViewById(R.id.option_C);
        keyOption=(EditText) findViewById(R.id.key);

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

                if(quizQuestion.equals("") || optionA.equals("") || optionB.equals("") || optionC.equals("") || answer.equals("")){
                    showToast("Error! Must define a question and three options!");
                }else{
                    QuizDatabase database= services.createQuizDataAccess("QuizBase");
                    database.addQuiz(quizQuestion, optionA, optionB, optionC, answer);

                    //reset the "EditText" fields
                    questionInput.setText("");
                    firstOption.setText("");
                    secondOption.setText("");
                    thirdOption.setText("");
                    keyOption.setText("");
                    showToast("Quiz Added!");
                }
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(MakeQuizActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}