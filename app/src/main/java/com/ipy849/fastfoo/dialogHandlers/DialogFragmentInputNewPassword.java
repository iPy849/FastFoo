package com.ipy849.fastfoo.dialogHandlers;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.fragments.main.MainProfileFragment;

public class DialogFragmentInputNewPassword extends DialogFragment {

    public static String TAG = "DialogFragmentInputNewPassword";

    protected View rootView;
    protected Button text;
    protected TextInputLayout newPassword, newPasswordRepeated;

    MainProfileFragment caller;

    public DialogFragmentInputNewPassword(MainProfileFragment caller){
        this.caller = caller;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentdialog_input_new_password, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Close button
        rootView.findViewById(R.id.dialog_inputText_cancelButton).setOnClickListener(view1 -> dismiss());

        // Inputs
        newPassword = rootView.findViewById(R.id.dialog_inputText_input_new_password);
        newPasswordRepeated = rootView.findViewById(R.id.dialog_inputText_input_new_password_repeat);

        // AcceptButton handler
        rootView.findViewById(R.id.dialog_inputText_acceptButton).setOnClickListener(view2 -> {
            String newPasswordValue = newPassword.getEditText().getText().toString();
            String newPasswordRepatedValue = newPasswordRepeated.getEditText().getText().toString();

            if(newPasswordValue.length() < 8 || !newPasswordValue.equals(newPasswordRepatedValue)) {
                new DialogFragmentTextOk("Las contraseñas no coinciden o no cumple con el tamaño mínimos de 8 carateres, vuelva a intentarlo.", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                return;
            }

            FirebaseAuth fbAuth = FirebaseAuth.getInstance();
            FirebaseUser user = fbAuth.getCurrentUser();
            user.updatePassword(newPasswordValue).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    new DialogFragmentTextOk("Se ha actualizado su contraseña correctamente", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                    dismiss();
                }
            });
        });
    }

}
