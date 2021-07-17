package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.object.Question;
import comp3350.AAS.R;
import comp3350.AAS.business.Validate;


public class MakeMcqQuizActivity extends AppCompatActivity {

    private String quizQuestion, optionA,optionB, optionC, answer, quizName;
    private EditText questionInput, firstOption, secondOption, thirdOption, keyOption, quizIndex;
    private final Validate val = new Validate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_mcq_quiz);

        questionInput= findViewById(R.id.question);
        firstOption= findViewById(R.id.option_A);
        secondOption= findViewById(R.id.option_B);
        thirdOption= findViewById(R.id.option_C);
        keyOption= findViewById(R.id.key);
        quizIndex= findViewById(R.id.quiz_index);

        Button submitButton = findViewById(R.id.submit_Button);
        submitButton.setOnClickListener(v -> {
            //store the text into variables
            quizQuestion = questionInput.getText().toString();
            optionA = firstOption.getText().toString();
            optionB = secondOption.getText().toString();
            optionC = thirdOption.getText().toString();
            answer = keyOption.getText().toString();
            quizName = quizIndex.getText().toString();

            if(!val.isValidMcqInput(quizQuestion, optionA, optionB, optionC, answer, quizName)) {
                showToast("Error! Must define a question and three options!");
            }
            else if (!val.containsAnswer(optionA, optionB, optionC, answer)){
                showToast("Error! Must define a valid answer!");
            }else{
                Question newQuestion=new Question(quizQuestion, optionA, optionB, optionC, answer);
                AccessQuiz accessQuiz = new AccessQuiz();
                accessQuiz.addQuiz(newQuestion, quizName);

                //reset the "EditText" fields
                questionInput.setText("");
                firstOption.setText("");
                secondOption.setText("");
                thirdOption.setText("");
                keyOption.setText("");
                quizIndex.setText("");
                showToast("Question Added!");
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(MakeMcqQuizActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}