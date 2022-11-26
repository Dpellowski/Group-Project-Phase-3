package edu.metrostate.cardealer.controllers.commands;

import edu.metrostate.cardealer.models.Company;
import edu.metrostate.cardealer.models.Dealer;
import edu.metrostate.cardealer.models.Vehicle;

public class SetLoanStatus {

    //sets the loan status to the opposite of what is currently is
    public void setLoanStatus(String carID){

        for(Dealer d : Company.getCompany()){

            for(Vehicle v : d.getListOfCarsAtDealer()){

                if(v.getVehicle_id().equals(carID)){

                    v.setIsLoaned(!v.getIsLoaned());
                }
            }
        }
    }
}
