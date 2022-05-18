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

import com.google.android.material.textfield.TextInputLayout;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.fragments.main.MainProfileFragment;

public class DialogFragmentInputNewPassword extends DialogFragment {

    public static String TAG = "DialogFragmentInputNewPassword";

    protected View rootView;
    protected Button text;
    protected TextInputLayout newPassword, newPasswordRepeated;
    protected IDialogHandler handler;

    MainProfileFragment caller;

    public DialogFragmentInputNewPassword(MainProfileFragment caller, IDialogHandler handler){
        this.caller = caller;
        this.handler = handler;
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
            if(newPasswordValue.equals(newPasswordRepatedValue))
                handler.handle(getContext(), rootView,  this);
        });
    }

}
