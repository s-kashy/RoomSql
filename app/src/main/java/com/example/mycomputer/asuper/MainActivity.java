package com.example.mycomputer.asuper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mycomputer.asuper.Activitiy.AddPruductActivity;
import com.example.mycomputer.asuper.Activitiy.CategoryActivity;
import com.example.mycomputer.asuper.Activitiy.CheckOutActivity;
import com.example.mycomputer.asuper.Activitiy.ProductListActivity;
import com.example.mycomputer.asuper.Classes.AppDatabase;
import com.example.mycomputer.asuper.Classes.ISqlManger;
import com.example.mycomputer.asuper.Classes.SqlManger;

import java.util.function.Consumer;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    EditText edName, edPass;
    Button btnSubmit;
    SharedPreferences sharedPreferences;
    public static final String uName = "unmae";
    public static final String uPass = "upass";
    RadioButton radioUser, radioAdmin;


    public static final String aName = "unmae";
    public static final String aPass = "upass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("login", 0);
        edName = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edUserPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        radioUser = (RadioButton) findViewById(R.id.rUser);
        radioAdmin = (RadioButton) findViewById(R.id.rAdmin);
        ISqlManger sqlManger=new SqlManger();
   //     sqlManger.deleteAll(this,getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1=new Intent(MainActivity.this,CategoryActivity.class);
//                    startActivity(intent1);
//                    finish();
                checkuser("sh", "12");
                }



        });

    }

    private void checkuser(String name, String pass) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (radioUser.isChecked()) {
            String nameS = sharedPreferences.getString(uName, null);
            String passS = sharedPreferences.getString(uPass, null);
            if (nameS != null) {
                if (nameS.equals(name) && passS.equals(pass)) {
                    Intent intent1 = new Intent(this, CategoryActivity.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(this, "user name our pass are not right", Toast.LENGTH_SHORT).show();
                }

            } else {
                editor.putString(uName, name);
                editor.putString(uPass, pass);
                editor.commit();
                Intent intent1 = new Intent(this, CategoryActivity.class);
                startActivity(intent1);
                finish();


            }

        } else if (radioAdmin.isChecked()) {
            String nameS = sharedPreferences.getString(aName, null);
            String passS = sharedPreferences.getString(aPass, null);
            if (nameS != null) {
                if (nameS.equals(name) && passS.equals(pass)) {
                    Intent intent1 = new Intent(this, AddPruductActivity.class);
                    startActivity(intent1);
                    finish();
                } else {

                    Toast.makeText(this, "user name our pass are not right", Toast.LENGTH_SHORT).show();
                }

            } else {
                editor.putString(aName, name);
                editor.putString(aPass, pass);
                editor.commit();
               Intent intent1 = new Intent(this, AddPruductActivity.class);
                startActivity(intent1);
                finish();

            }

        } else {
            Toast.makeText(this, "must choose radio buttom", Toast.LENGTH_SHORT).show();
        }
    }


}