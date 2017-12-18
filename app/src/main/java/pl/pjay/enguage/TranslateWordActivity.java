package pl.pjay.enguage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class TranslateWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_word);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void clickWordToTranslate(View view) {
        EditText wordToTranslateEditText = (EditText) findViewById(R.id.insertWordToTranslateEditText);
        String wordToTranslate = wordToTranslateEditText.getText().toString().toLowerCase().trim();
        String dikiUrl = "https://www.diki.pl/slownik-angielskiego?q=";
        Intent dikiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dikiUrl + wordToTranslate));
        Intent dikiChooserIntent = Intent.createChooser(dikiIntent , "Wybierz przeglądarkę...");
        wordToTranslateEditText.setText("");
        startActivity(dikiChooserIntent);
    }
}
