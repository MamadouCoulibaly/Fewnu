package com.fewnu.geek.fewnu.model;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fewnu.geek.fewnu.R;

import java.util.ArrayList;

public class ListDepenses extends BaseAdapter {

    private ArrayList<Depense> depenses;
    private AppCompatActivity activity;

    public ListDepenses() {
    }

    public ListDepenses(ArrayList<Depense> depenses, AppCompatActivity activity) {
        this.depenses = depenses;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return depenses.size();
    }

    @Override
    public Object getItem(int position) {
        return depenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.listedepenses,viewGroup,false);
        ((TextView)view.findViewById(R.id.listdesignation)).setText(String.valueOf(depenses.get(position).getDesignation()));
        ((TextView)view.findViewById(R.id.listprix)).setText(depenses.get(position).getPrix().toString());
        return view;
    }
}
