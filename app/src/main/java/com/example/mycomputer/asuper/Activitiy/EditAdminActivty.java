package com.example.mycomputer.asuper.Activitiy;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mycomputer.asuper.Classes.AdperEditAdmin;
import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.ISqlManger;
import com.example.mycomputer.asuper.Classes.Product;
import com.example.mycomputer.asuper.Classes.SqlManger;
import com.example.mycomputer.asuper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EditAdminActivty extends AppCompatActivity {
    private List<Product> prodactList;
    private AdperEditAdmin adpter;
    private AppDatabase appDatabase;
    ISqlManger isqlManger;
    String tempUnit;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);
        appDatabase = AppDatabase.getAppDatabase(this);
        ListView listView = (ListView) findViewById(R.id.list);
        AppDatabase appDatabase=AppDatabase.getAppDatabase(this);

        prodactList = new ArrayList<>();
        prodactList=appDatabase.listofDaoProduct().getAll();
        adpter = new AdperEditAdmin(this, 0, prodactList);
        listView.setAdapter(adpter);

        isqlManger = new SqlManger();
//        isqlManger.getAllProduct(this, new Consumer<List<Product>>() {
//            @Override
//            public void accept(List<Product> products) {
//                prodactList.addAll(products);
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adpter.getItem(position);
                showDialog(product, position);


            }
        });


    }

    public void showDialog(final Product product, final int index) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dailog_admin);
        dialog.setCancelable(false);
        TextView txProName = (TextView) dialog.findViewById(R.id.txNamePro);
        TextView txCatName = (TextView) dialog.findViewById(R.id.txNameCat);
        final EditText edPrice = (EditText) dialog.findViewById(R.id.edPrice);
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spiner);
        final TextView txUnit = (TextView) dialog.findViewById(R.id.txUnit);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txUnit.setText(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txUnit.setText( product.getUnit().toString());

        edPrice.setText(String.valueOf(product.getPrice()));
        txCatName.setText(product.getName().toString());
        txProName.setText(product.getNameOfProduct().toString());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setPrice(Integer.valueOf(edPrice.getText().toString()));
                product.setUnit(txUnit.getText().toString());
                prodactList.set(index,product);
                adpter.notifyDataSetChanged();
                updateItem(product);
                dialog.dismiss();

            }
        });


        dialog.show();

    }

    public void updateItem(Product p) {
        appDatabase.listofDaoProduct().updateProdce(p);
    }
}
