package com.example.mycomputer.asuper.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mycomputer.asuper.R;

import java.util.List;

/**
 * Created by My computer on 2/24/2018.
 */

public class AdperShoping extends ArrayAdapter<ShoppingCart> {
    private Context context;
    private List<ShoppingCart> objects;

    public AdperShoping(@NonNull Context context, int resource, @NonNull List<ShoppingCart> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShoppingCart shop = objects.get(position);
        ViewHelper viewHelper = null;
        if (convertView == null) {
            viewHelper = new ViewHelper();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.shop_bill_layout, parent, false);
            viewHelper.name = (TextView) convertView.findViewById(R.id.txName);
            viewHelper.price = (TextView) convertView.findViewById(R.id.txAmoout);
            convertView.setTag(viewHelper);
        } else {
            viewHelper = (ViewHelper) convertView.getTag();
        }
        viewHelper.name.setText(shop.getName().toString());
        viewHelper.price.setText(String.valueOf(shop.getAmount()));
        return convertView;
    }

    public static class ViewHelper {
        TextView name;
        TextView price;

    }
}
