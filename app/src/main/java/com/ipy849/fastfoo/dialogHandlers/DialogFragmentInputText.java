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
import com.ipy849.fastfoo.dialogHandlers.IDialogHandler;

public class DialogFragmentInputText extends DialogFragment {

    public static String TAG = "DialogFragmentInputText";

    protected View rootView;
    protected String text;
    protected String accepButtontText;
    protected IDialogHandler handler;

    public DialogFragmentInputText(String text, String accepButtontText, IDialogHandler handler){
        this.accepButtontText = accepButtontText;
        this.text = text;
        this.handler = handler;
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
        ((Button) rootView.findViewById(R.id.dialog_inputText_acceptButton)).setText(accepButtontText);
        rootView.findViewById(R.id.dialog_inputText_acceptButton).setOnClickListener(view2 -> handler.handle(getContext(), rootView, this));
    }

}
