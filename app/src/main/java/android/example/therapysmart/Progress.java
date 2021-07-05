package android.example.therapysmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Progress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.progress);

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

                        case R.id.dashboard:
                            startActivity(new Intent(getApplicationContext(), dashboard.class));
                            overridePendingTransition(0,0);
                            return true;

                    }
                    return false;
                });
    }
}