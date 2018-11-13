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

    private ArrayList<Vente> ventes;
    private AppCompatActivity activity;

    public ListVentes() {
    }

    public ListVentes(ArrayList<Vente> ventes, AppCompatActivity activity) {
        this.ventes = ventes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return ventes.size();
    }

    @Override
    public Object getItem(int position) {
        return ventes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.listeventes,viewGroup,false);
        ((TextView)view.findViewById(R.id.listdesignation)).setText(String.valueOf(ventes.get(position).getDesignation()));
        ((TextView)view.findViewById(R.id.listprix)).setText(ventes.get(position).getPrix().toString());
        return view;
    }
}
