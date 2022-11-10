package edu.metrostate.cardealer.controllers.commands;


import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;

import java.util.List;

public class DealerOn {

    public void dealerOn(String dealer_ID) {

        List<Dealer> listOfDealers = Company.getCompany();

        boolean dealershipIDFound = false;

        for (int i = 0; i < listOfDealers.size(); i++) {

            if (dealer_ID.equals(listOfDealers.get(i).getDealer_id())) {

                listOfDealers.get(i).setIsActivatedStatus(true);

                dealershipIDFound = true;
            }
        }

        if(dealershipIDFound == false){

            System.out.println("Dealership ID " + dealer_ID + " does not exist.\n");
        }

        //resets dealershipIDFound
        dealershipIDFound = false;
    }
}
