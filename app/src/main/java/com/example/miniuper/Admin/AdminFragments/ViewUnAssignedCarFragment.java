package com.example.miniuper.Admin.AdminFragments;

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

import com.example.miniuper.Admin.AdminViewModel;
import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.Presentetion.OnClickItem;
import com.example.miniuper.Presentetion.RecAllCar;

import com.example.miniuper.R;

import java.util.ArrayList;


public class ViewUnAssignedCarFragment extends Fragment implements OnClickItem {

    private com.example.miniuper.databinding.FragmentViewUnAssignedCarBinding binding;
    private NavController navController;
    AdminViewModel adminViewModel;
    ArrayList<CarModel>list2=new ArrayList<>();
    RecAllCar recAllCar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= com.example.miniuper.databinding.FragmentViewUnAssignedCarBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        adminViewModel=new ViewModelProvider(this).get(AdminViewModel.class);
        // Inflate the layout for this fragment

        ViewCars();

        return binding.getRoot();
    }

    public void ViewCars(){

        adminViewModel.ViewCars();

       adminViewModel.CarsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<CarModel>>() {
           @Override
           public void onChanged(ArrayList<CarModel> list) {
               list2.addAll(list);
               Log.e("TAG", "onChanged: "+list2.size());

               recAllCar=new RecAllCar(list,ViewUnAssignedCarFragment.this);
               binding.RecAllCar.setAdapter(recAllCar);
           }
       });
       adminViewModel.CarErrorMutableData.observe(getViewLifecycleOwner(), new Observer<String>() {
           @Override
           public void onChanged(String s) {
               Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
           }
       });





    }


    @Override
    public void onClick(int id) {

        SharedPrefrence.setCar_color(list2.get(id).getColor());
        SharedPrefrence.setCar_PlateNumber(list2.get(id).getPlateNumber());
        SharedPrefrence.set_Car_Year(list2.get(id).getYear());
        SharedPrefrence.setCarModel(list2.get(id).getModel());

navController.navigate(R.id.action_viewUnAssignedCarFragment_to_addDriverFragment);


    }
}