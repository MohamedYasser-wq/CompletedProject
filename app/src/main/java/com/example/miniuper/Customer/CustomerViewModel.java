package com.example.miniuper.Customer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.TripModel;

import java.util.ArrayList;

public class CustomerViewModel extends ViewModel {
public MutableLiveData<Boolean>BookTripLiveData=new MutableLiveData<>();
public MutableLiveData<String>ErrorBookTripLiveData=new MutableLiveData<>();

public MutableLiveData<ArrayList<TripModel>>ViewAllTripLiveData=new MutableLiveData<>();
public MutableLiveData<String>ErrorViewAllTripLiveData=new MutableLiveData<>();
public MutableLiveData<ArrayList<TripModel>>ViewAllAcceptedTripLiveData=new MutableLiveData<>();
public MutableLiveData<String>ErrorViewAllAcceptedTripLiveData=new MutableLiveData<>();

public MutableLiveData<Boolean>FeedBackTripLiveData=new MutableLiveData<>();
public MutableLiveData<String>ErrorFeedBackTripLiveData=new MutableLiveData<>();

 CustomerHelper customerHelper;

public CustomerViewModel(){

    customerHelper=new CustomerHelper();

}


public void BookTrip(TripModel tripModel, CustomerHelper.TripCallBack tripCallBack){

    customerHelper.BookTrip(tripModel, new CustomerHelper.TripCallBack() {
        @Override
        public void Success() {
            BookTripLiveData.setValue(true);
        }

        @Override
        public void Failure(String message) {
     ErrorBookTripLiveData.setValue(message);
        }
    });



}

public void ViewHistoryTrip(){

    customerHelper.ViewHistoryTrip(new CustomerHelper.ViewAllTripCallBack() {
        @Override
        public void Success(ArrayList<TripModel> list) {
            ViewAllTripLiveData.setValue(list);
        }

        @Override
        public void Failure(String message) {
            ErrorViewAllTripLiveData.setValue(message);

        }
    });



}

public void ViewAllAcceptedTrip(){

    customerHelper.ViewAllAcceptedTrip(new CustomerHelper.ViewAllTripCallBack() {
        @Override
        public void Success(ArrayList<TripModel> list) {
            ViewAllAcceptedTripLiveData.setValue(list);
        }

        @Override
        public void Failure(String message) {
            ErrorViewAllAcceptedTripLiveData.setValue(message);

        }
    });

}

public void Feedback(ComplainsModel complainsModel){

    customerHelper.FeedBackAcceptedTrip(complainsModel, new CustomerHelper.TripCallBack() {
        @Override
        public void Success() {
            FeedBackTripLiveData.setValue(true);
        }

        @Override
        public void Failure(String message) {
         ErrorFeedBackTripLiveData.setValue(message);
        }
    });

}

}
