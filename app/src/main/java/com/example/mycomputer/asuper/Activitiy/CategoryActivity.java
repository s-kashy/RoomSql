package com.example.mycomputer.asuper.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mycomputer.asuper.Classes.AdpterCategory;
import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.Category;
import com.example.mycomputer.asuper.MainActivity;
import com.example.mycomputer.asuper.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private List<Category> categories;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        AppDatabase appDatabase=AppDatabase.getAppDatabase(this);

        categories = new ArrayList<>();

        categories = appDatabase.listOfDaoCategory().getAll();
        recyclerView = (RecyclerView) findViewById(R.id.resProduct);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdpterCategory(categories, this);
        recyclerView.setAdapter(adapter);
     //   insertCategorys();
    }
    public void insertCategorys() {
        String[] temp = getResources().getStringArray(R.array.category);
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            categories.add(new Category(temp[i]));
        }
        AppDatabase appDatabase=AppDatabase.getAppDatabase(this);
        appDatabase.listOfDaoCategory().insert(categories);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signout:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.checkout:
                Intent intent = new Intent(this, CheckOutActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        return true;
    }
}
