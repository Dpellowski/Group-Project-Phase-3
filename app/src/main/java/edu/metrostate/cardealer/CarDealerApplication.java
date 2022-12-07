package edu.metrostate.cardealer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import edu.metrostate.cardealer.controllers.converters.Deserializer;
import edu.metrostate.cardealer.controllers.converters.XmlToArray;
import edu.metrostate.cardealer.controllers.commands.FileImporter;
import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

public class CarDealerApplication extends Application {
    private List<Dealer> dealerList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        readSaveData();
    }

    public List<Dealer> getDealerList() {
        return dealerList;
    }

    public void readSaveData(){
        File externalDir = getExternalFilesDir(null);
        File f = new File(externalDir+"/save_data.ser");

        Deserializer deserialize = new Deserializer();

        if(f.exists() && !f.isDirectory()){

            deserialize.deserializeData(externalDir+"/save_data.ser");
        }

        dealerList = Company.getCompany();
    }

    public void writeFile() {

        //TODO: Remove this code
        // Gets the output path which is /sdcard/Android/data/edu.metrostate.cardealer/files directory
        File externalDir = getExternalFilesDir(null);
        Context context = getApplicationContext();
        try {

            //create an ouput file named company-serialized-data.ser (serialized extension)
            FileOutputStream fileOut = new FileOutputStream(externalDir+"/save_data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            //we proceed to serialize the list of vehicles to the specified filepath.
            out.writeObject(Company.getCompany());
            out.close();
            fileOut.close();


            Toast.makeText(context, "Save was successful.",
                    Toast.LENGTH_LONG).show();
        } catch (IOException i) {

            i.printStackTrace();
            System.out.println("Failed to serialize specified data");
            Toast.makeText(context, "Save failed.",
                    Toast.LENGTH_LONG).show();

        }

    }

    public void importFile(String fileName) throws IOException, ParserConfigurationException, SAXException {

        File externalDir = getExternalFilesDir(null);

        File inputFile = new File(externalDir, fileName);

        FileImporter fileImport = new FileImporter();

        fileImport.fileImport(inputFile.getAbsolutePath());

        //set dealerList to our company dealerlist
        dealerList = Company.getCompany();

    }

}
