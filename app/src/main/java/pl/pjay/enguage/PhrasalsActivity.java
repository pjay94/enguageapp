package pl.pjay.enguage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class PhrasalsActivity extends AppCompatActivity {

    private TextView phrasalTextView;
    private Button phrasalAnswerButton1;
    private Button phrasalAnswerButton2;
    private String answer;
    private TextView streakNumberTextView;
    private PhrasalsBase phrasalsBase = new PhrasalsBase();
    private int phrasalNumber = 0;
    private int lastPhrasalNumber = 1;
    private int correctAnswerStreak = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrasals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phrasalTextView = (TextView) findViewById(R.id.phrasalTextView);
        phrasalAnswerButton1 = (Button) findViewById(R.id.phrasalAnswerButton1);
        phrasalAnswerButton2 = (Button) findViewById(R.id.phrasalAnswerButton2);
        streakNumberTextView = (TextView) findViewById(R.id.streakNumberTextView);
        updatePhrasal();

        final Toast toastCorrect = Toast.makeText(PhrasalsActivity.this, "Poprawna odpowiedź.", Toast.LENGTH_SHORT);
        final Toast toastIncorrect = Toast.makeText(PhrasalsActivity.this, "Zła odpowiedź.", Toast.LENGTH_SHORT);
        final Handler handler = new Handler();


        phrasalAnswerButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(phrasalAnswerButton1.getText().toString().equals(answer)){
                    updatePhrasal();
                    toastCorrect.show();
                    correctAnswerStreak++;
                    streakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toastCorrect.cancel();
                        }
                    }, 1000);

                }else{
                    updatePhrasal();
                    toastIncorrect.show();
                    correctAnswerStreak = 0;
                    streakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toastIncorrect.cancel();
                        }
                    }, 1000);
                }
            }
        });

        phrasalAnswerButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(phrasalAnswerButton2.getText().toString().equals(answer)){
                    updatePhrasal();
                    toastCorrect.show();
                    correctAnswerStreak++;
                    streakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toastCorrect.cancel();
                        }
                    }, 1000);

                }else{
                    updatePhrasal();
                    toastIncorrect.show();
                    correctAnswerStreak = 0;
                    streakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toastIncorrect.cancel();
                        }
                    }, 1000);
                }
            }
        });

    }

    public void updatePhrasal(){
        phrasalNumber = new Random().nextInt(129);
        while(phrasalNumber == lastPhrasalNumber){
            phrasalNumber = new Random().nextInt(129);
        }
        phrasalTextView.setText(phrasalsBase.getMeaning(phrasalNumber));
        phrasalAnswerButton1.setText(phrasalsBase.getOption1(phrasalNumber));
        phrasalAnswerButton2.setText(phrasalsBase.getOption2(phrasalNumber));

        answer = phrasalsBase.getAnswer(phrasalNumber);
        lastPhrasalNumber = phrasalNumber;
    }

}
