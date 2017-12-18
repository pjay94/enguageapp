package pl.pjay.enguage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Intent intent;

    public void clickTranslateWord(View view) {
        intent = new Intent(MainActivity.this, TranslateWordActivity.class);
        startActivity(intent);
    }

    public void clickWords(View view) {
        intent = new Intent(MainActivity.this, WordsCategoryActivity.class);
        startActivity(intent);
    }

    public void clickPhrasalVerbs(View view) {
        intent = new Intent(MainActivity.this, PhrasalsActivity.class);
        startActivity(intent);
    }

    public void clickOptions(View view) {
        intent = new Intent(MainActivity.this, OptionsActivity.class);
        startActivity(intent);
    }

    public void clickExit(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
