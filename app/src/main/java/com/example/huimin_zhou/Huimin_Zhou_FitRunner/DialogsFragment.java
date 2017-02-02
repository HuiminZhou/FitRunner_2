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
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.ExerciseEntry;

import java.util.Calendar;

/**
 * Created by Lucidity on 17/1/18.
 */

public class DialogsFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final int DIALOG_DATE_PICKER = 0;
    public static final int DIALOG_TIME_PICKER = 1;
    public static final int DIALOG_DURATION = 2;
    public static final int DIALOG_DISTANCE = 3;
    public static final int DIALOG_CALORIES = 4;
    public static final int DIALOG_HEART_RATE = 5;
    public static final int DIALOG_COMMENT = 6;
    public static final int DIALOG_ID_PHOTO_PICKER = 7;
    private static final String DIALOG_ID_KEY = "dialog_key";

    private EditText editText = null;
    private int id = 0;
    private String date = "";
    private String time = "";
    private String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static DialogsFragment newInstance(int id) {
        DialogsFragment dialogsFragment = new DialogsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_ID_KEY, id);
        dialogsFragment.setArguments(bundle);
        return dialogsFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        id = getArguments().getInt(DIALOG_ID_KEY);
        final Activity container = getActivity();

        Calendar mCalendar = Calendar.getInstance();
        AlertDialog.Builder mDialog = new AlertDialog.Builder(container);
        View view = container.getLayoutInflater().inflate(R.layout.layout_startdialog, null);
        mDialog.setView(view);

        editText = (EditText)view.findViewById(R.id.dialog_input);
        mDialog.setPositiveButton(R.string.btn_ok, this);
        mDialog.setNegativeButton(R.string.btn_cancel, null);

        switch (id) {
            case DIALOG_DATE_PICKER:
                DatePickerDialog.OnDateSetListener mListner =
                        new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Log.d("month index", "" + month);
                        date = months[month] + " " + day + " " + year;
                        ManualActivity.mEntry.setDate(date);
                    }
                };
                return new DatePickerDialog(
                        container,
                        mListner,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH));
            case DIALOG_TIME_PICKER:
                TimePickerDialog.OnTimeSetListener mListener =
                        new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        time = hour + ":" + minute + ":" + "00";
                        ManualActivity.mEntry.setTime(time);
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

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("date set", date);
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                switch (id) {
                    case DIALOG_DURATION:
                        ManualActivity.mEntry.setDuration(Integer.parseInt(editText.getText().toString()));
                        return;
                    case DIALOG_DISTANCE:
                        Log.d("onclick", editText.getText().toString());
                        ManualActivity.mEntry.setDistance(Integer.parseInt(editText.getText().toString()));
                        return;
                    case DIALOG_CALORIES:
                        ManualActivity.mEntry.setCalories(Integer.parseInt(editText.getText().toString()));
                        return;
                    case DIALOG_HEART_RATE:
                        ManualActivity.mEntry.setHeart(Integer.parseInt(editText.getText().toString()));
                        return;
                    case DIALOG_COMMENT:
                        return;
                    default:
                        return;
                }
            case DialogInterface.BUTTON_NEGATIVE:
                return;
            default:
                return;
        }
    }


}
