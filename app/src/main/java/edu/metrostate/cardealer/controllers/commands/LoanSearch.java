package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

public class LoanSearch {

    public boolean[] loanSearch(String carID) {

        boolean invalid_CarID = true;
        boolean carLoanedStatus = false;

        for(Dealer d : Company.getCompany()){

            for(Vehicle v : d.getListOfCarsAtDealer()){

                if(v.getVehicle_id().equals(carID)){

                    invalid_CarID = false;
                    carLoanedStatus = v.getIsLoaned();
                }
            }
        }

        return new boolean[] {invalid_CarID, carLoanedStatus};
    }

}
