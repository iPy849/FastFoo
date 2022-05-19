package com.ipy849.fastfoo.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.MainActivity;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputNewPassword;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputSelection;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputTextChangeAddress;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentInputTextChangePaymentMethod;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

public class MainProfileFragment extends Fragment implements View.OnClickListener {

    View rootView;
    Button changePaymentMethod, changeAddress, changePassword, logout, changeState;
    TextView name, email;
    MainActivity caller;

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

        // Datos
        email = rootView.findViewById(R.id.fragment_main_profile_email);
        email.setText(AppSession.user.getEmail());
        name = rootView.findViewById(R.id.fragment_main_profile_rol);
        name.setText(AppSession.user.getName());

        // Botones
        changePaymentMethod = rootView.findViewById(R.id.fragment_main_profile_change_payment_method_button);
        changeState = rootView.findViewById(R.id.fragment_main_profile_change_state_button);
        changeAddress = rootView.findViewById(R.id.fragment_main_profile_change_address_button);
        changePassword = rootView.findViewById(R.id.fragment_main_profile_change_password_button);
        logout = rootView.findViewById(R.id.fragment_main_profile_logout_button);
        changePaymentMethod.setOnClickListener(this);
        changeAddress.setOnClickListener(this);
        changeState.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_main_profile_change_payment_method_button: {
                new DialogFragmentInputTextChangePaymentMethod().show(getChildFragmentManager(), DialogFragmentInputTextChangePaymentMethod.TAG);
                break;
            }
            case R.id.fragment_main_profile_change_address_button: {
                new DialogFragmentInputTextChangeAddress().show(getChildFragmentManager(), DialogFragmentInputTextChangeAddress.TAG);
                break;
            }
            case R.id.fragment_main_profile_change_state_button: {
                new DialogFragmentInputSelection("Seleccione un estado de la lista para actualizar su información", "Cambiar").show(getChildFragmentManager(), DialogFragmentInputSelection.TAG);
                break;
            }
            case R.id.fragment_main_profile_change_password_button: {
                new DialogFragmentInputNewPassword(this).show(getChildFragmentManager(), DialogFragmentInputNewPassword.TAG);
                break;
            }
            case R.id.fragment_main_profile_logout_button:
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences = caller.getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), caller.MODE_PRIVATE);
                sharedPreferences.edit().putString("uid", null).commit();
                AppSession.user = null;
                Intent intent = caller.getIntent();
                caller.finish();
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context != null) {
            caller = (MainActivity) context;
        }
    }
}
