package com.example.miniuper.Customer.CustomerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Customer.CustomerViewModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.Data.TripModel;
import com.example.miniuper.Presentetion.OnClickItem;
import com.example.miniuper.Presentetion.Rec_Feedback;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentViewAcceptedTripBinding;

import java.util.ArrayList;


public class ViewAcceptedTripFragment extends Fragment implements OnClickItem {
    private FragmentViewAcceptedTripBinding binding;
    private NavController navController;
    private Rec_Feedback rec;
    private CustomerViewModel customerViewModel;
    ArrayList<TripModel>list=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentViewAcceptedTripBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        customerViewModel=new ViewModelProvider(this).get(CustomerViewModel.class);

        ViewAllAcceptedTrip();

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_viewAcceptedTripFragment_to_homeCustomerFragment);
            }
        });

        return binding.getRoot();
    }

    public void ViewAllAcceptedTrip(){

        customerViewModel.ViewAllAcceptedTrip();
        customerViewModel.ViewAllAcceptedTripLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
            @Override
            public void onChanged(ArrayList<TripModel> tripModels) {
                list.addAll(tripModels);

                SetAdapter(tripModels);


            }
        });
        customerViewModel.ErrorViewAllAcceptedTripLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

            }
        });






    }

    private void SetAdapter(ArrayList<TripModel> tripModels) {
        rec=new Rec_Feedback(tripModels,this);
        binding.RecViewAcceptedAllTrip.setAdapter(rec);


    }

    @Override
    public void onClick(int id) {

        SharedPrefrence.set_Driver_id(list.get(id).getId());
        navController.navigate(R.id.action_viewAcceptedTripFragment_to_feedbackFragment);

    }
}