package com.aditi.hkp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditi.hkp.Fragment.KhetDetail;
import com.aditi.hkp.Fragment.Questions;
import com.aditi.hkp.ModelClasses.GetKhasraNumber;
import com.aditi.hkp.R;

import java.util.List;

/**
 * Created by hrideshsarraf on 25-03-2018.
 */

public class KhasraListAdapter extends RecyclerView.Adapter<KhasraListAdapter.KhasraListViewHolder> {

    Context context;
    List<GetKhasraNumber> list;
    String halka_no;

    public KhasraListAdapter(Context context, List<GetKhasraNumber> list,String halka_no){
        this.context = context;
        this.list = list;
        this.halka_no = halka_no;
    }

    @Override
    public KhasraListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.field_list_item,parent,false);
        return new KhasraListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KhasraListViewHolder holder, final int position) {
        holder.count.setText(String.valueOf(position+1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("halka_no",halka_no);
                bundle.putString("khasra_no",list.get(position).getText());

                Questions questions = new Questions();
                questions.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,questions).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class KhasraListViewHolder extends RecyclerView.ViewHolder{

        TextView count, khasra, rnn, tehsil, village;

        public KhasraListViewHolder(View itemView){
            super(itemView);

            count = itemView.findViewById(R.id.count);
            khasra = itemView.findViewById(R.id.khasra_no);
            rnn = itemView.findViewById(R.id.rajasv);
            tehsil = itemView.findViewById(R.id.tehsil);
            village = itemView.findViewById(R.id.village);


        }
    }



}
