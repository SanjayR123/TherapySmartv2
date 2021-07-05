package android.example.therapysmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Calendar;

public class PatientInfo extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        initDatePicker();
        dateButton = findViewById(R.id.choose_date);
        dateButton.setText(getTodaysDate());

        Button button = findViewById(R.id.next_button);

        button.setOnClickListener(view -> openAddMedActivity());

        Spinner gender_spinner = findViewById(R.id.gender_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(PatientInfo.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genders));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(myAdapter);


    }

    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month - 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
    }

    private String makeDateString(int day, int month, int year){
        return day + " " + month + " " + year;
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    public void openAddMedActivity(){
        Intent intent = new Intent(this, addMedication.class);
        startActivity(intent);
    }
}