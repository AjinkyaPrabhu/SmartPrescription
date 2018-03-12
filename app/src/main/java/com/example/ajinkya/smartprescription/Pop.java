package com.example.ajinkya.smartprescription;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ajinkya on 10/3/18.
 */

public class Pop extends Activity implements DatePickerDialog.OnDateSetListener {
    private Calendar fromdate;
    private Calendar todate;
    private String typeDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.55));
        ImageButton calenderButton = (ImageButton) findViewById(R.id.datepicker1);

        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDate = "fromdate";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");


            }
        });
        ImageButton calenderButton2 = (ImageButton) findViewById(R.id.datepicker2);

        calenderButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDate = "todate";
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");

            }
        });

        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        TextInputEditText medname = (TextInputEditText) findViewById(R.id.name);
        TextInputEditText dosage = (TextInputEditText) findViewById(R.id.dosage);
        CheckBox[] timeofday = new CheckBox[4];
        timeofday[0] = (CheckBox) findViewById(R.id.c0);
        timeofday[1] = (CheckBox) findViewById(R.id.c1);
        timeofday[2] = (CheckBox) findViewById(R.id.c2);
        timeofday[3] = (CheckBox) findViewById(R.id.c3);
        String timeofDayString = "";
        String fromDate = "";
        String toDate = "";
        if (fromdate != null | todate != null) {
            fromDate = fromdate.get(Calendar.DAY_OF_MONTH) + "-" + fromdate.get(Calendar.MONTH) + "-" + fromdate.get(Calendar.YEAR);
            toDate = todate.get(Calendar.DAY_OF_MONTH) + "-" + todate.get(Calendar.MONTH) + "-" + todate.get(Calendar.YEAR);
        }
        for (int i = 0; i < 4; i++) {

            timeofDayString += timeofday[i].isChecked() + "-";
        }

        data.putExtra("MedName", "" + medname.getText());
        data.putExtra("Dosage", "" + dosage.getText());
        data.putExtra("TimesOfDay", timeofDayString);
        data.putExtra("from", fromDate);
        data.putExtra("to", toDate);


        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR, year);
        calendar.set(calendar.MONTH, month);
        calendar.set(calendar.DAY_OF_MONTH, day);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d");
        if (typeDate == "fromdate") {
            TextView textView1 = (TextView) findViewById(R.id.fromtext);
            fromdate = calendar;
            textView1.setText(" " + dateFormatter.format(fromdate.getTime()));
        } else if (typeDate == "todate") {
            TextView textView2 = (TextView) findViewById(R.id.totext);
            todate = calendar;
            textView2.setText(" " + dateFormatter.format(todate.getTime()));

        }
    }
}