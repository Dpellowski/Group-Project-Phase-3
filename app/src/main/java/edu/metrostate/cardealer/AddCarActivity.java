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

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

public class AddCarActivity extends AppCompatActivity {
    private Dealer dealerObject;
    String carPriceString, carMake, carModel, carId, currencyType, vehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        CarDealerApplication app = (CarDealerApplication) getApplication(); //this does nothing?

        Spinner carTypesSpinner = (Spinner) findViewById(R.id.vehicleType);
        ArrayAdapter<CharSequence> carTypesAdapter = ArrayAdapter.createFromResource(this, R.array.carTypes, android.R.layout.simple_spinner_item);
        carTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carTypesSpinner.setAdapter(carTypesAdapter);

        Spinner currencyTypesSpinner = (Spinner) findViewById(R.id.vehicleCurrencyType);
        ArrayAdapter<CharSequence> currencyTypesAdapter = ArrayAdapter.createFromResource(this, R.array.currencyTypes, android.R.layout.simple_spinner_item);
        currencyTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencyTypesSpinner.setAdapter(currencyTypesAdapter);

        Intent i = getIntent();
        dealerObject = (Dealer) i.getSerializableExtra("dealerObject");


        EditText carMakeInput = (EditText) findViewById(R.id.vehicleMake);
        carMakeInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                carMake = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        TextInputEditText carModelInput = (TextInputEditText) findViewById(R.id.vehicleModel);
        carModelInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                carModel = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText carIdInput = (EditText) findViewById(R.id.vehicleId);
        carIdInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                carId = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        TextInputEditText carPriceInput = findViewById(R.id.vehiclePrice);
        carPriceInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                carPriceString = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        Spinner currencyTypeSpinner = (Spinner) findViewById(R.id.vehicleCurrencyType);
        currencyType = currencyTypeSpinner.getSelectedItem().toString();

        Spinner vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicleType);
        vehicleType = vehicleTypeSpinner.getSelectedItem().toString();


        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int carPrice = Integer.parseInt(carPriceString);

                long acquisitionDate = System.currentTimeMillis();

                Vehicle inputVehicle = new Vehicle(dealerObject.getDealer_id(), vehicleType, carMake, carModel, carId, carPrice, acquisitionDate);

                if (currencyType.equals("$")) {
                    inputVehicle.setCurrencyType("Dollars");
                } else {
                    inputVehicle.setCurrencyType("Pounds");
                }

                dealerObject.addToListOfCarsAtDealer(inputVehicle);

                int counter = 0;
                int dealerIndex = 0;
                for(Dealer d: Company.getCompany()){
                    if(d.getDealer_id().equalsIgnoreCase(Company.getCompany().get(counter).getDealer_id())){
                        dealerIndex = counter;
                    }
                    counter++;
                }
                Company.getCompany().set(dealerIndex, dealerObject);
                finish();
            }
        });
    }
}