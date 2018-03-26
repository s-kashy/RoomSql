package com.example.mycomputer.asuper.Activitiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycomputer.asuper.Classes.AdperShoping;
import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.GetAmount;
import com.example.mycomputer.asuper.Classes.ISqlManger;
import com.example.mycomputer.asuper.Classes.ShoppingCart;
import com.example.mycomputer.asuper.Classes.SqlManger;
import com.example.mycomputer.asuper.MainActivity;
import com.example.mycomputer.asuper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CheckOutActivity extends AppCompatActivity {
    ListView listView;
    List<ShoppingCart> shoppingCart;
    AppDatabase appDatabase;
    AdperShoping adpter;
    TextView txAmount;
    Button btnPay;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        shoppingCart = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listShop);
        btnPay = (Button) findViewById(R.id.btnPay);
        txAmount = (TextView) findViewById(R.id.txTotal);
        appDatabase = AppDatabase.getAppDatabase(CheckOutActivity.this);
        new Thread(){
            public void run(){

                shoppingCart=appDatabase.listOdDaoShopingCart().getAllCart();
                int id = appDatabase.listOdDaoShopingCart().countNumberOfRow();
                adpter = new AdperShoping(CheckOutActivity.this, 0, shoppingCart);
                listView.setAdapter(adpter);
            }
        }.start();
//        appDatabase = AppDatabase.getAppDatabase(this);
//        shoppingCart=appDatabase.listOdDaoShopingCart().getAllCart();
//        int id = appDatabase.listOdDaoShopingCart().countNumberOfRow();
//        adpter = new AdperShoping(CheckOutActivity.this, 0, shoppingCart);
//        listView.setAdapter(adpter);
    //    Toast.makeText(this, "amount in " + id, Toast.LENGTH_SHORT).show();
        int sum=appDatabase.listOdDaoShopingCart().sumOfShoppingCart();
        txAmount.setText(String.valueOf(sum));
//        ISqlManger iSqlManger = new SqlManger();
//        iSqlManger.getAllShoppingCart(this, new Consumer<List<ShoppingCart>>() {
//            @Override
//            public void accept(List<ShoppingCart> shoppingCarts) {
//                shoppingCart.addAll(shoppingCarts);
//                txAmount.setText(String.valueOf(GetAmount.getTotal( shoppingCart)));
//
//
//            }
//        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final ShoppingCart shoppingCart = adpter.getItem(position);
                final AlertDialog.Builder alert = new AlertDialog.Builder(CheckOutActivity.this);
                alert.setCancelable(false).setMessage("Do you want to delete this Item").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase appDatabase = AppDatabase.getAppDatabase(CheckOutActivity.this);
                        appDatabase.listOdDaoShopingCart().deleteByID(shoppingCart.getName());
                        adpter.remove(shoppingCart);

                        adpter.notifyDataSetChanged();


                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                return false;
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.listOdDaoShopingCart().deleteAll();
                Intent intent1=new Intent(CheckOutActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();

            }
        });


    }

    @Override
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
        }
        return true;
    }
}
