package com.ipy849.fastfoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputTextMailRecovery;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentTextOk;
import com.ipy849.fastfoo.model.User;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    TextInputLayout userEmailInput, userPasswordInput;
    Button loginButton;

    FirebaseAuth fbAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        userEmailInput = findViewById(R.id.login_email);
        userPasswordInput = findViewById(R.id.login_password);

        // Ingreso del usuario
        loginButton = findViewById(R.id.login_loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<String, String> inputCredentials = new Pair<>(
                        userEmailInput.getEditText().getText().toString(),
                        userPasswordInput.getEditText().getText().toString()
                );
                fbAuth.signInWithEmailAndPassword(inputCredentials.first, inputCredentials.second).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    sharedPreferences.edit().putString("uid", fbAuth.getCurrentUser().getUid()).commit();
                                    AppSession.user = new User();
                                    DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
                                    fbDatabaseRef.child("users").child(fbAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if(!task.isSuccessful()) return;
                                            HashMap<String, Object> data = (HashMap<String, Object>) task.getResult().getValue();
                                            AppSession.user = new User(data);
                                            finish();
                                        }
                                    });
                                } else {
                                    new DialogFragmentTextOk(getString(R.string.login_failure), getText(R.string.ok).toString()).show(getSupportFragmentManager(), DialogFragmentTextOk.TAG);
                                }
                            }
                        }
                );
            }
        });

        // Registrar nuevo usuario
        findViewById(R.id.login_loginCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        // Recuperar email
        findViewById(R.id.login_recoverAccount).setOnClickListener(view2 -> new DialogFragmentInputTextMailRecovery(
                getText(R.string.dialog_reocervEmail_text).toString()).show(getSupportFragmentManager(), DialogFragmentInputTextMailRecovery.TAG));

    }
}