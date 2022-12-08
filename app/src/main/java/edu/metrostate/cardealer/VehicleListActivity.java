package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import edu.metrostate.cardealer.controllers.commands.DeleteDealer;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

public class VehicleListActivity extends AppCompatActivity {

    Dealer dealerObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        //Xue's code
        Intent i = getIntent();
        dealerObject = (Dealer)i.getSerializableExtra("selectedDealer");
        TextView dealerName = findViewById(R.id.title);
        if(dealerObject.getName().equals("")){
            dealerName.setText("No name Dealer");
        }
        else{
            dealerName.setText(dealerObject.getName());
        }



        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Create an adapter for the list view
        VehicleAdapter adapter = new VehicleAdapter(this, dealerObject.getListOfCarsAtDealer());

        // Find the list view and add the adapter
        ListView vehicleList = ((ListView)findViewById(R.id.vehicle_list));
        vehicleList.setAdapter(adapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //todo hunter or valle on click open commands and rig commands up to buttons
                showDialog(adapter.getItem(position));
            }
        });




    }

    public void showDialog(Vehicle vehicle) {



        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("My alert")
                .setCancelable(false)
                .setTitle("Vehicle ID: " + vehicle.getVehicle_id())
                .setMessage("Model: " + vehicle.getVehicle_model() + "\n" +
                        "Vehicle Type: " + vehicle.getVehicle_type() + "\n" +
                        "Vehicle Manufacturer: " + vehicle.getVehicle_manufacturer() + "\n" +
                        "Vehicle Price: " + vehicle.getPrice() + vehicle.getCurrencyType() + "\n" +
                        "Rental Status: " + vehicle.getIsLoaned()
                )
                .setPositiveButton( "Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(VehicleListActivity.this, VehicleEditActivity.class);
                        i.putExtra("vehicleObject", vehicle);
                        startActivity(i);
                        finish();
                    }
                });
            dialog.setNegativeButton("Cancel", (dialog1, id) -> dialog1.dismiss()).create();
        dialog.show();
    }

    public void addCarBtn(View v){
        //Intent to go to the AddCarActivity
        Intent i = new Intent(VehicleListActivity.this, AddCarActivity.class);

        //Dealer object to be pass over to the AddCarActivity
        i.putExtra("dealerObject", dealerObject);

        startActivity(i);
        finish();
    }


}