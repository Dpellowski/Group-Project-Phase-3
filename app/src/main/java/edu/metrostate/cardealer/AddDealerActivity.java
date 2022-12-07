package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.controllers.commands.CreateDealer;
import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

public class AddDealerActivity extends AppCompatActivity {

    private String dealerID, dealerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dealer);

        EditText editText_dealerID = findViewById(R.id.editText_dealerID);
        EditText editText_dealerName = findViewById(R.id.editText_dealerName);

        editText_dealerID.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                dealerID = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editText_dealerName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                dealerName = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        findViewById(R.id.button_addDealer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDealer command = new CreateDealer();

                if(command.createDealer(dealerID, dealerName)) {

                    Toast.makeText(AddDealerActivity.this, "Invalid Dealer", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddDealerActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }




            }
            });

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent with the new activity
                Intent intent = new Intent(AddDealerActivity.this, DealerListActivity.class);

                // Launch the new Activity
                startActivity(intent);
            }
        });




    }
}