package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import comp3350.AAS.R;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.business.Validate;
import comp3350.AAS.object.Question;

public class MakeTfQuizActivity extends AppCompatActivity {
    private String quizQuestion, quizName;
    private String answer;
    private EditText tfQuestion, quizBelong;
    private RadioGroup radioGroup;
    private Validate val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_tf_quiz);

        init();
    }

    public void init(){
        val = new Validate();
        answer = "";
        tfQuestion = findViewById(R.id.TFQuestion);
        radioGroup =findViewById(R.id.makeTFQuestion);
        quizBelong = findViewById(R.id.quiz_belong);
        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        Button submitButton = findViewById(R.id.submit_Btn);
        submitButton.setOnClickListener(v -> {
            //store the text into variables
            quizQuestion = tfQuestion.getText().toString();
            quizName = quizBelong.getText().toString();

            if(!val.isValidTrueOrFalseInput(quizQuestion, answer, quizName)) {
                showToast("Error! Must define a question and True or False");
            }else{
                Question newQuestion = new Question(quizQuestion, "True", "False", "", answer);
                AccessQuiz accessQuiz = new AccessQuiz();
                accessQuiz.addQuiz(newQuestion, quizName);

                //reset the "EditText" fields
                tfQuestion.setText("");
                radioGroup.check(0);
                quizBelong.setText("");
                showToast("Question Added!");
            }
        });
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radioGroup.setClickable(false);
        switch (checkedId){
            case R.id.True:
                answer = "True";
                break;
            case R.id.False:
                answer = "False";
                break;
        }
    }

    private void showToast(String text) {
        Toast.makeText(MakeTfQuizActivity.this,text, Toast.LENGTH_SHORT).show();
    }

}