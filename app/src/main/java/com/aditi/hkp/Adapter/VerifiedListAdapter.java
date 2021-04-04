package com.aditi.hkp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditi.hkp.R;

import java.util.List;

/**
 * Created by hrideshsarraf on 25-03-2018.
 */

    public class VerifiedListAdapter extends RecyclerView.Adapter<VerifiedListAdapter.VerifiedListViewHolder> {

    Context context;
    List<String> list;



    public VerifiedListAdapter(Context context, List<String> list){

        this.context = context;
        this.list = list;
    }

    @Override
    public VerifiedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_verified_list_item,parent,false);
        return new VerifiedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerifiedListViewHolder holder, int position) {
        holder.count.setText(String.valueOf(position+1));


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class VerifiedListViewHolder extends RecyclerView.ViewHolder {

        TextView count, khasra, rnn, tehsil, village;

        public VerifiedListViewHolder(View itemView) {
            super(itemView);

            count = itemView.findViewById(R.id.count);
            khasra = itemView.findViewById(R.id.khasra_no);
            rnn = itemView.findViewById(R.id.rajasv);
            tehsil = itemView.findViewById(R.id.tehsil);
            village = itemView.findViewById(R.id.village);

        }
    }
}