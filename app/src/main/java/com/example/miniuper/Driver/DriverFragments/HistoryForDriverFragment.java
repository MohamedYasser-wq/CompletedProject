package com.example.miniuper.Driver.DriverFragments;

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

import com.example.miniuper.Data.TripModel;
import com.example.miniuper.Driver.DriverViewModel;
import com.example.miniuper.Presentetion.RecTrip;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentHistoryForDriverBinding;
import com.example.miniuper.databinding.FragmentHomeDriverBinding;

import java.util.ArrayList;

public class HistoryForDriverFragment extends Fragment {
    private FragmentHistoryForDriverBinding binding;
    private NavController navController;
    private RecTrip recTrip;
    private DriverViewModel driverViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHistoryForDriverBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        driverViewModel=new ViewModelProvider(this).get(DriverViewModel.class);
        // Inflate the layout for this fragment
        ViewHistory();
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_historyForDriverFragment_to_homeDriverFragment);
            }
        });

        return binding.getRoot();

    }

    public void ViewHistory(){

         driverViewModel.ViewHistory();
         driverViewModel.HistoryTripLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
             @Override
             public void onChanged(ArrayList<TripModel> tripModels) {

                 if(!(tripModels.size()==0)) {
                     binding.textView7.setVisibility(View.INVISIBLE);
                     recTrip = new RecTrip(tripModels);
                     binding.RecHistory.setAdapter(recTrip);

                 }
                 else
                 {
                     binding.textView7.setVisibility(View.VISIBLE);
                 }
             }
         });

         driverViewModel.ErrorHistoryTripLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
             @Override
             public void onChanged(String s) {
                 Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
             }
         });



    }
}