package com.ipy849.fastfoo.dialogHandlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.utils.Constants;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

public class DialogFragmentInputSelection extends DialogFragment {

    public static String TAG = "DialogFragmentInputSelection";

    protected View rootView;
    protected String text;
    protected String acceptButtontText;

    public DialogFragmentInputSelection(String text, String acceptButtontText){
        this.acceptButtontText = acceptButtontText;
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentdialog_inputselection, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Close button
        rootView.findViewById(R.id.dialog_inputSelection_cancelButton).setOnClickListener(view1 -> dismiss());

        // Text
        ((TextView) rootView.findViewById(R.id.dialog_inputSelection_text)).setText(text);

        // AcceptButton
        ((Button) rootView.findViewById(R.id.dialog_inputSelection_acceptButton)).setText(acceptButtontText);
        rootView.findViewById(R.id.dialog_inputSelection_acceptButton).setOnClickListener(view2 -> handle());

        TextInputLayout input = rootView.findViewById(R.id.dialog_inputSelection_input);
        ArrayAdapter stateArrayAdapter = new ArrayAdapter(getContext(), R.layout.autocomplete_textview_items, Constants.stateList);
        ((AutoCompleteTextView) input.getEditText()).setAdapter(stateArrayAdapter);
    }

    protected void handle(){
        TextInputLayout input = rootView.findViewById(R.id.dialog_inputSelection_input);
        String content = input.getEditText().getText().toString();

        DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), Context.MODE_PRIVATE);
        fbDatabaseRef.child("users").child(sharedPreferences.getString("uid", null)).child("state").setValue(content).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    new DialogFragmentTextOk("Se ha cambiado el estado exitosamente!", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                else
                    new DialogFragmentTextOk("Lo sentimos, tenemos un error al actualizar su información", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
                dismiss();
            }
        });
    }

}
