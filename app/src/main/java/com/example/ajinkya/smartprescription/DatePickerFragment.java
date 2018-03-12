package com.example.ajinkya.smartprescription;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by ajinkya on 11/3/18.
 */

public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
