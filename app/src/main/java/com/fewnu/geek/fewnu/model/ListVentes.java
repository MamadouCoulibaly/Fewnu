package com.fewnu.geek.fewnu.model;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.fewnu.geek.fewnu.R;

import java.util.ArrayList;

public class ListVentes extends BaseAdapter {

    private ArrayList<String> designations;
    private ArrayList<Double> prix;
    private AppCompatActivity activity;

    public ListVentes() {
    }

    public ListVentes(ArrayList<String> designations, ArrayList<Double> prix, AppCompatActivity activity) {
        this.designations = designations;
        this.prix = prix;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return designations.size();
    }

    @Override
    public Object getItem(int position) {
        return designations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.listeventes,viewGroup,false);
        ((TextView)view.findViewById(R.id.listdesignation)).setText(String.valueOf(designations.get(position)));
        ((TextView)view.findViewById(R.id.listprix)).setText(prix.get(position).toString());
        return view;
    }
}
