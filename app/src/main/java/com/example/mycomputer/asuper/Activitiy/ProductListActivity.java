package com.example.mycomputer.asuper.Activitiy;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mycomputer.asuper.Classes.AdpterProduct;
import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.Category;
import com.example.mycomputer.asuper.Classes.ISqlManger;
import com.example.mycomputer.asuper.Classes.Product;
import com.example.mycomputer.asuper.Classes.SqlManger;
import com.example.mycomputer.asuper.MainActivity;
import com.example.mycomputer.asuper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ProductListActivity extends AppCompatActivity {
    private List<Product> productList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    AppDatabase appDatabase;
    private Category c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            c = (Category) intent.getExtras().getSerializable("key");
            productList = new ArrayList<>();
            recyclerView = (RecyclerView) findViewById(R.id.resList);
            ISqlManger sqlManger = new SqlManger();
            AppDatabase appDatabase=AppDatabase.getAppDatabase(this);
            ////////////////List<Product>p=appDatabase.listofDaoProduct().getAll();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            productList=appDatabase.listofDaoProduct().getAllProductById(c.getNameOfCategory());
            adapter=new AdpterProduct(productList,this);
            recyclerView.setAdapter(adapter);
//            sqlManger.getAllProductById(c.getNameOfCategory(), this, new Consumer<List<Product>>() {
//                @Override
//                public void accept(List<Product> products) {
//                    productList.addAll(products);
//                    adapter = new AdpterProduct(productList, ProductListActivity.this);
//                    recyclerView.setAdapter(adapter);
//                }
//
//            });
        }

    }

//            sqlManger.getAllProduct(this, new Consumer<List<Product>>() {
//                @Override
//                public void accept(List<Product> products) {
//                    Toast.makeText(ProductListActivity.this, ""+products.size(), Toast.LENGTH_SHORT).show();
//                    productList.addAll(products);
//                    adapter = new AdpterProduct(productList, ProductListActivity.this);
//                    recyclerView.setAdapter(adapter);
//                }
//            });
//
//        }
    //  }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkout, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                AppDatabase.destroyInstance();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.checkout:
                Intent intent1 = new Intent(this, CheckOutActivity.class);
                startActivity(intent1);
                finish();
                break;
        }

        return true;
    }

}
