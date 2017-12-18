package pl.pjay.enguage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent startMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(startMainActivity);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }
}
