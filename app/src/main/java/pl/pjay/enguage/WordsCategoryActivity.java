package pl.pjay.enguage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class WordsCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    Intent intent;
    String categoryName;

    public void clickCategoryABC(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "ABC";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryDEF(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "DEF";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryGHI(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "GHI";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryJKL(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "JKL";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryMNO(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "MNO";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryPQRS(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "PQRS";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryTUV(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "TUV";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryWXYZ(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "WXYZ";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }

    public void clickCategoryAll(View view) {
        intent = new Intent(WordsCategoryActivity.this, GameActivity.class);
        categoryName = "Wszystkie słowa";
        intent.putExtra(GameActivity.CATEGORY_NAME, categoryName.toUpperCase());
        startActivity(intent);
    }
}
