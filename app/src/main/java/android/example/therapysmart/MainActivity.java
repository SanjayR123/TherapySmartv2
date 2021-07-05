package android.example.therapysmart;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.signupbutton);

        button.setOnClickListener(view -> openActivity2());
    }

    public void openActivity2(){
        Intent intent = new Intent(this, PatientInfo.class);
        startActivity(intent);
    }
}