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

public class DialogFragmentTextOk extends DialogFragment {

    public static String TAG = "DialogFragmentTextOk";

    protected View rootView;
    protected String text;
    protected String accepButtontText;

    public DialogFragmentTextOk(String text, String accepButtontText){
        this.accepButtontText = accepButtontText;
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentdialog_okdialog, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Text
        ((TextView) rootView.findViewById(R.id.dialog_okDialog_text)).setText(text);

        // AcceptButton
        ((Button) rootView.findViewById(R.id.dialog_okDialog_acceptButton)).setText(accepButtontText);
        rootView.findViewById(R.id.dialog_okDialog_acceptButton).setOnClickListener(view2 -> dismiss());
    }

}
