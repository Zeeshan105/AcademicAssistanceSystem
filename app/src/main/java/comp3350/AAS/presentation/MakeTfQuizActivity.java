package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

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
    private AccessQuiz accessQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_tf_quiz);

        init();
    }

    public void init(){
        val = new Validate();
        accessQuiz = new AccessQuiz();
        ArrayList<String> quizNameList = accessQuiz.getQuizNames();
        Spinner spinnerQuizName = findViewById(R.id.spinner_quiz_name_2);
        ArrayAdapter<String> adapterQuizName = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, quizNameList);

        spinnerQuizName.setAdapter(adapterQuizName);
        spinnerQuizName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quizName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        answer = "";
        tfQuestion = findViewById(R.id.TFQuestion);
        radioGroup =findViewById(R.id.makeTFQuestion);
        quizBelong = findViewById(R.id.quiz_belong);

        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        Button submitButton = findViewById(R.id.submit_Btn);
        submitButton.setOnClickListener(v -> {
            //store the text into variables
            quizQuestion = tfQuestion.getText().toString();
            String typedName = quizBelong.getText().toString();

            if (!typedName.equals("")){
                quizName = typedName;
            }

            if(!val.isValidTrueOrFalseInput(quizQuestion, answer, quizName)) {
                showToast("Error! Must define a question and True or False");
            }else{
                Question newQuestion = new Question(quizQuestion, "True", "False", "", answer);
                accessQuiz.addQuiz(newQuestion, quizName);

                //reset the "EditText" fields
                tfQuestion.setText("");
                radioGroup.check(0);
                quizBelong.setText("");
                showToast("Question Added!");
            }
            startActivity(new Intent(this, MakeTfQuizActivity.class));
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


    public void backToQuizHome(){
        Intent intent = new Intent(this, QuizHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        backToQuizHome();
        super.onBackPressed();
    }

}