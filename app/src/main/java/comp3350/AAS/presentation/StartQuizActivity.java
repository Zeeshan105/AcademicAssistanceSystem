package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView questionText;
    private RadioGroup radioGroup;
    private RadioButton button1, button2, button3;

    private int currIndex;
    private ArrayList<Quiz> quizArrayList;  // To store all quizzes
    private ArrayList<Question> questionArrayList;  // To store all questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        quizArrayList = services.createQuizDataAccess("QuizBase").getQuizList();

        //TODO this is not a fix number in get(?). It can be changed when user select different button on quiz list
        questionArrayList=quizArrayList.get(0).getQuestionList();


        if (questionArrayList.size()>0){
            currIndex=0;
            generateQuiz();
        }
        currIndex=0;
    }

    public void generateQuiz(){
        // set id to the question and three buttons
        questionText=(TextView) findViewById(R.id.question_text);
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
        nextQuizBtn.setOnClickListener(v -> getNextQuiz());

    }

    public void onCheckedChanged(RadioGroup group, int checkedId){
        radioGroup.setClickable(false);
        switch (checkedId){
            case R.id.btn_1:
                String msg1=button1.getText().toString();
                setBtnColor(msg1, button1);
                break;
            case R.id.btn_2:
                String msg2=button2.getText().toString();
                setBtnColor(msg2, button2);
                break;
            case R.id.btn_3:
                String msg3=button3.getText().toString();
                setBtnColor(msg3, button3);
                break;
        }
    }

    private void setBtnColor(String msg, RadioButton btn){
        if (msg.equals(questionArrayList.get(currIndex).getKey())){
            questionText.setTextColor(Color.GREEN);
            btn.setTextColor(Color.GREEN);
        }else {
            questionText.setTextColor(Color.RED);
            btn.setTextColor(Color.RED);
        }
    }

    // restore color to black when access the new quiz
    private void restoreTextColor(){
        button1.setChecked(false);
        button2.setChecked(false);
        button3.setChecked(false);

        questionText.setTextColor(Color.BLACK);
        button1.setTextColor(Color.BLACK);
        button2.setTextColor(Color.BLACK);
        button3.setTextColor(Color.BLACK);
    }

    // go to the next quiz question
    public void getNextQuiz(){
        currIndex++;

        if (currIndex<questionArrayList.size()) {
            restoreTextColor();
            showToast("New question!");
            generateQuiz();
        }else {
            showToast("No more question to be test!");
        }
    }

    private void showToast(String text){
        Toast.makeText(StartQuizActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}