package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.controllers.converters.JsonToArray;
import edu.metrostate.cardealer.controllers.converters.XmlToArray;
import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileImporter {

    public void fileImport(String fileName) throws IOException, ParserConfigurationException, SAXException {

        List<Dealer> listOfDealers;

        //list of dealers contains all cars read from json file
        JsonToArray json = new JsonToArray();
        XmlToArray xml = new XmlToArray();
        if(fileName.endsWith(".xml")){
            File file = new File(fileName);
            listOfDealers = xml.fromXmlToArr(file);
        }
        else{
            FileReader file = new FileReader(fileName);
            listOfDealers = json.fromJsonToInvArr(file);
        }



        //////////////////////////////
        //add new dealers to company//
        //////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////
        //go threw all cars in the listOfDealers and all cars in Company and if there are matches,
        // delete the car that is in the listOfDealers

        //check all incoming cars to see if they are in any of the company dealers
        for(Dealer newDealer : listOfDealers){
            for(int i = 0; i < newDealer.getListOfCarsAtDealer().size(); i++){
                Vehicle newVehicle = newDealer.getListOfCarsAtDealer().get(i);
                for(Dealer companyDealer : Company.getCompany()){
                    for(Vehicle companyVehicle : companyDealer.getListOfCarsAtDealer()){
                        if(newVehicle.getVehicle_id().equals(companyVehicle.getVehicle_id())){
                            newDealer.getListOfCarsAtDealer().remove(newVehicle);
                            i=-1;
                            break;
                        }
                    }
                }
            }
        }


        

        //get dealerIDs for all dealers in Company
        List<String> companyDealerIDs = new ArrayList<>();
        for(Dealer d : Company.getCompany()){
            companyDealerIDs.add(d.getDealer_id());
        }

        //if a Dealer id from listOfDealers does not exist in companyDealerIDs, add the new dealer to company
        List<Dealer> deleteFromListOfDealers = new ArrayList<>();
        for(Dealer d : listOfDealers){
            if(!companyDealerIDs.contains(d.getDealer_id())){
                Company.getCompany().add(d);
                deleteFromListOfDealers.add(d);
            }
        }

        //delete the dealers that have been added to Company
        for(Dealer d : deleteFromListOfDealers){
            listOfDealers.remove(d);
        }

        //merge remaining new dealers with company dealers
        for(Dealer newDealer : listOfDealers){
            for(Dealer companyDealer : Company.getCompany()){

                //check if company dealer has a name, if not and the new dealer does,
                //added the name of the new dealer to the company dealer
                if(newDealer.getDealer_id().equals(companyDealer.getDealer_id())){
                    if(companyDealer.getName().equals("") && !newDealer.getName().equals("")){
                        companyDealer.setName(newDealer.getName());
                    }


                    for(Vehicle v : newDealer.getListOfCarsAtDealer()){
                        companyDealer.getListOfCarsAtDealer().add(v);
                    }
                }
            }
        }


    }
}
