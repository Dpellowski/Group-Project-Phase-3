package edu.metrostate.cardealer;

import android.app.Application;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import edu.metrostate.cardealer.controllers.commands.FileImporter;
import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;
public class CarDealerApplication extends Application {
    private final List<Vehicle> vehicleList = new ArrayList<>();
    private final List<Dealer> dealerList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();



    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
    public List<Dealer> getDealerList() {
        return dealerList;
    }

    public void writeFile(String fileName) {

        //TODO: Remove this code
        // Gets the output path which is /sdcard/Android/data/edu.metrostate.cardealer/files directory
        File externalDir = getExternalFilesDir(null);

        // Establishes the output file as "myfile.txt"
        File outputFile = new File(externalDir, fileName);

        try {
            Files.createFile(outputFile.toPath());

            // Saves the string "My data" to the file
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }

    public void importFile(String fileName) throws IOException, ParserConfigurationException, SAXException {

        File externalDir = getExternalFilesDir(null);

        File inputFile = new File(externalDir, fileName);

        FileImporter fileImport = new FileImporter();

        fileImport.fileImport(inputFile.getAbsolutePath());

        //debug
        //todo make it dealers
        for(Dealer d : Company.getCompany()){
            vehicleList.addAll(d.getListOfCarsAtDealer());
        }
        //Populate dealerList
        for (Dealer d : Company.getCompany()) {
            dealerList.add(d);
        }



    }



}
