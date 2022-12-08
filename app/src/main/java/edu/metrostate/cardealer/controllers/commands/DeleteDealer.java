package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

public class DeleteDealer {

    public boolean deleteDealer(String dealerID){

        boolean deleted = false;

        for(Dealer d : Company.getCompany()){
            if(d.getDealer_id().equals(dealerID)){
                Company.getCompany().remove(d);
                deleted = true;
                break;
            }
        }

        return deleted;
    }
}
