package com.ipy849.fastfoo.dialogHandlers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.MainActivity;
import com.ipy849.fastfoo.R;

public class DialogFragmentConfirmationText extends DialogFragment {

    public static String TAG = "DialogFragmentInputText";

    protected View rootView;
    protected String text;
    protected String acceptButtontText;
    MainActivity caller;

    public DialogFragmentConfirmationText(String text, String acceptButtontText, MainActivity caller){
        this.acceptButtontText = acceptButtontText;
        this.text = text;
        this.caller = caller;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragmentdialog_confirmation, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Close button
        rootView.findViewById(R.id.dialog_confirmation_cancelButton).setOnClickListener(view1 -> dismiss());

        // Text
        ((TextView) rootView.findViewById(R.id.dialog_confirmation_text)).setText(text);

        // AcceptButton
        ((Button) rootView.findViewById(R.id.dialog_confirmation_acceptButton)).setText(acceptButtontText);
        rootView.findViewById(R.id.dialog_confirmation_acceptButton).setOnClickListener(view2 -> handle());
    }

    protected void handle(){
        AppSession.user.setCart(null);
        AppSession.user.save(getContext());
        new DialogFragmentTextOk("Se ha completado el pago!", getText(R.string.ok).toString()).show(getParentFragmentManager(), DialogFragmentTextOk.TAG);
        Intent intent = caller.getIntent();
        caller.finish();
        startActivity(intent);
    }

}
