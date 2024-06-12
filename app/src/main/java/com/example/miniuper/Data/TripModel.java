package com.example.miniuper.Data;

public class TripModel {


    private String StartingPoint;
    private String DestinationPoint;
    private String Time;
    private String id;

    public String getStartingPoint() {
        return StartingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        StartingPoint = startingPoint;
    }

    public String getDestinationPoint() {
        return DestinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        DestinationPoint = destinationPoint;
    }

    public TripModel() {
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TripModel(String startingPoint, String destinationPoint, String time) {
        StartingPoint = startingPoint;
        DestinationPoint = destinationPoint;
        Time = time;
    }
}
