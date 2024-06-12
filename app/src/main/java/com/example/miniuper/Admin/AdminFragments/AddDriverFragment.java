package com.example.miniuper.Admin.AdminFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Admin.AdminViewModel;
import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.DriverModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentAddDriverBinding;

public class AddDriverFragment extends Fragment {

    private FragmentAddDriverBinding binding;
    private NavController navController;
    AdminViewModel adminViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= com.example.miniuper.databinding.FragmentAddDriverBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        adminViewModel=new ViewModelProvider(this).get(AdminViewModel.class);

               SetData();

        binding.btnChooseCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          navController.navigate(R.id.action_addDriverFragment_to_viewUnAssignedCarFragment);

            }
        });
        binding.BtnAddNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.EdtDriverName.getText().toString();
                String email=binding.EdtDriverEmail.getText().toString();
                String phone=binding.EdtDriverPhone.getText().toString();
                String pass=binding.EdtDriverPassword.getText().toString();

                Validate(name,email,phone,pass);
            }
        });
        binding.btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_addDriverFragment_to_homeEmployeeFragment);

            }
        });





        return binding.getRoot();
    }



   public void SetData(){
        binding.EdtDriverEmail.setText(SharedPrefrence.get_Driver_Email());
       binding.EdtDriverName.setText(SharedPrefrence.getDriverName());
       binding.EdtDriverPhone.setText(SharedPrefrence.get_Driver_Phone());
       binding.EdtDriverPassword.setText(SharedPrefrence.getDriver_Password());

    }

    public void Validate(String name,String email,String phone,String pass){

        if(name.equalsIgnoreCase("")||email.equalsIgnoreCase("")||
                phone.equalsIgnoreCase("")||pass.equalsIgnoreCase("")){

            Toast.makeText(getContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();

        }
        else {
            if(pass.length()<6){
                Toast.makeText(getContext(), "Pass Should equal 6 number or more", Toast.LENGTH_SHORT).show();

            } else if (!(email.contains("@"))) {
                Toast.makeText(getContext(), "Email Should Contain @", Toast.LENGTH_SHORT).show();

            } else if (!(phone.length()==11)) {

                Toast.makeText(getContext(), "Phone Should be 11 number", Toast.LENGTH_SHORT).show();

            }
            else {
                CarModel carModel=new CarModel(SharedPrefrence.getCar_Color(),SharedPrefrence.get_Car_Year(),SharedPrefrence.getCarModel(),SharedPrefrence.getCar_PlateNumber());
                DriverModel driverModel=new DriverModel();
                driverModel.setEmail(email);
                driverModel.setName(name);
                driverModel.setPassword(pass);
                driverModel.setPhone(phone);
                driverModel.setCar(carModel);

                adminViewModel.AddNewDriver(driverModel);
               Validate2();
            }

        }


    }

    private void Validate2() {

        adminViewModel.DriverMutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(getContext(), "Driver is Added!!", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_addDriverFragment_to_homeEmployeeFragment);
                }
            }
        });
        adminViewModel.DriverErrorMutableData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
            }
        });



    }

}