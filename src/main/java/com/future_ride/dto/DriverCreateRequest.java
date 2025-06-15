package com.future_ride.dto;

public class DriverCreateRequest {
    private String name;
    private String phoneNumber;
    private String vehicleNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleNumber(){
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }

}
