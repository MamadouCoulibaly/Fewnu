package com.fewnu.geek.fewnu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button modifier,supprimer;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("ventes");
        ed1=(EditText)findViewById(R.id.updatedesignation);
        ed2=(EditText)findViewById(R.id.updateprix);
        modifier=(Button)findViewById(R.id.modifier);
        supprimer=(Button)findViewById(R.id.supprimer);
        ed1.setText(AccueilActivity.designation);
        ed2.setText(AccueilActivity.prixu.toString());
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(AccueilActivity.idV).child("designation").setValue(ed1.getText().toString());
                myRef.child(AccueilActivity.idV).child("prix").setValue(Double.parseDouble(ed2.getText().toString()));
                Intent itr= new Intent(UpdateActivity.this,AccueilActivity.class);
                startActivity(itr);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(AccueilActivity.idV).removeValue();
                Intent intentdelete= new Intent(UpdateActivity.this,AccueilActivity.class);
                startActivity(intentdelete);
            }
        });



    }
}
