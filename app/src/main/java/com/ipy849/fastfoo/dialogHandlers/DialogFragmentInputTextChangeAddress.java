package com.ipy849.fastfoo.dialogHandlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

public class DialogFragmentInputTextChangeAddress extends BaseDialogFragmentInputText {

    public static String TAG = "DialogFragmentInputTextChangeAddress";


    public DialogFragmentInputTextChangeAddress() {
        super("LLene este formulario para proceder a cambiar la dirección de entrega de su cuenta de FastFoo", "Cambiar");
    }

    @Override
    protected void handle() {
        TextInputLayout input = rootView.findViewById(R.id.dialog_inputText_input);

        String content = input.getEditText().getText().toString();

        DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), Context.MODE_PRIVATE);
        fbDatabaseRef.child("users").child(sharedPreferences.getString("uid", null)).child("address").setValue(content).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    new DialogFragmentTextOk("Se ha cambiado el dirección de entrega exitosamente!", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                else
                    new DialogFragmentTextOk("Lo sentimos, tenemos un error al actualizar su información", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                dismiss();
            }
        });
    }
}
