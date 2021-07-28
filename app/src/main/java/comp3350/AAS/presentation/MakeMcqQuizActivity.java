package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.object.Question;
import comp3350.AAS.R;
import comp3350.AAS.business.Validate;


public class MakeMcqQuizActivity extends AppCompatActivity {
    private String quizQuestion, optionA,optionB, optionC, answer, quizName;
    private EditText questionInput, firstOption, secondOption, thirdOption, keyOption, quizIndex;
    private Validate val;
    private AccessQuiz accessQuiz;
    private ArrayList<String> quizNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_mcq_quiz);

        val = new Validate();
        accessQuiz = new AccessQuiz();
        quizNameList = accessQuiz.getQuizNames();
        Spinner spinnerQuizName = findViewById(R.id.spinner_quiz_name);
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
            String typedName = quizIndex.getText().toString();

            if (!typedName.equals("")){
                quizName = typedName;
            }

            if(!val.isValidMcqInput(quizQuestion, optionA, optionB, optionC, answer, quizName)) {
                showToast("Error! Must define a question and three options!");
            } else if (!val.containsAnswer(optionA, optionB, optionC, answer)){
                showToast("Error! Must define a valid answer!");
            } else {
                Question newQuestion=new Question(quizQuestion, optionA, optionB, optionC, answer);
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
            startActivity(new Intent(this, MakeMcqQuizActivity.class));
        });
    }

    private void showToast(String text){
        Toast.makeText(MakeMcqQuizActivity.this,text, Toast.LENGTH_SHORT).show();
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