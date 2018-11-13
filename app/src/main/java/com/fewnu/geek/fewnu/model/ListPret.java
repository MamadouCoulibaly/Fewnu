package com.fewnu.geek.fewnu.model;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fewnu.geek.fewnu.R;

import java.util.ArrayList;

public class ListPret extends BaseAdapter {

    private ArrayList<String> noms;
    private ArrayList<Double> montant;
    private AppCompatActivity activity;

    public ListPret() {
    }

    public ListPret(ArrayList<String> noms, ArrayList<Double> montant, AppCompatActivity activity) {
        this.noms = noms;
        this.montant = montant;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return noms.size();
    }

    @Override
    public Object getItem(int position) {
        return noms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.listepret,viewGroup,false);
        ((TextView)view.findViewById(R.id.listdesignation)).setText(String.valueOf(noms.get(position)));
        ((TextView)view.findViewById(R.id.listprix)).setText(montant.get(position).toString());
        return view;
    }
}
