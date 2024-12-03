package android.example.therapysmart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import data.Med;
import data.MedRepository;

public class addMedication extends AppCompatActivity implements View.OnTouchListener,
        View.OnClickListener, TextWatcher {

    private EditText mAddMedText;
    private Button nextbutton;
    private Spinner mMedUnitsSpinner;

    public int mMedUnits = Med.pills;

    private boolean Spinnerchosen = true;

    private String selection;

    private boolean mIsNewMed;
    private MedRepository mMedRepository;
    private Med mMedFinal;
    private Med mMedInitial;

    private int doseValue;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        NumberPicker numberPicker1 = findViewById(R.id.dose_picker);
        nextbutton = findViewById(R.id.next2_button);
        mAddMedText = findViewById(R.id.medication_name);
        mMedUnitsSpinner = findViewById(R.id.units_spinner);

        mMedRepository = new MedRepository(this);

        setListeners();

        numberPicker1.setMinValue(0);
        numberPicker1.setValue(1);
        numberPicker1.setMaxValue(1000);

       /* Update:!!! ArrayAdapter<String> myAdapter = new ArrayAdapter<>(addMedication.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMedUnitsSpinner.setAdapter(myAdapter);
        */

        numberPicker1.setOnValueChangedListener((NumberPicker numberPicker, int oldValue, int newValue) ->
                doseValue = newValue);


        if (getIncomingIntent()) {
            //this is a new note
            mMedFinal = new Med();
            mMedInitial = new Med();
        } else {
            //this is not a new note

            mAddMedText.setText(mMedInitial.getName());
            doseValue = mMedInitial.getDose();

            mMedUnitsSpinner.setSelection(mMedInitial.getDose());
        }

        setupSpinner();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_med")) {
            mMedInitial = getIntent().getParcelableExtra("selected_med");

            mMedFinal = new Med();
            assert mMedInitial != null;
            mMedFinal.setName(mMedInitial.getName());
            mMedFinal.setUnits(mMedInitial.getUnits());
            mMedFinal.setDose(mMedInitial.getDose());
            mMedFinal.setId(mMedInitial.getId());

            mIsNewMed = false;
            return false;
        }
        mIsNewMed = true;
        return true;
    }

    private void setListeners() {

        nextbutton.setOnClickListener(this);
        mAddMedText.addTextChangedListener(this);
        mMedUnitsSpinner.setOnTouchListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String it will use
        // the spinner will use the default layout
        ArrayAdapter <?> medSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        medSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mMedUnitsSpinner.setAdapter(medSpinnerAdapter);


        // Set the integer mSelected to the constant values
        mMedUnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selection = (String) parent.getItemAtPosition(position);

                if(Spinnerchosen) {
                    if (!mIsNewMed) {

                        Spinnerchosen = false;

                        switch (mMedInitial.getDose()) {
                            case 1: {
                                mMedUnitsSpinner.setSelection(1);
                                break;

                            }
                            case 2: {
                                mMedUnitsSpinner.setSelection(2);
                                break;

                            }
                            case 3: {
                                mMedUnitsSpinner.setSelection(3);
                                break;

                            }
                            case 4: {
                                mMedUnitsSpinner.setSelection(4);
                                break;
                            }
                            case 5: {
                                mMedUnitsSpinner.setSelection(5);
                                break;

                            }
                            case 6: {
                                mMedUnitsSpinner.setSelection(6);
                                break;

                            }
                            case 7: {
                                mMedUnitsSpinner.setSelection(7);
                                break;

                            }
                            case 8: {
                                mMedUnitsSpinner.setSelection(8);
                                break;
                            }
                        }
                    }
                }
                Spinnerchosen = false;
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.puffs))) {
                        mMedUnits = Med.puffs; // Daily
                    } else if (selection.equals(getString(R.string.pessary))) {
                        mMedUnits = Med.pessary; // Weekly
                    } else if (selection.equals(getString(R.string.patches))) {
                        mMedUnits = Med.patches; // Monthly
                    } else if (selection.equals(getString(R.string.piece))) {
                        mMedUnits = Med.piece; // Yearly
                    } else if (selection.equals(getString(R.string.sachets))) {
                        mMedUnits = Med.sachets; // Weekly
                    } else if (selection.equals(getString(R.string.sprays))) {
                        mMedUnits = Med.sprays; // Monthly
                    } else if (selection.equals(getString(R.string.tablespoons))) {
                        mMedUnits = Med.tablespoons; // Yearly
                    } else if (selection.equals(getString(R.string.teaspoons))) {
                        mMedUnits = Med.teaspoons; // Yearly
                    } else {
                        mMedUnits = Med.pills; // One Time
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMedUnits = 0; // One-Time
            }
        });
    }

    private void saveChanges() {
        if (mIsNewMed) {
            saveNewMed();
        } else {
            updateMed();
        }
    }

    public void updateMed() {
        mMedRepository.updateMed(mMedFinal);
    }

    public void saveNewMed() {
        mMedRepository.insertMed(mMedFinal);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.next2_button) {
            mMedFinal.setName(mAddMedText.getText().toString().trim());
            mMedFinal.setUnits(mMedUnits);
            mMedFinal.setDose(doseValue);
            Intent intent = new Intent(addMedication.this, dashboard.class);
            startActivity(intent);
            saveChanges();
        }
    }
}