package com.fewnu.geek.fewnu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fewnu.geek.fewnu.model.Vente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AjoutVenteActivity extends AppCompatActivity {

    EditText designation;
    private FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vente);
        designation = (EditText) findViewById(R.id.designation);
        final EditText prix = (EditText) findViewById(R.id.prix);
        Button ajouter = (Button) findViewById(R.id.ajouter);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ventes");
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = myRef.push().getKey();
                Vente vente = new Vente(designation.getText().toString(), Double.parseDouble(prix.getText().toString()));
                myRef.child(id).setValue(vente);
                Intent listvente = new Intent(AjoutVenteActivity.this, AccueilActivity.class );
            }
        });




    }
}
