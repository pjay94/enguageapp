package pl.pjay.enguage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    Intent intent;
    public void clickAddWord(View view) {
        intent = new Intent(OptionsActivity.this, AddWordActivity.class);
        startActivity(intent);
    }

    public void clickDeleteWord(View view) {
        intent = new Intent(OptionsActivity.this, DeleteWordActivity.class);
        startActivity(intent);
    }

    public void clickDatabaseManagement(View view) {
        intent = new Intent(OptionsActivity.this, DatabaseManagementActivity.class);
        startActivity(intent);
    }
}
