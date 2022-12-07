package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.controllers.converters.XmlToArray;
import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

public class ExportDealerToJSON {

    public boolean exportDealerToJSON(String dealerID) {

        XmlToArray c = new XmlToArray();

        boolean invalid_DealerID = true;

        Dealer dealer = null;

        //find dealer
        for(Dealer d : Company.getCompany()){

            if(d.getDealer_id().equals(dealerID)){

                dealer = d;
                invalid_DealerID = false; //dealer found
            }
        }

        //if dealer found, export to JSON
        if(! invalid_DealerID){
            
            c.convertToJson(dealer);
        }
        
        return invalid_DealerID;
    }
}
