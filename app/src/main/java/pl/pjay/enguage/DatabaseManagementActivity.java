package pl.pjay.enguage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class DatabaseManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void clickImportWords(View view) {
        Toast.makeText(this, "Importowanie...", Toast.LENGTH_SHORT).show();
        try {
            File esdDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();

            if (esdDirectory.canWrite()) {
                String currentDBPath = "/data/pl.piotrs.enguage/databases/" + WordsDatabaseHelper.DB_NAME;
                String backupDBPath  = WordsDatabaseHelper.DB_NAME;
                File backupDB = new File(dataDirectory, currentDBPath);
                File currentDB  = new File(esdDirectory, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel source = new FileInputStream(currentDB).getChannel();
                    FileChannel destination = new FileOutputStream(backupDB).getChannel();
                    destination.transferFrom(source, 0, source.size());
                    source.close();
                    destination.close();
                    Toast.makeText(getBaseContext(), "Baza danych została importowana.", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void clickExportWords(View view) {
        Toast.makeText(this, "Eksportowanie...", Toast.LENGTH_SHORT).show();
        try {
            File esdDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();

            if (esdDirectory.canWrite()) {
                String currentDBPath = "/data/pl.piotrs.enguage/databases/" + WordsDatabaseHelper.DB_NAME;
                String backupDBPath = WordsDatabaseHelper.DB_NAME;
                File currentDB = new File(dataDirectory, currentDBPath);
                File backupDB = new File(esdDirectory, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel source = new FileInputStream(currentDB).getChannel();
                    FileChannel destination = new FileOutputStream(backupDB).getChannel();
                    destination.transferFrom(source, 0, source.size());
                    source.close();
                    destination.close();
                    Toast.makeText(getBaseContext(), "Baza danych została eksportowana.", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void clickInstruction(View view) {
        Intent intent = new Intent(DatabaseManagementActivity.this, InstructionActivity.class);
        startActivity(intent);
    }
}
