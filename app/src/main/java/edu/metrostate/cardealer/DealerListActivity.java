package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.metrostate.cardealer.models.Dealer;

public class DealerListActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_list);

        // Get the application instance from the activity
        CarDealerApplication app = (CarDealerApplication) getApplication();

        // Create an adapter for the list view
        DealerAdapter adapter = new DealerAdapter(this, app.getDealerList());

        // Find the list view and add the adapter
        ListView dealerList = ((ListView)findViewById(R.id.dealer_list));
        dealerList.setAdapter(adapter);

        //todo onHold open command intent for dealer
//

        dealerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //showDialog(adapter.getItem(position));

                //Xue's codes, vehicleListActivity switched out for ListOfVehiclesAtDealer
                Intent i = new Intent(DealerListActivity.this, VehicleListActivity.class);
                Dealer selectedDealer = adapter.getItem(position);
                i.putExtra("selectedDealer", selectedDealer);
                startActivity(i);
            }
        });

        findViewById(R.id.buttonAddDealer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent with the new activity
                Intent intent = new Intent(DealerListActivity.this, AddDealerActivity.class);

                // Launch the new Activity
                startActivity(intent);
            }
        });


    }
}
