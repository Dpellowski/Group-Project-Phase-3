package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

public class CreateDealer {

    public boolean createDealer(String dealerID, String dealerName){

        boolean invalid_DealerID = false;

        //check if dealer ID already exists in Company.getCompany()
        //if dealer ID already exists error
        for(Dealer d : Company.getCompany()){

            if(d.getDealer_id().equals(dealerID)){

                invalid_DealerID = true;
            }
        }

        //if dealerID is unique, create the dealer
        if(!invalid_DealerID){

            Dealer d = new Dealer(dealerID, dealerName);
            Company.getCompany().add(d);
        }
        
        return invalid_DealerID;
    }
}
