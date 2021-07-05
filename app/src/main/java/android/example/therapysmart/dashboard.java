package android.example.therapysmart;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.Med;
import data.MedRepository;

public class dashboard extends AppCompatActivity implements MedCursorAdapter.onMedListener {

    MedCursorAdapter mCursorAdapter;

    private RecyclerView mRecyclerView;

    private final ArrayList<Med> mMed = new ArrayList<>();
    private MedRepository mMedRepository;

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setSupportActionBar(findViewById(R.id.toolbar));
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Dashboard");
        setTitle("");

        FloatingActionButton button = findViewById(R.id.fab);

        button.setOnClickListener(view -> openAddMed());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.supply:
                            startActivity(new Intent(getApplicationContext(), Supply.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.doctor:
                            startActivity(new Intent(getApplicationContext(), Doctor.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.progress:
                            startActivity(new Intent(getApplicationContext(), Progress.class));
                            overridePendingTransition(0,0);
                            return true;

                    }
                    return false;
                });

        mRecyclerView = findViewById(R.id.med_list);
        initRecyclerView();
        mMedRepository = new MedRepository(this);
        retrieveMed();
    }

    private void retrieveMed() {
        mMedRepository.retrieveMed().observe(this, meds -> {
            if(mMed.size() > 0){
                mMed.clear();
            }
            if(meds != null){
                mMed.addAll(meds);

            }
            mCursorAdapter.notifyDataSetChanged();

        });
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCursorAdapter = new MedCursorAdapter(mMed, this);
        mRecyclerView.setAdapter(mCursorAdapter);
    }

    @Override
    public void onMedClick(int position) {
        Intent intent = new Intent(this, addMedication.class);
        intent.putExtra("selected_task", mMed.get(position));
        startActivity(intent);
    }


    public void openAddMed(){
        Intent intent = new Intent(this, addMedication.class);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(int position) {
        Log.d("Med", "Delete Button is clicked");
        Med position_med = mMed.get(position);
        deleteMed(position_med);
    }


    public void deleteMed(Med med) {
        mMed.remove(med);
        mCursorAdapter.notifyDataSetChanged();
        mMedRepository.deleteMed(med);
    }
}