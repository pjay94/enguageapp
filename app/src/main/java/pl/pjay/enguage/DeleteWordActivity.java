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

public class DeleteWordActivity extends AppCompatActivity {
    private String chosenCategory;
    private Spinner categorySpinner;
    private EditText deletePlWordEditText;
    private String plWordToDelete;
    private boolean toDeleteFlag;
    private boolean wordExistsFlag;
    private WordsDatabaseHelper wordsDatabaseHelper = new WordsDatabaseHelper(this);
    private SQLiteDatabase db;
    private Cursor cursor;
    private int maxNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_word);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categorySpinner = (Spinner) findViewById(R.id.deleteWordCategorySpinner);
        deletePlWordEditText = (EditText) findViewById(R.id.deletePlWordEditText);
    }

    public void clickDeletingWord(View view) {
        getWordParametersToDelete();
        toDeleteFlag = getNumberOfWords();
        if (toDeleteFlag == true) {
            wordExistsFlag = checkPlWord();
            if (wordExistsFlag == true) {
                if(wordsDatabaseHelper.deleteWord(chosenCategory, plWordToDelete) > 0){
                    Toast.makeText(DeleteWordActivity.this, "Usunięto wyrażenie: " + plWordToDelete, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DeleteWordActivity.this, "Nie udało się usunąć wyrażenia." + plWordToDelete, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(DeleteWordActivity.this, "Słowa nie ma w podanej kategorii.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(DeleteWordActivity.this, "Musi być więcej słów w kategorii, żeby można było przeprowadzić usunięcie.", Toast.LENGTH_LONG).show();
        }
        setEditTextBlank();
    }

    private void getWordParametersToDelete(){
        chosenCategory = String.valueOf(categorySpinner.getSelectedItem());
        plWordToDelete = deletePlWordEditText.getText().toString().toLowerCase().trim();
    }

    private boolean checkPlWord(){
        boolean wordExists = true;
        try{
            openDatabaseToRead();
            String wordToFind = plWordToDelete;
            cursor = db.query(chosenCategory, new String[]{"PLWORD"},
                "PLWORD = ?", new String[]{wordToFind}, null, null, null);
        if(cursor.moveToFirst()) {
            wordExists = true;
            }else {
            wordExists = false;
        }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Brak dostępu do bazy danych.", Toast.LENGTH_SHORT);
            toast.show();
        }
        return wordExists;
        }

    private boolean getNumberOfWords(){
        boolean toDelete = true;
            openDatabaseToRead();
            cursor = db.query(chosenCategory, new String[]{"_id"},
                    null, null, null, null, null);

            int id = 0;
            if (cursor.moveToLast()) {
                id = cursor.getInt(0);
            }
            maxNumber = id;

            int wordCounter = 0;
            String checkedField;

            for(int i = 1; i <= maxNumber; i++) {
            cursor = db.query(chosenCategory, new String[]{"_id", "PLWORD"},
                    "_id = ?", new String[]{Integer.toString(i)}, null, null, null);

            if (cursor.moveToFirst()) {
                checkedField = cursor.getString(1);
                if (!checkedField.equals(null)) { 
                    wordCounter++;
                }
            }
        }
            cursor.close();
            db.close();

        if(wordCounter > 5){
            toDelete = true;
        }else{
            toDelete = false;
        }
        return toDelete;
    }

    public void openDatabaseToRead() {
        db = wordsDatabaseHelper.getReadableDatabase();
    }

    private void setEditTextBlank(){
        deletePlWordEditText.setText("");
    }
}
