package com.ipy849.fastfoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentTextOk;
import com.ipy849.fastfoo.model.User;
import com.ipy849.fastfoo.utils.Constants;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout state, email, password, repeatPassword, address, paymentMethod, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Capture components
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        repeatPassword = findViewById(R.id.register_password_repeat);
        address = findViewById(R.id.register_houseDirection);
        state = findViewById(R.id.register_state);
        paymentMethod = findViewById(R.id.register_paymentMethod);
        ArrayAdapter stateArrayAdapter = new ArrayAdapter(this, R.layout.autocomplete_textview_items, Constants.stateList);
        ((AutoCompleteTextView) state.getEditText()).setAdapter(stateArrayAdapter);


        // Back button
        findViewById(R.id.register_backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Botón de registrar
        findViewById(R.id.register_registerButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() != R.id.register_registerButton) return;
        boolean error = false;

        String nameContent = name.getEditText().getText().toString();
        if (TextUtils.isEmpty(nameContent)) {
            name.setError("Debe escribir tu nombre en este campo");
            name.setErrorEnabled(true);
            error = true;
        } else name.setErrorEnabled(false);

        String emailContent = email.getEditText().getText().toString();
        if (TextUtils.isEmpty(emailContent) || !Patterns.EMAIL_ADDRESS.matcher(emailContent).matches()) {
            email.setError("Debe escribir tu email en este campo");
            email.setErrorEnabled(true);
            error = true;
        } else email.setErrorEnabled(false);

        String passwordContent = password.getEditText().getText().toString();
        if (TextUtils.isEmpty(passwordContent) || passwordContent.length() < 8) {
            password.setError("Debe escribir tu contraseña en este campo, esta debe tener al menos 8 caracteres");
            password.setErrorEnabled(true);
            error = true;
        } else password.setErrorEnabled(false);

        String passwordRepeeatedContent = repeatPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty(passwordRepeeatedContent) || !passwordContent.equals(passwordRepeeatedContent)) {
            repeatPassword.setError("Debe escribir tu contraseña en este campo y que coincida con el campo anterior a este");
            repeatPassword.setErrorEnabled(true);
            error = true;
        } else repeatPassword.setErrorEnabled(false);

        String stateContent = state.getEditText().getText().toString();
        if (TextUtils.isEmpty(stateContent)) {
            state.setError("Debe seleccionar un estado");
            state.setErrorEnabled(true);
            error = true;
        } else state.setErrorEnabled(false);

        String addressContent = address.getEditText().getText().toString();
        if (TextUtils.isEmpty(addressContent)) {
            address.setError("Debe escribir su dirección en este campo");
            address.setErrorEnabled(true);
            error = true;
        } else address.setErrorEnabled(false);

        String paymentMethodContent = paymentMethod.getEditText().getText().toString();
        if (TextUtils.isEmpty(paymentMethodContent) || paymentMethodContent.length() != 16) {
            paymentMethod.setError("Debe escribir su método en este campo con un logitud de 16 caracteres (sin espacios en blanco)");
            paymentMethod.setErrorEnabled(true);
            error = true;
        } else paymentMethod.setErrorEnabled(false);

        if(error) return;

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        fbAuth.createUserWithEmailAndPassword(emailContent, passwordRepeeatedContent)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (! task.isSuccessful()) {
                            new DialogFragmentTextOk("Lo sentimos, ocurrió un error en la creación de esta cuenta", getText(R.string.ok).toString()).show(getSupportFragmentManager(), DialogFragmentTextOk.TAG);
                        } else {
                            User user = new User();
                            user.setEmail(emailContent);
                            user.setName(nameContent);
                            user.setAddress(addressContent);
                            user.setState(stateContent);
                            user.setPayMethod(paymentMethodContent);
                            String uid = task.getResult().getUser().getUid();
                            fbAuth.signOut();
                            DatabaseReference fbrDatabaseRef = FirebaseDatabase.getInstance().getReference();
                            fbrDatabaseRef.child("users").child(uid).setValue(user);

                            finish();
                        }
                    }
                });

    }
}