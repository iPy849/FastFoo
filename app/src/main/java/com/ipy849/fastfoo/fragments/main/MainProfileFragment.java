package com.ipy849.fastfoo.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputNewPassword;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputText;
import com.ipy849.fastfoo.dialogHandlers.IDialogHandler;

public class MainProfileFragment extends Fragment implements View.OnClickListener {

    View rootView;
    Button changePaymentMethod, changeAddress, changePassword, logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePaymentMethod = rootView.findViewById(R.id.fragment_main_profile_change_payment_method_button);
        changeAddress = rootView.findViewById(R.id.fragment_main_profile_change_address_button);
        changePassword = rootView.findViewById(R.id.fragment_main_profile_change_password_button);
        logout = rootView.findViewById(R.id.login_loginCreateAccount);
        changePaymentMethod.setOnClickListener(this);
        changeAddress.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_main_profile_change_payment_method_button: {
                break;
            }
            case R.id.fragment_main_profile_change_address_button: {
                break;
            }
            case R.id.fragment_main_profile_change_password_button: {
                new DialogFragmentInputNewPassword(
                        this, (context, view1, caller) -> ((DialogFragmentInputNewPassword) caller).dismiss()
                ).show(getChildFragmentManager(), DialogFragmentInputText.TAG);
                break;
            }
            case R.id.login_loginCreateAccount:
                break;
            default:
                break;
        }
    }
}
