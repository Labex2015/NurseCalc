package com.nursecalc.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nursecalc.R;
import com.nursecalc.Util.actions.DialogActions;

import static com.nursecalc.R.color.blue;

/**
 * Created by 0126128 on 20/02/2015.
 */
public class DialogMaker {

    private Activity activity;
    private DialogActions dialogActions;
    private EditText editText;
    private Dialog dialog;

    public DialogMaker(Activity activity, DialogActions dialogActions) {
        this.activity = activity;
        this.dialogActions = dialogActions;
    }

    public Dialog createDialogWithEditText(String title, String labelEditTextValue, String message,String hint, String buttonConfirmName, String buttonCancelName){
        dialog = new Dialog(activity);
        LinearLayout v = new LinearLayout(activity);
        v.setOrientation(LinearLayout.VERTICAL);
        v.setBackgroundColor(Color.parseColor("#ffffff"));
        v.setPadding(10, 10, 10, 10);
        TextView resultado = (TextView) new TextView(activity);
        TextView labelEditText = (TextView) new TextView(activity);

        labelEditText.setTextColor(Color.parseColor("#333333"));
        labelEditText.setTextSize(20);
        labelEditText.setTypeface(null, Typeface.BOLD);
        resultado.setTextSize(15);

        editText = new EditText(activity);
        editText.setHint(hint);
        editText.setTextColor(Color.parseColor("#000000"));

        resultado.setText(message);

        v.addView(labelEditText);
        v.addView(resultado);
        v.addView(editText);

        Button confirm = new Button(activity);
        confirm.setText(buttonConfirmName);
        confirm.setBackgroundResource(R.drawable.sl_button);
        confirm.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 0, 5);
        confirm.setLayoutParams(params);
        confirm.setOnClickListener(confirmActionListener());
        labelEditText.setText(labelEditTextValue);

        Button cancel = new Button(activity);

        cancel.setText(buttonCancelName);
        cancel.setBackgroundResource(R.drawable.sl_button);
        cancel.setTextColor(Color.WHITE);
        cancel.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cancel.setOnClickListener(cancelActionListener());


        v.addView(confirm);
        v.addView(cancel);

        dialog.setContentView(v);
        dialog.setTitle(title);
        return dialog;
    }


    private View.OnClickListener confirmActionListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogActions.onSave();
            }
        };
    }

    private View.OnClickListener cancelActionListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogActions.onCancel();
            }
        };
    }

    public void closeDialog(){
        if(dialog.isShowing())
            dialog.dismiss();
    }

    public EditText getEditText() {
        return editText;
    }

    public String getEditTextValue() {
        return editText.getText().toString();
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
