package com.example.mycomputer.asuper.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.Product;
import com.example.mycomputer.asuper.MainActivity;
import com.example.mycomputer.asuper.R;

public class AddPruductActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edName, edPrice;
    Spinner spUnit;
    Spinner spCategory;
    Product newProduct;
    AppDatabase appDatabase;
    String unitTemp;
    String catTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pruduct);
        newProduct = new Product();
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        edName = (EditText) findViewById(R.id.etName);
        edPrice = (EditText) findViewById(R.id.edPrice);
        spCategory = (Spinner) findViewById(R.id.spCategory);
        spUnit = (Spinner) findViewById(R.id.spUnit);
        // AppDatabase.destroyInstance();
        appDatabase = AppDatabase.getAppDatabase(this);

        //  appDatabase.listofDaoProduct().deleteAll();
        int id = appDatabase.listofDaoProduct().countNumberOfRow();
    ///    Toast.makeText(AddPruductActivity.this, "amount in table" + id, Toast.LENGTH_SHORT).show();


        spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitTemp = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catTemp = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProduct.setUnit(unitTemp);
                newProduct.setName(catTemp);
                newProduct.setNameOfProduct(edName.getText().toString());
                if (!edPrice.getText().toString().equals("")) {
                    newProduct.setPrice(Integer.parseInt(edPrice.getText().toString()));
                }

                if (validate()) {
                    appDatabase.listofDaoProduct().insertProdect(newProduct);
                    edName.setText("");
                    edPrice.setText("");

                    int id = appDatabase.listofDaoProduct().countNumberOfRow();
                    Toast.makeText(AddPruductActivity.this, "amount in table" + id, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddPruductActivity.this, "maust complite all fieds", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validate() {
        String temp = String.valueOf(newProduct.getPrice());
        if ((newProduct.getNameOfProduct() == null || newProduct.getNameOfProduct()//
                .equals("")) || (newProduct.getUnit() == null || newProduct.getUnit().equals(""))
                || temp.equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signout) {
            AppDatabase.destroyInstance();
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
            finish();}

            else if(item.getItemId()==R.id.edit){
            Intent intent1=new Intent(this,EditAdminActivty.class);
            startActivity(intent1);
        }

        return true;
    }
}
