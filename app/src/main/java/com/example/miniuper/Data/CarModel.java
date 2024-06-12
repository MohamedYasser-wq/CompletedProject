package com.example.miniuper.Data;

public class CarModel {



    private String color;


    public CarModel() {
    }

    private String year;
    private String model;
    private String plateNumber;

    public CarModel(String color, String year, String model, String plateNumber) {
        this.color = color;
        this.year = year;
        this.model = model;
        this.plateNumber = plateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

}
