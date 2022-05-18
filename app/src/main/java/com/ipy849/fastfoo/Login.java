package com.ipy849.fastfoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputText;
import com.ipy849.fastfoo.dialogHandlers.IDialogHandler;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Registrar nuevo usuario
        findViewById(R.id.login_loginCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        // Recuperar email
        findViewById(R.id.login_recoverAccount).setOnClickListener(view2 -> new DialogFragmentInputText(
                getText(R.string.dialog_reocervEmail_text).toString(),
                "Enviar",
                new IDialogHandler() {
                    @Override
                    public void handle(Context context, View view, Object caller) {
                        // TODO: Aquí va la lógica de enviar correo
                    }
                }).show(getSupportFragmentManager(), DialogFragmentInputText.TAG));

    }
}