package edu.metrostate.cardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class ImportFileActivity extends AppCompatActivity {

    EditText input;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_file);
        //get input text
        input = (EditText) findViewById(R.id.file_name);
        error = (TextView) findViewById(R.id.invalidFileText);

        //get input button (Input was reserved word)
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get instance of app
                CarDealerApplication app = (CarDealerApplication) getApplication();

                //attempt to import file name
                try {
                    app.importFile(String.valueOf(input.getText()));

                    //go back to main if success
                    Intent intent = new Intent(ImportFileActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    error.setText("Invalid File");
                    e.printStackTrace();
                }
            }
        });
    }
}