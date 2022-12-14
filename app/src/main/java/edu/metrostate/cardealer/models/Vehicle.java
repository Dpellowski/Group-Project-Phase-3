package edu.metrostate.cardealer.models;

import java.time.Instant;

public class Vehicle implements java.io.Serializable{

    // Model
    private String dealership_id;
    private String vehicle_type;
    private String vehicle_manufacturer;
    private String vehicle_model;
    private String vehicle_id;
    private int price;

    private String currencyType = "$";
    private long acquisition_date;

    private boolean isLoaned;

    public Vehicle(String dealership_id, String vehicle_type, String vehicle_manufacturer, String vehicle_model, String vehicle_id, int price, long acquisition_date) {
        
        this.dealership_id = dealership_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_manufacturer = vehicle_manufacturer;
        this.vehicle_model = vehicle_model;
        this.vehicle_id = vehicle_id;
        this.price = price;
        this.acquisition_date = acquisition_date;
        this.isLoaned = false;
    }

    public void setCurrencyType(String unit){

        this.currencyType = unit;
    }

    //this will set the dealership ID to whatever string is input for dealership_ID
    public void setDealership_id(String dealership_id) {

        this.dealership_id = dealership_id;
    }

    //this will set the vehicle type to whatever string is input for vehicle_type
    public void setVehicle_type(String vehicle_type) {

        this.vehicle_type = vehicle_type;
    }

    //this will set the vehicle manufacturer to whatever string is input for vehicle_manufacturer
    public void setVehicle_manufacturer(String vehicle_manufacturer) {

        this.vehicle_manufacturer = vehicle_manufacturer;
    }

    //this will set the vehicle model to whatever string is input for vehicle_model
    public void setVehicle_model(String vehicle_model) {

        this.vehicle_model = vehicle_model;
    }

    //this will set the vehicle ID to whatever string is input for vehicle_id
    public void setVehicle_id(String vehicle_id) {

        this.vehicle_id = vehicle_id;
    }

    //this will set the vehicle price to whatever int is input for price
    public void setPrice(int price) {

        this.price = price;
    }

    //this will set the vehicle acquisition date to whatever long is input for acquisition_date
    public void setAcquisition_date(long acquisition_date) {

        this.acquisition_date = acquisition_date;
    }

    public void setIsLoaned(boolean bool) {
        this.isLoaned = bool;
    }


    public String getCurrencyType() {

        return this.currencyType;
    }

    public boolean getIsLoaned() {

        return this.isLoaned;
    }

    public String getDealership_id() {

        return dealership_id;
    }

    public String getVehicle_type() {

        return vehicle_type;
    }

    public String getVehicle_manufacturer() {

        return vehicle_manufacturer;
    }

    public String getVehicle_model() {

        return vehicle_model;
    }

    public String getVehicle_id() {

        return vehicle_id;
    }

    public int getPrice() {

        return price;
    }

    public long getAcquisition_date() {

        return acquisition_date;
    }

    public String toString(){

        String someString =  "Car ID: " + this.getVehicle_id() + " | Car Price: " + this.getPrice() + " | Car Acquisition Date: " + Instant.ofEpochMilli(this.getAcquisition_date()) + " | vehicle type: " + this.getVehicle_type() + " | vehicle manufacturer: " + this.getVehicle_manufacturer() + " | vehicle model: " + this.getVehicle_model();
        return someString;
    }
}
