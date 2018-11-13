package com.fewnu.geek.fewnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.fewnu.geek.fewnu.model.Depense;
import com.fewnu.geek.fewnu.model.ListDepenses;
import com.fewnu.geek.fewnu.model.ListVentes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class DepenseActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    ListView listViewD;
    static String idD, designationD;
    static Double prixD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("depenses");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listViewD = (ListView) findViewById(R.id.listdepense);

        final ArrayList<Depense> depenses = new ArrayList<>();
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
                    Depense depense = iterator.next().getValue(Depense.class);
                    depenses.add(depense);
                    ((ListDepenses)(((ListView)findViewById(R.id.listdepense)).getAdapter())).notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        ((ListView)findViewById(R.id.listdepense)).setAdapter(new ListDepenses(depenses,this));
        //setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.people_list_row, R.id.personNameTv,names));
        ((ListView)findViewById(R.id.listdepense)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                idD = depenses.get(position).getId();
                designationD = depenses.get(position).getDesignation();
                prixD =  depenses.get(position).getPrix();
                String str=listViewD.getItemAtPosition(position).toString();
                Intent it=new Intent(DepenseActivity.this,UpdateFinalDepenseActivity.class);
                startActivity(it);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
