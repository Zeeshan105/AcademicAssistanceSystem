package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.ASS.R;
import java.util.ArrayList;

import comp3350.AAS.application.services;

public class StartQuizActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton button1, button2, button3;

    private int currIndex;
    static int currPosition;
    private int numPassed;
    private boolean isCorrect;
    private ArrayList<Question> questionArrayList;  // To store all questions on a quiz list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        // To get all quizzes
        ArrayList<Quiz> quizArrayList = services.createQuizDataAccess("QuizBase").getQuizList();
        // To get selected quiz list
        questionArrayList= quizArrayList.get(currPosition).getQuestionList();
        numPassed=0;

        if (questionArrayList.size()>0){
            currIndex=0;
            generateQuestion();
        }
        numPassed=0;
        currIndex=0;
    }

    // Generate a question for user to answer
    public void generateQuestion(){
        // set id to the question and three buttons
        TextView questionText = (TextView) findViewById(R.id.question_text);
        radioGroup=(RadioGroup) findViewById(R.id.choose_group);
        button1=(RadioButton) findViewById(R.id.btn_1);
        button2=(RadioButton) findViewById(R.id.btn_2);
        button3=(RadioButton) findViewById(R.id.btn_3);

        questionText.setText(questionArrayList.get(currIndex).getQuestion());
        button1.setText(questionArrayList.get(currIndex).getOption1());
        button2.setText(questionArrayList.get(currIndex).getOption2());
        button3.setText(questionArrayList.get(currIndex).getOption3());

        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        Button nextQuizBtn = (Button) findViewById(R.id.next_quiz);
        nextQuizBtn.setOnClickListener(v -> getNextQuestion());
    }

    // Track which button is clicked
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radioGroup.setClickable(false);
        switch (checkedId){
            case R.id.btn_1:
                String msg1=button1.getText().toString();
                checkAnswer(msg1);
                break;
            case R.id.btn_2:
                String msg2=button2.getText().toString();
                checkAnswer(msg2);
                break;
            case R.id.btn_3:
                String msg3=button3.getText().toString();
                checkAnswer(msg3);
                break;
        }
    }

    // Check the button whether is correct
    private void checkAnswer(String msg){
        if (msg.equals(questionArrayList.get(currIndex).getKey())){
            isCorrect=true;
        }else {
            isCorrect=false;
        }
    }

    // Go to the next quiz question
    public void getNextQuestion(){
        if (isCorrect){
            numPassed++;
        }
        currIndex++;

        if (currIndex<questionArrayList.size()) {
            radioGroup.check(0);
            showToast("New question!");
            generateQuestion();
        }else {
            TextView score = (TextView) findViewById(R.id.quizScore);
            score.setText("You final score is "+numPassed+"/"+questionArrayList.size());
            showToast("No more question to be tested!");
        }
    }

    private void showToast(String text){
        Toast.makeText(StartQuizActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}