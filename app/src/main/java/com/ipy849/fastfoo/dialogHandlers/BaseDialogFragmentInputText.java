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

import com.ipy849.fastfoo.R;

public abstract class BaseDialogFragmentInputText extends DialogFragment {

    public static String TAG = "DialogFragmentInputText";

    protected View rootView;
    protected String text;
    protected String acceptButtontText;

    public BaseDialogFragmentInputText(String text, String acceptButtontText){
        this.acceptButtontText = acceptButtontText;
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentdialog_inputtext, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Close button
        rootView.findViewById(R.id.dialog_inputText_cancelButton).setOnClickListener(view1 -> dismiss());

        // Text
        ((TextView) rootView.findViewById(R.id.dialog_inputText_text)).setText(text);

        // AcceptButton
        ((Button) rootView.findViewById(R.id.dialog_inputText_acceptButton)).setText(acceptButtontText);
        rootView.findViewById(R.id.dialog_inputText_acceptButton).setOnClickListener(view2 -> handle());
    }

    protected abstract void handle();

}
