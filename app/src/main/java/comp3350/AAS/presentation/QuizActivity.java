package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.academicassistancesystem.R;
import java.util.ArrayList;
import java.util.Random;

import comp3350.AAS.database.QuizDatabase;
import comp3350.AAS.object.Quiz;


public class QuizActivity extends AppCompatActivity {
    private TextView wordText;
    private RadioGroup radioGroup;
    private RadioButton button1, button2, button3;
    private ArrayList<QuizDatabase> data;  // To store all quizzes
    private ArrayList<Integer> list;  // To make an order of quizzes' question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        init();
    }

    public void onCheckedChanged(RadioGroup group, int checkedId){
        radioGroup.setClickable(false);
        switch (checkedId){
            case R.id.btn_1:
                String msg1=button1.getText().toString().substring(3);
                setBtnColor(msg1, button1);
                break;
            case R.id.btn_2:
                String msg2=button2.getText().toString().substring(3);
                setBtnColor(msg2, button2);
                break;
            case R.id.btn_3:
                String msg3=button3.getText().toString().substring(3);
                setBtnColor(msg3, button3);
                break;
        }
    }

    public void init(){
        // TODO
        data=new ArrayList<>();

        list=new ArrayList<Integer>();
        Random r = new Random();
        int num;
        while (list.size()<5){
            num=r.nextInt(5);
            if (!list.contains(num)){
                list.add(num);
            }
        }

        wordText=(TextView) findViewById(R.id.word_text);

        // set id to three button
        radioGroup=(RadioGroup) findViewById(R.id.choose_group);
        button1=(RadioButton) findViewById(R.id.btn_1);
        button2=(RadioButton) findViewById(R.id.btn_2);
        button3=(RadioButton) findViewById(R.id.btn_3);

        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    private void setBtnColor(String msg, RadioButton btn){
        //temp: Ottawa
        if (msg.equals("Ottawa")){
            wordText.setTextColor(Color.GREEN);
            btn.setTextColor(Color.GREEN);
        }else {
            wordText.setTextColor(Color.RED);
            btn.setTextColor(Color.RED);
        }
    }

    // restore color to black when access the new quiz
    private void restoreTextColor(){
        button1.setChecked(false);
        button2.setChecked(false);
        button3.setChecked(false);

        wordText.setTextColor(Color.BLACK);
        button1.setTextColor(Color.BLACK);
        button2.setTextColor(Color.BLACK);
        button3.setTextColor(Color.BLACK);
    }

    // go to the next quiz question
    public void getNextQuestion(){
        restoreTextColor();

    }




}