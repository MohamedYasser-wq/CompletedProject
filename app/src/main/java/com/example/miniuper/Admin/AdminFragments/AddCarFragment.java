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
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentAddCarBinding;

public class AddCarFragment extends Fragment {

    private com.example.miniuper.databinding.FragmentAddCarBinding binding;
    private NavController navController;
    AdminViewModel adminViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= com.example.miniuper.databinding.FragmentAddCarBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        adminViewModel=new ViewModelProvider(this).get(AdminViewModel.class);


        binding.btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_addCarFragment_to_homeEmployeeFragment2);
            }
        });

        binding.BtnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model =binding.EdtModel.getText().toString();
                String Year =binding.EdtYear.getText().toString();
                String color =binding.EdtColor.getText().toString();
                String PlateNumber =binding.EdtPlanetNumber.getText().toString();

                Validate(model,Year,color,PlateNumber);
            }
        });

        return binding.getRoot();
    }

    public void Validate(String model,String Year,String color,String PlanetNumber){

        if(model.equalsIgnoreCase("")||color.equalsIgnoreCase("")||
                Year.equalsIgnoreCase("")||PlanetNumber.equalsIgnoreCase(""))
        {

            Toast.makeText(getContext(), "Please Enter All Field", Toast.LENGTH_SHORT).show();

        }else
        {
            if(!(PlanetNumber.length()==4))
            {
                Toast.makeText(getContext(), "Plant Number should be 4 Number", Toast.LENGTH_SHORT).show();
            }
            else if (!(Year.length()==4))
            {
                Toast.makeText(getContext(), "Year should be 4 Number", Toast.LENGTH_SHORT).show();
            }
            else {
                CarModel carModel=new CarModel(color,Year,model,PlanetNumber);
                adminViewModel.AddNewCar(carModel);
                Validate2();
            }

        }





    }

    private void Validate2() {
        adminViewModel.mutableLiveData1.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(getContext(), "Car Added Successfully!!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_addCarFragment_to_homeEmployeeFragment2);
            }
        });
        adminViewModel.mutableLiveData2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });


    }

}