package comp3350.AAS.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.academicassistancesystem.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flash cards button
        Button button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(v -> openFlashCards());

        //setup the quizzes button
        //quizzes button
        Button button2 = (Button) findViewById(R.id.Button2);
        button2.setOnClickListener(v -> openQuizzes());

        //setup the Calendar button
        //Calendar button
        Button button3 = (Button) findViewById(R.id.Button3);
        button3.setOnClickListener(v -> openCalendar());
    }

    public void openFlashCards(){
        Intent intent = new Intent(this, FlashCardActivity.class);
        startActivity(intent);
    }

    public void openQuizzes(){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void openCalendar(){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}