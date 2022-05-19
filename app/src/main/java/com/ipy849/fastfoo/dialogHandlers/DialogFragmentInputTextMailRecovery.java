package com.ipy849.fastfoo.dialogHandlers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.ipy849.fastfoo.R;

public class DialogFragmentInputTextMailRecovery extends BaseDialogFragmentInputText {

    public static String TAG = "DialogFragmentInputTextMailRecovery";


    public DialogFragmentInputTextMailRecovery(String text) {
        super(text, "Enviar");
    }

    @Override
    protected void handle() {
        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        TextInputLayout input = (TextInputLayout) rootView.findViewById(R.id.dialog_inputText_input);
        fbAuth.sendPasswordResetEmail(input.getEditText().getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            new DialogFragmentTextOk(getString(R.string.mail_recovery_sucess), getString(R.string.ok)).show(getParentFragmentManager(), DialogFragmentInputTextMailRecovery.TAG);
                        else {
                            new DialogFragmentTextOk(getString(R.string.mail_recovery_error), getString(R.string.ok)).show(getParentFragmentManager(), DialogFragmentInputTextMailRecovery.TAG);
                        }
                        dismiss();
                    }
                });
    }
}
