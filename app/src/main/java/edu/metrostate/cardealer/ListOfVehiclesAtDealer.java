package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ListOfVehiclesAtDealer extends AppCompatActivity {

    RecyclerView recyclerView;

    List carList = new ArrayList();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_vehicles_at_dealer);

        recyclerView = findViewById(R.id.recycleListCars);

        //Testing
        carList.add("1");
        carList.add("1");
        carList.add("1");
        carList.add("1");
        carList.add("1");

        adapter = new ArrayAdapter(ListOfVehiclesAtDealer.this, android.R.layout.simple_list_item_1, carList);

        recyclerView.setAdapter((RecyclerView.Adapter) adapter);
    }
}