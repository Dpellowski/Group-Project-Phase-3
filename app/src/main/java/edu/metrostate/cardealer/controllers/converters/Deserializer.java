package edu.metrostate.cardealer.controllers.converters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

public class Deserializer {
    //get out
    public static void deserializeData(String serializedDataPath){
        List<Dealer> listOfDealers = null;
        try {

            // This block of code  of creates an object of arrayList containing the vehicle objects to be loaded into our program further down
            FileInputStream serializedDataFile = new FileInputStream(serializedDataPath);
            ObjectInputStream inputObjects = new ObjectInputStream(serializedDataFile);
            listOfDealers = (List<Dealer>)inputObjects.readObject();
            serializedDataFile.close();
            inputObjects.close();
            Company.getCompany().addAll(listOfDealers);

        } catch (IOException i) {

            i.printStackTrace();
            System.out.println("fail ioexception deserialize");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.println("fail classnotfoundexception deserialize");
        }
    }
}
