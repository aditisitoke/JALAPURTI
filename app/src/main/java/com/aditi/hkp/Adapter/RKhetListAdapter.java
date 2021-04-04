package com.aditi.hkp.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditi.hkp.Fragment.KhetDetail;
import com.aditi.hkp.R;

import java.util.List;

/**
 * Created by LENOVO on 25-03-2018.
 */

public class RKhetListAdapter extends RecyclerView.Adapter<RKhetListAdapter.RKhetListViewHolder> {

    Context context;
    List<String> list;

    public RKhetListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RKhetListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_registered_khet_field,parent,false);
        return new RKhetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RKhetListViewHolder holder, int position) {
        holder.count.setText(String.valueOf(position+1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new KhetDetail()).addToBackStack("khet_detail").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class RKhetListViewHolder extends RecyclerView.ViewHolder{

        TextView count, khasra_no;

        public RKhetListViewHolder(View itemView) {
            super(itemView);

            count = itemView.findViewById(R.id.count);
            khasra_no = itemView.findViewById(R.id.khasra_no);

        }
    }

}
