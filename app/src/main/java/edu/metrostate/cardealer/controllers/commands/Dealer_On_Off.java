package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import java.util.List;

public class Dealer_On_Off {
    public void changeDealerStatus(String dealerId) {

        List<Dealer> listOfDealers = Company.getCompany();


        for (int i = 0; i < listOfDealers.size(); i++) {
            if (dealerId.equals(listOfDealers.get(i).getDealer_id())) {
                Dealer d =  listOfDealers.get(i);
                d.setIsActivatedStatus(!d.getBoolActivationStatus());
            }

        }

    }
}
