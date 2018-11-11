package com.fewnu.geek.fewnu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    static String phone;
    EditText mphoneInput;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mphoneInput = (EditText) findViewById(R.id.phone);
        mSaveButton = (Button) findViewById(R.id.suivant);


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (verification()){
                    phone= mphoneInput.getText().toString();
                    Intent accueilActivity = new Intent(MainActivity.this, FisrtActivity.class);

                    startActivity(accueilActivity);
                }


            }
        });
    }

    private boolean verification() {
        String phone = mphoneInput.getText().toString();
        boolean retour=true;
        if (phone.length() != 9) {
            mphoneInput.setError("Ajouter un bon numéro Svp !!");
            mphoneInput.requestFocus();
            retour = false;
        }
        return  retour;
    }




}
