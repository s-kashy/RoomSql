package com.example.mycomputer.asuper.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycomputer.asuper.Activitiy.ProductListActivity;
import com.example.mycomputer.asuper.Classes.Category;
import com.example.mycomputer.asuper.R;


import java.util.List;

/**
 * Created by My computer on 2/21/2018.
 */

public class AdpterCategory extends RecyclerView.Adapter<AdpterCategory.ViewHolder> {
    private List<Category> objects;
    private Context context;

    public AdpterCategory(List<Category> objects, Context context) {
        this.objects = objects;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())//
               .inflate(R.layout.category_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Category p = objects.get(position);
        holder.txPro.setText(p.getNameOfCategory().toString());
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, ProductListActivity.class);
                intent1.putExtra("key", objects.get(position));
                context.startActivity(intent1);
//                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txPro;
        public LinearLayout line;

        public ViewHolder(View itemView) {
            super(itemView);
            txPro = (TextView) itemView.findViewById(R.id.txPro);
            line = (LinearLayout) itemView.findViewById(R.id.line);

        }
    }


}
