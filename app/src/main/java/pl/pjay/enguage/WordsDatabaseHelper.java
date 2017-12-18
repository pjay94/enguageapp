package pl.pjay.enguage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WordsDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "WORDS";
    private static final int DB_VERSION = 1;

    WordsDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1) {
            db.execSQL("CREATE TABLE ABC (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "ABC", "auto", "car", null);
            insertWord(db, "ABC", "brązowy", "brown", "bronze");
            insertWord(db, "ABC", "ciastko", "cookie", "cooky");
            insertWord(db, "ABC", "aforyzm", "aphorism", null);
            insertWord(db, "ABC", "bankiet", "banquet", null);
            insertWord(db, "ABC", "całka", "integral", null);


            db.execSQL("CREATE TABLE DEF (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "DEF", "dystans", "distance", null);
            insertWord(db, "DEF", "echo", "echo", null);
            insertWord(db, "DEF", "foka", "seal", null);
            insertWord(db, "DEF", "dystrybucja", "distribution", null);
            insertWord(db, "DEF", "ewangelia", "gospel", null);
            insertWord(db, "DEF", "fala", "wave", null);


            db.execSQL("CREATE TABLE GHI (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "GHI", "gruszka", "pear", null);
            insertWord(db, "GHI", "hiena", "hyena", "hyaena");
            insertWord(db, "GHI", "igła", "needle", null);
            insertWord(db, "GHI", "gorączka", "fever", null);
            insertWord(db, "GHI", "hasło", "password", null);
            insertWord(db, "GHI", "ikona", "icon", null);


            db.execSQL("CREATE TABLE JKL (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "JKL", "jacht", "yacht", null);
            insertWord(db, "JKL", "kapitulacja", "capitulation", null);
            insertWord(db, "JKL", "lawa", "lava", null);
            insertWord(db, "JKL", "jabłko", "apple", null);
            insertWord(db, "JKL", "korba", "crank", null);
            insertWord(db, "JKL", "lawina", "avalanche", null);

            db.execSQL("CREATE TABLE MNO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "MNO", "mleko", "milk", null);
            insertWord(db, "MNO", "naklejka", "sticker", null);
            insertWord(db, "MNO", "odpowiedź", "answer", "reply");
            insertWord(db, "MNO", "masło", "butter", null);
            insertWord(db, "MNO", "nałóg", "habit", "addiction");
            insertWord(db, "MNO", "ofensywa", "offensive", null);

            db.execSQL("CREATE TABLE PQRS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "PQRS", "pralka", "washing machine", null);
            insertWord(db, "PQRS", "rybak", "fisherman", null);
            insertWord(db, "PQRS", "szyba", "glass", null);
            insertWord(db, "PQRS", "pies", "dog", null);
            insertWord(db, "PQRS", "robak", "worm", "bug");
            insertWord(db, "PQRS", "przyjazd", "arrival", null);


            db.execSQL("CREATE TABLE TUV (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "TUV", "trening", "training", null);
            insertWord(db, "TUV", "uprząż", "harness", "trappings");
            insertWord(db, "TUV", "trawnik", "lawn", "grass");
            insertWord(db, "TUV", "trasa", "way", "route");
            insertWord(db, "TUV", "upał", "heat", null);
            insertWord(db, "TUV", "upadek", "fall", "collapse");

            db.execSQL("CREATE TABLE WXYZ (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "PLWORD TEXT, "
                    + "ENGWORD1 TEXT, "
                    + "ENGWORD2 TEXT);");

            insertWord(db, "WXYZ", "wino", "wine", null);
            insertWord(db, "WXYZ", "wiatr", "wind", null);
            insertWord(db, "WXYZ", "ząb", "tooth", null);
            insertWord(db, "WXYZ", "zazdrość", "jealousy", "envy");
            insertWord(db, "WXYZ", "zima", "winter", null);
            insertWord(db, "WXYZ", "żaba", "frog", null);

        }
    }

    public static void insertWord(SQLiteDatabase db, String tableName, String plWord, String engWord1, String engWord2){
        ContentValues wordValues = new ContentValues();
        wordValues.put("PLWORD", plWord);
        wordValues.put("ENGWORD1", engWord1);
        wordValues.put("ENGWORD2", engWord2);
        db.insert(tableName, null, wordValues);
    }

    public void addNewWord(String tableName, String plWord, String engWord1, String engWord2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wordValues = new ContentValues();
        wordValues.put("PLWORD", plWord);
        wordValues.put("ENGWORD1", engWord1);
        wordValues.put("ENGWORD2", engWord2);
        db.insert(tableName, null, wordValues);
    }

    public int deleteWord(String tableName, String plWord){
        SQLiteDatabase db = this.getWritableDatabase();
        int result;
        result = db.delete(tableName, "PLWORD = ?", new String[]{plWord});
        return result;
    }
}