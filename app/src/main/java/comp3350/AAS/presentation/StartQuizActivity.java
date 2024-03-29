package comp3350.AAS.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.R;
import java.util.ArrayList;

import comp3350.AAS.business.Calculate;

public class StartQuizActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton button1, button2, button3;
    private int currIndex;
    static int currPosition;
    private ArrayList<Question> questionArrayList;  // To store all questions on a quiz list
    private Quiz selectedQuiz;  // To store all questions on a quiz list
    private AccessQuiz accessQuiz;
    private Calculate cal = new Calculate();
    private String selectedAnswer;
    private int numRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        // To get all quizzes
        accessQuiz = new AccessQuiz();
        ArrayList<Quiz> quizArrayList = accessQuiz.getQuizList();

        // To get selected quiz list
        selectedQuiz = quizArrayList.get(currPosition);
        questionArrayList = quizArrayList.get(currPosition).getQuestionList();
        numRemaining = questionArrayList.size()-1;

        if (questionArrayList.size()>0){
            currIndex=0;
            generateQuestion();
        }
        currIndex=0;
    }

    // Generate a question for user to answer
    public void generateQuestion(){
        // set id to the question and three buttons
        TextView questionText = findViewById(R.id.question_text);
        TextView numRemain = findViewById(R.id.remain_quiz);
        String remainQuizNum = "Remaining #: "+numRemaining;

        radioGroup=findViewById(R.id.choose_group);
        button1= findViewById(R.id.btn_1);
        button2=findViewById(R.id.btn_2);
        button3=findViewById(R.id.btn_3);

        questionText.setText(questionArrayList.get(currIndex).getQuestion());
        button1.setText(questionArrayList.get(currIndex).getOption1());
        button2.setText(questionArrayList.get(currIndex).getOption2());
        button3.setText(questionArrayList.get(currIndex).getOption3());
        numRemain.setText(remainQuizNum);

        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        Button nextQuizBtn =findViewById(R.id.next_quiz);
        nextQuizBtn.setOnClickListener(v -> getNextQuestion());
    }

    // Track which button is clicked
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radioGroup.setClickable(false);
        switch (checkedId){
            case R.id.btn_1:
                selectedAnswer=button1.getText().toString().substring(2);
                button1.setTextColor(Color.MAGENTA);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.BLACK);
                break;
            case R.id.btn_2:
                selectedAnswer=button2.getText().toString().substring(2);
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.MAGENTA);
                button3.setTextColor(Color.BLACK);
                break;
            case R.id.btn_3:
                selectedAnswer=button3.getText().toString().substring(2);
                button1.setTextColor(Color.BLACK);
                button2.setTextColor(Color.BLACK);
                button3.setTextColor(Color.MAGENTA);
                break;
        }
    }

    // Go to the next quiz question
    public void getNextQuestion(){
        cal.updateGrade(selectedQuiz, selectedQuiz.getQuestionList().get(currIndex), selectedAnswer);
        AlertDialog.Builder builder = new AlertDialog.Builder(StartQuizActivity.this);
        currIndex++;

        if (currIndex<questionArrayList.size()) {
            button1.setChecked(false);
            button2.setChecked(false);
            button3.setChecked(false);
            button1.setTextColor(Color.BLACK);
            button2.setTextColor(Color.BLACK);
            button3.setTextColor(Color.BLACK);
            radioGroup.check(0);
            numRemaining--;
            showToast("New question!");
            generateQuestion();
        } else {
            selectedQuiz.setCompleteStatus(true);
            accessQuiz.updateStatus(selectedQuiz.getQuizName(), "TRUE");
            accessQuiz.updateGrade(selectedQuiz.getQuizName(), selectedQuiz.getQuizResult());

            builder.setMessage("You have completed the quiz. Results can be viewed in the statistics page")
                    .setPositiveButton("Close", (dialog, which) -> StartQuizActivity.this.finish());
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void showToast(String text){
        Toast.makeText(StartQuizActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}