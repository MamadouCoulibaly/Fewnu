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

public class FisrtActivity extends AppCompatActivity {
     EditText mNomInput;
     EditText mCodeInput;
     Button mValiderButton;
     String codeSent;
     String numero;
     StringBuilder stringBuilder;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisrt);
        stringBuilder = new StringBuilder("+221");
        numero = stringBuilder.append(MainActivity.phone).toString();

        mNomInput = (EditText) findViewById(R.id.nomcomplet) ;
        mCodeInput = (EditText) findViewById(R.id.code);
        mValiderButton = (Button) findViewById(R.id.valider);
        mValiderButton.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();
        mNomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mValiderButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        verif();


        mValiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifiercode();

            }
        });
    }
    void verif(){
      PhoneAuthProvider.getInstance().verifyPhoneNumber(
              numero,        // Phone number to verify
              60,                 // Timeout duration
              TimeUnit.SECONDS,   // Unit of timeout
              this,               // Activity (for callback binding)
              mCallbacks);        // OnVerificationStateChangedCallbacks
  }
    void verifiercode(){
        String codeverification = mCodeInput.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, codeverification);
        signInWithPhoneAuthCredential(credential);
    }
    void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Connexion Réussie", Toast.LENGTH_LONG).show();
                                    Intent accueilActivity = new Intent(FisrtActivity.this, AccueilActivity.class);
                                    startActivity(accueilActivity);
                                } else {
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                                        Toast.makeText(getApplicationContext(), "Code Incorrect", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };
}
