package edu.metrostate.cardealer.controllers.commands;

import java.io.*;

import edu.metrostate.cardealer.models.Company;

public class SaveSerializedData {

    public void saveSerializedData(){

       // get current directory 
       String serializedDataFilePath = System.getProperty("user.dir") + "\\company-serialized-data.ser";
       
        try {

        //create an ouput file named company-serialized-data.ser (serialized extension)
        FileOutputStream fileOut = new FileOutputStream(serializedDataFilePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        //we proceed to serialize the list of vehicles to the specified filepath.
        out.writeObject(Company.getCompany());
        out.close();
        fileOut.close();
     } catch (IOException i) {
      
        i.printStackTrace();
        System.out.println("Failed to serialize specified data");
      } 
   }
}
