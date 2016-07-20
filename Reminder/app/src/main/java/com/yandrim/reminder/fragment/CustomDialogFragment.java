package com.yandrim.reminder.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yandrim.reminder.MainActivity;
import com.yandrim.reminder.R;
import com.yandrim.reminder.dto.RemindDTO;

import java.util.Calendar;

public class CustomDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private View form = null;
    private TextView currentDateTime;
    private Calendar dateAndTime = Calendar.getInstance();
    private String[] spinnerData = {"встреча", "дело", "день рождения"};
    private Spinner spinner;

    private Button mChangeDate;
    private Button mChangeTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        form = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment, null);

        initView();

        initListeners();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner)form.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return (builder.setTitle("Добавить напоминание").setView(form).setPositiveButton(android.R.string.ok, this).setNegativeButton(android.R.string.cancel, null).create());
    }

    private void initView() {
        currentDateTime = (TextView) form.findViewById(R.id.currentDateTime);

        mChangeDate = (Button) form.findViewById(R.id.dateButton);
        mChangeTime = (Button) form.findViewById(R.id.timeButton);
    }

    private void initListeners() {
        mChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
        mChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText titleBox = (EditText) form.findViewById(R.id.themeET);
        EditText descrBox = (EditText) form.findViewById(R.id.descrET);
        String title = titleBox.getText().toString();
        if(title.length()==0) {
            Toast.makeText(getContext(), "Нет названия напоминания", Toast.LENGTH_SHORT).show();
            return;
        }
        String description = descrBox.getText().toString();
        setInitialDateTime();
        RemindDTO remindDTO = new RemindDTO(title);
        remindDTO.setDescription(description);
        remindDTO.setRemindDate(dateAndTime.getTime());

        MainActivity activity = (MainActivity) getActivity();
        switch (spinner.getSelectedItemPosition()){
            case 0:
                Toast.makeText(getContext(), "Добавлена новая встреча", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(),"Добавлено новое дело", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(),"Добавлен день рождения", Toast.LENGTH_SHORT).show();
                break;
        }
        activity.setRemindDTO(remindDTO, spinner.getSelectedItemPosition());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    public void setDate(View v) {
        new DatePickerDialog(v.getContext(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTime(View v) {
        new TimePickerDialog(form.getContext(), t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialDateTime() {
        currentDateTime.setText(DateUtils.formatDateTime(getContext(), dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}
