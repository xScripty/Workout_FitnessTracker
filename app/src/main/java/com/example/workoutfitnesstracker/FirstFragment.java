package com.example.workoutfitnesstracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutfitnesstracker.databinding.FragmentFirstBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FirstFragment extends Fragment {


    private FragmentFirstBinding binding;
    SharedPreferences preferences;
    private WorkoutAdapter adapter;
    private ArrayList<Workout> workouts;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences=getActivity().getApplicationContext().getSharedPreferences("Userinfo",0);
        workouts=new ArrayList<Workout>();
        adapter=new WorkoutAdapter(getActivity().getApplicationContext(), workouts);
        binding.WorkoutsListview.setAdapter(adapter);

        String jsonText=preferences.getString("Workouts","[]");
        Workout[] workoutsObjs=new Gson().fromJson(jsonText,Workout[].class);
        for(Workout workout: workoutsObjs){
            workouts.add(workout);
        }

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}