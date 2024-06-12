package com.example.miniuper.Customer.CustomerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Customer.CustomerHelper;
import com.example.miniuper.Customer.CustomerViewModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.Data.TripModel;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentAddTripBinding;

import java.util.Date;

public class AddTripFragment extends Fragment {

    private FragmentAddTripBinding binding;
    private NavController navController;

    CustomerViewModel customerViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding= FragmentAddTripBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        customerViewModel=new ViewModelProvider(this).get(CustomerViewModel.class);
        // Inflate the layout for this fragment

        binding.EdtTime.setText(SharedPrefrence.get_Date());

        binding.EdtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_addTripFragment_to_calenderFragment);
            }
        });
        binding.BtnBookCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Destination=binding.EdtDestinationPoint.getText().toString();
                String Start=binding.EdtPickubPoint.getText().toString();
                String Date=binding.EdtTime.getText().toString();

                Validate(Start,Destination,Date);


            }

        });
            binding.btnBackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_addTripFragment_to_homeCustomerFragment);

                }
            });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_addTripFragment_to_splashFragment);

            }
        });

        return binding.getRoot();
    }
    public void Validate(String StartPoint, String Destination,String Date){
        if(StartPoint.equalsIgnoreCase("")||Destination.equalsIgnoreCase("")
             ||Date.equalsIgnoreCase("")){
            Toast.makeText(getContext(), "Enter All Fields", Toast.LENGTH_SHORT).show();

        }
        else {
            TripModel tripModel = new TripModel(StartPoint, Destination, Date);
            validate2(tripModel);
        }



    }

    private void validate2(TripModel tripModel) {

        customerViewModel.BookTrip(tripModel, new CustomerHelper.TripCallBack() {
            @Override
            public void Success() {

                Toast.makeText(getContext(), "Trip Booked Successfully!!!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_addTripFragment_to_homeCustomerFragment);
            }

            @Override
            public void Failure(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

            }
        });

    }


}