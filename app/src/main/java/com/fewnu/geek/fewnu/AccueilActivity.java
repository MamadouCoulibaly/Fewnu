package com.fewnu.geek.fewnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fewnu.geek.fewnu.model.ListVentes;
import com.fewnu.geek.fewnu.model.Vente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class AccueilActivity extends AppCompatActivity {



    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ventes");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final ArrayList<String> designations = new ArrayList<>();
        final ArrayList<Double> prix = new ArrayList<>();
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                // Log.d(TAG, "Value is: " + value);
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while((iterator.hasNext())){
                    Vente value = iterator.next().getValue(Vente.class);
                    designations.add(value.getDesignation());
                    prix.add(value.getPrix());
                    ((ListVentes)(((ListView)findViewById(R.id.peopleList)).getAdapter())).notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        ((ListView)findViewById(R.id.peopleList)).setAdapter(new ListVentes(designations,prix,this));
        //setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.people_list_row, R.id.personNameTv,names));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tabLayout.getSelectedTabPosition()==0){
                    Intent venteActivity = new Intent(AccueilActivity.this, AjoutVenteActivity.class);
                    startActivity(venteActivity);
                }
                else if (tabLayout.getSelectedTabPosition()==1){
                    Intent venteActivity = new Intent(AccueilActivity.this, AjoutDepenseActivity.class);
                    startActivity(venteActivity);
                }
                else {
                    Intent venteActivity = new Intent(AccueilActivity.this, AjoutPretActivity.class);
                    startActivity(venteActivity);
                }


            }
        });



    }
    private void LireVentes(){

    }


}
