package com.example.miniuper.Admin;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.DriverModel;

import java.util.ArrayList;

public class AdminViewModel extends ViewModel {

    private AdminHelper adminHelper;
    public MutableLiveData<Boolean>mutableLiveData1=new MutableLiveData<>();
    public MutableLiveData<String>mutableLiveData2=new MutableLiveData<>();

    public MutableLiveData<ArrayList<CarModel>>CarsMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<String>CarErrorMutableData=new MutableLiveData<>();


    public MutableLiveData<Boolean>DriverMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<String>DriverErrorMutableData=new MutableLiveData<>();

    public MutableLiveData<ArrayList<ComplainsModel>>FeedBackDriverMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<String>FeedBackDriverErrorMutableData=new MutableLiveData<>();

    AdminViewModel(){

        adminHelper=new AdminHelper();
    }

    public void AddNewCar(CarModel car){

        adminHelper.AddNewCar(car, new AdminHelper.AdminCallback() {
            @Override
            public void OnSuccess() {
                mutableLiveData1.setValue(true);

            }

            @Override
            public void OnFailure(String message) {
               mutableLiveData2.setValue(message);
            }
        });

    }

    public void ViewCars(){

        CarsMutableLiveData.setValue(adminHelper.GetAllCar(new AdminHelper.CarCallback() {
            @Override
            public void OnSuccess(ArrayList<CarModel> list) {

                CarsMutableLiveData.setValue(list);
            }

            @Override
            public void OnFailure(String message) {
                CarErrorMutableData.setValue(message);

            }
        }));

    }

    public void AddNewDriver(DriverModel driver){

        adminHelper.AddNewDriverAuth(driver, new AdminHelper.AdminCallback() {
            @Override
            public void OnSuccess() {
                DriverMutableLiveData.setValue(true);
            }

            @Override
            public void OnFailure(String message) {
                DriverErrorMutableData.setValue(message);

            }
        });




    }

    public void ViewAllFeedBack(){
         adminHelper.ViewAllFeedback(new AdminHelper.FeedBcakCallback() {
             @Override
             public void OnSuccess(ArrayList<ComplainsModel> list) {
                 FeedBackDriverMutableLiveData.setValue(list);
             }

             @Override
             public void OnFailure(String message) {
                 FeedBackDriverErrorMutableData.setValue(message);
             }
         });




    }




}
