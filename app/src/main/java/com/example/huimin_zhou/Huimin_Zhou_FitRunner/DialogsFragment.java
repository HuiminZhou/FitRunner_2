package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Lucidity on 17/1/18.
 */

public class DialogsFragment extends DialogFragment {
    public static final int DIALOG_DATE_PICKER = 0;
    public static final int DIALOG_TIME_PICKER = 1;
    public static final int DIALOG_DURATION = 2;
    public static final int DIALOG_DISTANCE = 3;
    public static final int DIALOG_CALORIES = 4;
    public static final int DIALOG_HEART_RATE = 5;
    public static final int DIALOG_COMMENT = 6;
    public static final int DIALOG_ID_PHOTO_PICKER = 7;
    private static final String DIALOG_ID_KEY = "dialog_key";

    public static DialogsFragment newInstance(int id) {
        DialogsFragment dialogsFragment = new DialogsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_ID_KEY, id);
        dialogsFragment.setArguments(bundle);
        return dialogsFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        int id = getArguments().getInt(DIALOG_ID_KEY);
        final Activity container = getActivity();

        Calendar mCalendar = Calendar.getInstance();
        AlertDialog.Builder mDialog = new AlertDialog.Builder(container);
        View view = container.getLayoutInflater().inflate(R.layout.layout_startdialog, null);
        mDialog.setView(view);
        mDialog.setPositiveButton(R.string.btn_ok, null);
        mDialog.setNegativeButton(R.string.btn_cancel, null);
        EditText editText = (EditText)view.findViewById(R.id.dialog_input);

        switch (id) {
            case DIALOG_DATE_PICKER:
                DatePickerDialog.OnDateSetListener mListner = new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                    }
                };
                return new DatePickerDialog(container, mListner, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            case DIALOG_TIME_PICKER:
                TimePickerDialog.OnTimeSetListener mListener = new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                    }
                };
                return new TimePickerDialog(container, mListener, mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE), true);
            case DIALOG_DURATION:
                mDialog.setTitle(R.string.text_dura);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                return mDialog.create();
            case DIALOG_DISTANCE:
                mDialog.setTitle(R.string.text_dist);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                return mDialog.create();
            case DIALOG_CALORIES:
                mDialog.setTitle(R.string.text_calo);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                return mDialog.create();
            case DIALOG_HEART_RATE:
                mDialog.setTitle(R.string.text_hear);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                return mDialog.create();
            case DIALOG_COMMENT:
                mDialog.setTitle(R.string.text_comm);
                editText.setHint(R.string.text_hint);
                return mDialog.create();
            case DIALOG_ID_PHOTO_PICKER:
                AlertDialog.Builder dialog = new AlertDialog.Builder(container);
                dialog.setTitle(R.string.dialog_photo);
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ProfileActivity)container).onCamera(which);
                    }
                };
                dialog.setItems(R.array.entries_photo, listener);
                return dialog.create();
            default:
                return null;
        }
    }
}
