package pl.pjay.enguage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    public static String CATEGORY_NAME = " ";
    private Intent intent;
    private TextView categoryNameTextView;
    private TextView wordToGuessTextView;
    private EditText wordAnswerEditText;
    private TextView wordStreakNumberTextView;
    private int wordId;
    private SQLiteOpenHelper wordsDatabaseHelper = new WordsDatabaseHelper(this);
    private SQLiteDatabase db;
    private String chosenCategoryName;
    private String sqlCategoryName;
    private String wordAnswer;
    private String wordToGuess;
    private String engWord1;
    private String engWord2;
    private Cursor cursor;
    private int numberOfWords;
    private int correctAnswerStreak = 0;
    private int lastExcludedInt = 0;


    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryNameTextView = (TextView) findViewById(R.id.categoryNameTextView);
        wordStreakNumberTextView = (TextView) findViewById(R.id.wordStreakNumberTextView);
        intent = getIntent();

        chosenCategoryName = intent.getStringExtra(GameActivity.CATEGORY_NAME);
        sqlCategoryName = chosenCategoryName;
        categoryNameTextView.setText(chosenCategoryName);
        numberOfWords = getNumberOfWordsInDatabase();
		if(!chosenCategoryName.equals("WSZYSTKIE SŁOWA")){
			Toast.makeText(this, "Liczba wyrażeń w kategorii: " + numberOfWords, Toast.LENGTH_LONG).show();
		}
        printWord();
    }

    public void printWord() {
        boolean toPrintFlag = false;
        while(toPrintFlag == false){
            try {
                openDatabaseToRead();
                if(randomId() == lastExcludedInt){
                 continue;
                }

                if (chosenCategoryName.equals("WSZYSTKIE SŁOWA")) {
                    sqlCategoryName = randomiseCategory();
                }
                cursor = db.query(sqlCategoryName, new String[]{"PLWORD", "ENGWORD1", "ENGWORD2"},
                        "_id = ?", new String[]{Integer.toString(wordId)}, null, null, null);

                if (cursor.moveToFirst()) {
                    wordToGuess = cursor.getString(0);
                    engWord1 = cursor.getString(1);
                    engWord2 = cursor.getString(2);
                    if(wordToGuess == null) {
                        toPrintFlag = false;
                    }else {
                        toPrintFlag = true;
                        lastExcludedInt = wordId;
                        wordToGuessTextView = (TextView) findViewById(R.id.wordToGuessTextView);
                        wordToGuessTextView.setText(wordToGuess);
                    }
                }
                cursor.close();
                db.close();
            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Brak dostępu do bazy danych.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public int randomId() {
        int id = getMaxId();
        wordId = new Random().nextInt(id + 1);
        while (wordId == 0) {
            wordId = new Random().nextInt(id + 1);
        }
        return wordId;
    }

    public void openDatabaseToRead() {
        db = wordsDatabaseHelper.getReadableDatabase();
    }

    public void clickCheckWord(View view) {
        wordAnswerEditText = (EditText) findViewById(R.id.wordAnswerEditText);
        wordAnswer = wordAnswerEditText.getText().toString().toLowerCase().trim();
        if(wordAnswer.equals("")) {
            Toast.makeText(GameActivity.this, "Nie wpisano słowa. Spróbuj jeszcze raz.", Toast.LENGTH_SHORT).show();
        }else {
            if (wordAnswer.equals(engWord1) || wordAnswer.equals(engWord2)) {
                Toast.makeText(getBaseContext(), "Podano prawidłowe słowo.", Toast.LENGTH_SHORT).show();
                correctAnswerStreak++;
                wordStreakNumberTextView.setText(Integer.toString(correctAnswerStreak));
            } else {
                if(engWord2 != null){
                        Toast.makeText(getBaseContext(), "Podano nieprawidłowe słowo. \nPowinno być: " + engWord1 + " lub " + engWord2, Toast.LENGTH_SHORT).show();
                        correctAnswerStreak = 0;
                        wordStreakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                }else{
                    Toast.makeText(getBaseContext(), "Podano nieprawidłowe słowo. \nPowinno być: " + engWord1, Toast.LENGTH_SHORT).show();
                    correctAnswerStreak = 0;
                    wordStreakNumberTextView.setText(Integer.toString(correctAnswerStreak));
                }
            }
            wordAnswerEditText.setText("");
            wordAnswerEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(wordAnswerEditText, InputMethodManager.SHOW_IMPLICIT);
            printWord();
        }
    }

    public String randomiseCategory() {
        String[] randomCategories = {"ABC", "DEF", "GHI",
                "JKL", "MNO", "PQRS",
                "TUV", "WXYZ"};
        int randomI = new Random().nextInt(8);
        String randomCategory = randomCategories[randomI];
        return randomCategory;
    }

    public int getMaxId() {
        openDatabaseToRead();
        if (chosenCategoryName.equals("WSZYSTKIE SŁOWA")) {
            sqlCategoryName = randomiseCategory();
        }
        cursor = db.query(sqlCategoryName, new String[]{"_id"},
                null, null, null, null, null);

        int id = 0;

        if (cursor.moveToLast()){
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }

    public int getNumberOfWordsInDatabase(){
        openDatabaseToRead();
        int wordCounter = 0;
        int maxIdNumber = getMaxId();
        String checkedField;
        if (chosenCategoryName.equals("WSZYSTKIE SŁOWA")) {
            sqlCategoryName = randomiseCategory();
        }

        for(int i = 1; i <= maxIdNumber; i++) {
            cursor = db.query(sqlCategoryName, new String[]{"_id", "PLWORD"},
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
        return wordCounter;
    }
}
