package edu.metrostate.cardealer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

    public class VehicleEditActivity extends AppCompatActivity {
        Vehicle vehicleObject;
        String carMakeString, carModelString, carIdString, carPriceString;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_vehicle);

            Intent i = getIntent();
            vehicleObject =(Vehicle) i.getSerializableExtra("vehicleObject");

            TextView vehicleMake = (TextView)findViewById(R.id.editMake);
            TextView vehicleModel = (TextView)findViewById(R.id.editModel);
            TextView vehicleId = (TextView)findViewById(R.id.editID);
            TextView vehiclePrice = (TextView)findViewById(R.id.editPrice);
            Spinner vehicleCurrencyType = (Spinner) findViewById(R.id.editCurrencyType);
            Spinner vehicleType = (Spinner) findViewById(R.id.editType);

            Spinner carTypesSpinner = (Spinner) findViewById(R.id.editType);
            ArrayAdapter<CharSequence> carTypesAdapter = ArrayAdapter.createFromResource(this, R.array.carTypes, android.R.layout.simple_spinner_item);
            carTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            carTypesSpinner.setAdapter(carTypesAdapter);

            Spinner currencyTypesSpinner = (Spinner) findViewById(R.id.editCurrencyType);
            ArrayAdapter<CharSequence> currencyTypesAdapter = ArrayAdapter.createFromResource(this, R.array.currencyTypes, android.R.layout.simple_spinner_item);
            currencyTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            currencyTypesSpinner.setAdapter(currencyTypesAdapter);


            vehicleMake.setText(vehicleObject.getVehicle_manufacturer());
            vehicleModel.setText(vehicleObject.getVehicle_model());
            vehicleId.setText(vehicleObject.getVehicle_id());
            vehiclePrice.setText(Integer.toString(vehicleObject.getPrice()));
            if(vehicleObject.getCurrencyType().equalsIgnoreCase("Dollars")){
                vehicleCurrencyType.setSelection(0);
            }
            else{
                vehicleCurrencyType.setSelection(1);
            }
            if(vehicleObject.getVehicle_type().equalsIgnoreCase("Sedan")){vehicleType.setSelection(0);}
            if(vehicleObject.getVehicle_type().equalsIgnoreCase("Pickup")){vehicleType.setSelection(1);}
            if(vehicleObject.getVehicle_type().equalsIgnoreCase("Sports Car")){vehicleType.setSelection(2);}
            if(vehicleObject.getVehicle_type().equalsIgnoreCase("SUV")){vehicleType.setSelection(3);}

            TextInputEditText carMakeInput = findViewById(R.id.editMake);
            carMakeInput.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    carMakeString = s.toString();
                    vehicleObject.setVehicle_manufacturer(carMakeString);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });


            TextInputEditText carModelInput = findViewById(R.id.editModel);
            carModelInput.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    carModelString = s.toString();
                    vehicleObject.setVehicle_model(carModelString);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });

            TextInputEditText carIdInput = findViewById(R.id.editID);
            carIdInput.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    carIdString = s.toString();
                    vehicleObject.setVehicle_manufacturer(carIdString);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });

            TextInputEditText carPriceInput = findViewById(R.id.editPrice);
            carIdInput.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    carPriceString = s.toString();
                    vehicleObject.setPrice(Integer.parseInt(carPriceString));
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });

            Spinner currencyTypeSpinner = (Spinner) findViewById(R.id.editCurrencyType);
            String currencyType = currencyTypeSpinner.getSelectedItem().toString();

            Spinner vehicleTypeSpinner = (Spinner) findViewById(R.id.editType);
            String vehicleTypeString = vehicleTypeSpinner.getSelectedItem().toString();
            vehicleObject.setVehicle_type(vehicleTypeString);

            if (currencyType.equals("$")) {
                vehicleObject.setCurrencyType("Dollars");
            } else {
                vehicleObject.setCurrencyType("Pounds");
            }

            findViewById(R.id.editSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int counter = 0;
                    int dealerIndex = 0;
                    for(Dealer d: Company.getCompany()){
                        if(vehicleObject.getDealership_id().equalsIgnoreCase(Company.getCompany().get(counter).getDealer_id())){
                            dealerIndex = counter;
                        }
                        counter++;
                    }

                    counter = 0;
                    int vehicleIndex = 0;
                    for(Vehicle vehicle : Company.getCompany().get(dealerIndex).getListOfCarsAtDealer()){
                        if(vehicleObject.getVehicle_id().equalsIgnoreCase(vehicleObject.getVehicle_id())){
                            vehicleIndex = counter;
                        }
                        counter++;
                    }

                    Company.getCompany().get(dealerIndex).getListOfCarsAtDealer().set(vehicleIndex, vehicleObject);
                    finish();
                }
            });
        }
    }

