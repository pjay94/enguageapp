package pl.pjay.enguage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddWordActivity extends AppCompatActivity {

    private WordsDatabaseHelper wordsDatabaseHelper = new WordsDatabaseHelper(this);
    private SQLiteDatabase db;
    private String plWordToAdd;
    private String engWord1ToAdd;
    private String engWord2ToAdd;
    private EditText addPlWordEditText;
    private EditText addEngWord1EditText;
    private EditText addEngWord2EditText;
    private Spinner categorySpinner;
    private String chosenCategory;
    private Cursor cursor;
    private boolean toAddFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPlWordEditText = (EditText) findViewById(R.id.addPlWordEditText);
        addEngWord1EditText = (EditText) findViewById(R.id.addEngWord1EditText);
        addEngWord2EditText = (EditText) findViewById(R.id.addEngWord2EditText);
        categorySpinner = (Spinner) findViewById(R.id.addWordCategorySpinner);
    }

    public void clickAddingWord(View view) {
        getWordParameters();
        if(plWordToAdd.length() <= 30){
            if(engWord1ToAdd.length() <= 30){
                if(engWord2ToAdd.length() <= 30){
                    toAddFlag = checkTheSameWord();
                    if(toAddFlag == true) {
                        if(engWord1ToAdd.equals("")){
                            Toast.makeText(AddWordActivity.this, "Spróbuj jeszcze raz.", Toast.LENGTH_LONG).show();
                        }else {
                            if (engWord2ToAdd.equals("")) {
                                wordsDatabaseHelper.addNewWord(chosenCategory, plWordToAdd, engWord1ToAdd, null);
                                setEditTextsBlank();
                                Toast.makeText(AddWordActivity.this, "Dodano do kategorii: " + chosenCategory, Toast.LENGTH_SHORT).show();
                                Toast.makeText(AddWordActivity.this, "Nie dodano drugiego tłumaczenia.", Toast.LENGTH_SHORT).show();
                            } else {
                                wordsDatabaseHelper.addNewWord(chosenCategory, plWordToAdd, engWord1ToAdd, engWord2ToAdd);
                                setEditTextsBlank();
                                Toast.makeText(AddWordActivity.this, "Dodano do kategorii: " + chosenCategory, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else{
                    Toast.makeText(AddWordActivity.this, "Wyrażenia nie mogą być dłuższe niż 30 znaków.", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(AddWordActivity.this, "Wyrażenia nie mogą być dłuższe niż 30 znaków.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(AddWordActivity.this, "Wyrażenia nie mogą być dłuższe niż 30 znaków.", Toast.LENGTH_LONG).show();
        }
    }

    private void getWordParameters(){
        chosenCategory = String.valueOf(categorySpinner.getSelectedItem());
        plWordToAdd = addPlWordEditText.getText().toString().toLowerCase().trim();
        engWord1ToAdd = addEngWord1EditText.getText().toString().toLowerCase().trim();
        engWord2ToAdd = addEngWord2EditText.getText().toString().toLowerCase().trim();
    }

    private void setEditTextsBlank(){
        addPlWordEditText.setText("");
        addEngWord1EditText.setText("");
        addEngWord2EditText.setText("");
    }

    public boolean checkTheSameWord(){
        boolean toAdd = true;
        try {
            openDatabaseToRead();
            cursor = db.query(chosenCategory, new String[]{"PLWORD"},
                    "PLWORD = ?", new String[]{plWordToAdd}, null, null, null);
            if(cursor.moveToFirst()) {
                String checkedWord = cursor.getString(0);
                if (checkedWord.equals(plWordToAdd)) {
                    Toast.makeText(AddWordActivity.this, "Podane polskie słowo już jest w tej kategorii.", Toast.LENGTH_LONG).show();
                    toAdd = false;
                } else {
                    toAdd = true;
                }
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Brak dostępu do bazy danych.", Toast.LENGTH_SHORT);
            toast.show();
        }
        return toAdd;
    }

    public void openDatabaseToRead() {
        db = wordsDatabaseHelper.getReadableDatabase();
    }

}
