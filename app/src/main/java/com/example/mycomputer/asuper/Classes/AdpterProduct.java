package com.example.mycomputer.asuper.Classes;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycomputer.asuper.R;

import java.util.List;

/**
 * Created by My computer on 2/22/2018.
 */

public class AdpterProduct extends RecyclerView.Adapter<AdpterProduct.ViewHolder> implements View.OnClickListener {
    List<Product> objects;
    Context context;

    public AdpterProduct(List<Product> objects, Context context) {
        this.context = context;
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())//
                .inflate(R.layout.produce_list_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!objects.isEmpty()) {
            Product p = objects.get(position);

            holder.txName.setText(p.getNameOfProduct().toString());
            holder.txPrice.setText(String.valueOf(p.getPrice()));
            holder.txUnit.setText(p.getUnit().toString());
            holder.line.setTag(position);
            holder.line.setOnClickListener(this);
        }


    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onClick(View v) {
        LinearLayout linearLayout = (LinearLayout) v;
        int pos = (int) linearLayout.getTag();

        final Product p = objects.get(pos);
        final Dialog dialog = new Dialog(context);


        dialog.setCancelable(false);
        //   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setContentView(R.layout.order_product);

        final TextView name = (TextView) dialog.findViewById(R.id.txName);

        final TextView price = (TextView) dialog.findViewById(R.id.txPrice);
        price.setText(String.valueOf(p.getPrice()));
        TextView unit = (TextView) dialog.findViewById(R.id.txUnit);
        unit.setText(p.getUnit().toString());
        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        final EditText txNum = (EditText) dialog.findViewById(R.id.numPic);
        // final   NumberPicker num = (NumberPicker) dialog.findViewById(R.id.numPic);
//
//        num.setMinValue(1);
//        num.setMaxValue(100);
        name.setText(p.getNameOfProduct().toString());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setAmount(Integer.parseInt(txNum.getText().toString()) * Integer.valueOf(price.getText().toString()));
                shoppingCart.setName(name.getText().toString());
//                num.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                    @Override
//                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                        shoppingCart.setAmount(newVal*p.getPrice());
//                        Log.d("picker","newval"+newVal);
//
//                        Log.d("picker","get"+picker.getValue());
//                    }
//                });

                AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
                appDatabase.listOdDaoShopingCart().addItem(shoppingCart);
                dialog.dismiss();
            }

        });


        dialog.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txName;
        public TextView txPrice;
        public TextView txUnit;
        public LinearLayout line;

        public ViewHolder(View itemView) {
            super(itemView);
            txName = (TextView) itemView.findViewById(R.id.txName);
            txPrice = (TextView) itemView.findViewById(R.id.txPrice);
            txUnit = (TextView) itemView.findViewById(R.id.txUnit);
            line = (LinearLayout) itemView.findViewById(R.id.linePro);


        }
    }

}
