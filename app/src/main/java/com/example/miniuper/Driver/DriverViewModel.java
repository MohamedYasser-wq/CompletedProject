package com.example.miniuper.Driver;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniuper.Customer.CustomerHelper;
import com.example.miniuper.Data.TripModel;

import java.util.ArrayList;

public class DriverViewModel  extends ViewModel {

    public MutableLiveData<Boolean> HandleTripLiveData=new MutableLiveData<>();
    public MutableLiveData<String>ErrorHandleTripLiveData=new MutableLiveData<>();

    public MutableLiveData<ArrayList<TripModel>>SearchTripLiveData=new MutableLiveData<>();
    public MutableLiveData<String>ErrorViewSearchTripLiveData=new MutableLiveData<>();

    public MutableLiveData<ArrayList<TripModel>>HistoryTripLiveData=new MutableLiveData<>();
    public MutableLiveData<String>ErrorHistoryTripLiveData=new MutableLiveData<>();
    DriverHelper driverHelper;

    public DriverViewModel(){

        driverHelper=new DriverHelper();

    }

    public void SearchTrip(){

        driverHelper.ViewAllTrip(new DriverHelper.SearchAllTripCallBack() {
            @Override
            public void Success(ArrayList<TripModel> list) {
                SearchTripLiveData.setValue(list);
            }

            @Override
            public void Failure(String message) {
                ErrorViewSearchTripLiveData.setValue(message);
            }
        });





    }
    public void HandleTrips(TripModel tripModel, String id, DriverHelper.HandleAllTripCallBack handleAllTripCallBack){

        driverHelper.AcceptTrip(tripModel, id, new DriverHelper.HandleAllTripCallBack() {
            @Override
            public void Success() {
                HandleTripLiveData.setValue(true);
            }

            @Override
            public void Failure(String message) {
                ErrorHandleTripLiveData.setValue(message);

            }
        });

    }

    public void ViewHistory(){



        driverHelper.ViewHistory(new DriverHelper.SearchAllTripCallBack() {
            @Override
            public void Success(ArrayList<TripModel> list) {

                HistoryTripLiveData.setValue(list);
            }

            @Override
            public void Failure(String message) {
             ErrorHistoryTripLiveData.setValue(message);
            }
        });



    }

}
