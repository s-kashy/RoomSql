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
 * Created by My computer on 2/28/2018.
 */

public class AdperEditAdmin extends ArrayAdapter<Product> {
    private List<Product> objects;
    private Context context;

    public AdperEditAdmin(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Product p = getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.edit_admin_layout, parent, false);
            viewHolder.txNameProduct = (TextView) convertView.findViewById(R.id.txProName);
            viewHolder.txNameCat = (TextView) convertView.findViewById(R.id.txProCat);
            viewHolder.txPrice = (TextView) convertView.findViewById(R.id.txPrice);
            viewHolder.txUnit = (TextView) convertView.findViewById(R.id.txUnit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (p != null) {
            viewHolder.txNameProduct.setText(p.getNameOfProduct().toString());
            viewHolder.txNameCat.setText(p.getName().toString());
            viewHolder.txPrice.setText(String.valueOf(p.getPrice()));
            viewHolder.txUnit.setText(p.getUnit().toString());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txNameCat;
        TextView txNameProduct;
        TextView txPrice;
        TextView txUnit;
    }
}
