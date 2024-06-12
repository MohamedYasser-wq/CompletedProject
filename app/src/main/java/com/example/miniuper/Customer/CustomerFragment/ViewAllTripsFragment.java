package com.example.miniuper.Customer.CustomerFragment;

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

import com.example.miniuper.Customer.CustomerViewModel;
import com.example.miniuper.Data.TripModel;
import com.example.miniuper.Presentetion.RecTrip;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentViewAllTripsBinding;

import java.util.ArrayList;

public class ViewAllTripsFragment extends Fragment {
    private FragmentViewAllTripsBinding binding;
    private NavController navController;

    CustomerViewModel customerViewModel;
    RecTrip recTrip;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentViewAllTripsBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        customerViewModel=new ViewModelProvider(this).get(CustomerViewModel.class);

        GetAllData();

        return binding.getRoot();
    }

    public void GetAllData(){
        customerViewModel.ViewHistoryTrip();
        customerViewModel.ErrorViewAllTripLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        customerViewModel.ViewAllTripLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
            @Override
            public void onChanged(ArrayList<TripModel> tripModels) {
                SetAdapter(tripModels);
            }

        });



    }
    private void SetAdapter(ArrayList<TripModel> tripModels) {
        recTrip=new RecTrip(tripModels);
        binding.RecViewAllTrip.setAdapter(recTrip);
    }

}