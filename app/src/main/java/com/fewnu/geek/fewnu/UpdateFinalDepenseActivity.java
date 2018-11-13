package com.fewnu.geek.fewnu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateFinalDepenseActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button modifier,supprimer;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_final_depense);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("depenses");
        ed1=(EditText)findViewById(R.id.designationD);
        ed2=(EditText)findViewById(R.id.prixD);
        modifier=(Button)findViewById(R.id.modifierD);
        supprimer=(Button)findViewById(R.id.supprimerD);
        ed1.setText(DepenseActivity.designationD);
        ed2.setText(DepenseActivity.prixD.toString());
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(DepenseActivity.idD).child("designation").setValue(ed1.getText().toString());
                myRef.child(DepenseActivity.idD).child("prix").setValue(Double.parseDouble(ed2.getText().toString()));
                Intent itr= new Intent(UpdateFinalDepenseActivity.this,DepenseActivity.class);
                startActivity(itr);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(DepenseActivity.idD).removeValue();
                Intent intentdelete= new Intent(UpdateFinalDepenseActivity.this,DepenseActivity.class);
                startActivity(intentdelete);
            }
        });
    }
}
