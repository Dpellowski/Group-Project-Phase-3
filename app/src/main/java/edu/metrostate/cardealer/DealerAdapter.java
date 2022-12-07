package edu.metrostate.cardealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.metrostate.cardealer.controllers.commands.Dealer_On_Off;
import edu.metrostate.cardealer.models.Dealer;

import java.util.List;

public class DealerAdapter extends ArrayAdapter<Dealer> {
    public DealerAdapter(Context context, List<Dealer> shelterList) {
        super(context, R.layout.dealer_item, shelterList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dealer_item, parent, false);
        }

        Switch switch_activation_status = convertView.findViewById(R.id.switch_activation_status);
        TextView id = convertView.findViewById(R.id.dealer_id);
        TextView model = convertView.findViewById(R.id.dealer_status);


        id.setText(getItem(position).getDealer_id());
        model.setText(getItem(position).getActivatedStatus());

        if(getItem(position).getActivatedStatus().equals("Activated")) {
            switch_activation_status.setChecked(true);
        }else{
            switch_activation_status.setChecked(false);
        }

        switch_activation_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dealer_On_Off command = new Dealer_On_Off();
                command.changeDealerStatus(getItem(position).getDealer_id());
                model.setText(getItem(position).getActivatedStatus());
            }
        });

        return convertView;
    }
}

