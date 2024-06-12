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
import com.example.miniuper.Driver.DriverHelper;
import com.example.miniuper.Driver.DriverViewModel;
import com.example.miniuper.Presentetion.OnClickItem;
import com.example.miniuper.Presentetion.Rec_SearchTrip;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentSearchTripBinding;

import java.util.ArrayList;

public class SearchTripFragment extends Fragment implements OnClickItem {
    private FragmentSearchTripBinding binding;
    private NavController navController;
    private Rec_SearchTrip recSearchTrip;
    private ArrayList<TripModel> tripModels1 = new ArrayList<>();
    private DriverViewModel driverViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchTripBinding.inflate(inflater);
        navController = Navigation.findNavController(container);
        driverViewModel = new ViewModelProvider(this).get(DriverViewModel.class);

        ViewTripToChoose();

        return binding.getRoot();
    }

    public void ViewTripToChoose() {
        driverViewModel.SearchTrip();
        driverViewModel.SearchTripLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
            @Override
            public void onChanged(ArrayList<TripModel> tripModels) {

                     if(!(tripModels.size()==0)){
                         binding.textView5.setVisibility(View.INVISIBLE);

                         tripModels1.clear();
                         tripModels1.addAll(tripModels);
                         if (recSearchTrip == null)
                         {
                             recSearchTrip = new Rec_SearchTrip(tripModels, SearchTripFragment.this);
                             binding.SearchTrip.setAdapter(recSearchTrip);
                         }
                         else
                         {
                             recSearchTrip.notifyDataSetChanged();
                         }

                     }
                     else
                     {
                         binding.textView5.setVisibility(View.VISIBLE);
                     }
            }
        });

        driverViewModel.ErrorHandleTripLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(int position) {
        TripModel selectedTrip = tripModels1.get(position);
        driverViewModel.HandleTrips(selectedTrip, selectedTrip.getId(), new DriverHelper.HandleAllTripCallBack() {
            @Override
            public void Success() {
                tripModels1.remove(position);
                recSearchTrip.notifyItemRemoved(position);
                recSearchTrip.notifyItemRangeChanged(position, tripModels1.size());


            }

            @Override
            public void Failure(String message) {
                Toast.makeText(getContext(), "Fail: " + message, Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getContext(), "Assigned Successfully!!", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_searchTripFragment_to_homeDriverFragment);
    }
}
