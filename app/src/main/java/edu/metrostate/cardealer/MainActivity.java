package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent with the new activity
                Intent intent = new Intent(MainActivity.this, DealerListActivity.class);

                // Launch the new Activity
                startActivity(intent);
            }
        });

        findViewById(R.id.import_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent with the new activity
                Intent intent = new Intent(MainActivity.this, ImportFileActivity.class);

                // Launch the new Activity
                startActivity(intent);
            }
        });

    }
}