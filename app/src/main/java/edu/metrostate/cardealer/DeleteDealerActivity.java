package edu.metrostate.cardealer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.metrostate.cardealer.controllers.commands.DeleteDealer;

public class DeleteDealerActivity extends AppCompatActivity {

        private String sDealerID;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_delete_dealer);

            EditText editText_dealerId = findViewById(R.id.editText_deleteDealerID);

            editText_dealerId.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {

                    sDealerID = s.toString();
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });

            findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DeleteDealer command = new DeleteDealer();
                    command.deleteDealer(sDealerID);

                }
            });

            findViewById(R.id.buttonBackDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                    Intent intent = new Intent(DeleteDealerActivity.this, DealerListActivity.class);

                    // Launch the new Activity
                    startActivity(intent);

                }
            });
        }
}
