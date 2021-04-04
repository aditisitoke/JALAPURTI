package com.aditi.hkp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditi.hkp.Adapter.VerifiedListAdapter;
import com.aditi.hkp.R;

import java.util.ArrayList;

/**
 * Created by hrideshsarraf on 25-03-2018.
 */

public class VerifiedKhet extends Fragment {

    View view;
    Context context;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        context = getActivity();
        view = inflater.inflate(R.layout.activity_verified_list_item, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(new VerifiedListAdapter(context, new ArrayList<String>()));

        return view;
    }

}
