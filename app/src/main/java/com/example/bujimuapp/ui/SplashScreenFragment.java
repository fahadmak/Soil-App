package com.example.bujimuapp.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bujimuapp.R;

import static com.example.bujimuapp.Constants.EMAIL;
import static com.example.bujimuapp.Constants.LOGGED_IN;
import static com.example.bujimuapp.Constants.MYPREF;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {

    SharedPreferences pref;
    boolean loginStatus;
    String email;

    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                pref = getContext().getSharedPreferences(MYPREF, 0);
                loginStatus = pref.getBoolean(LOGGED_IN, false);
                if (!loginStatus) {
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_splash_screen_to_authFragment);;
                } else {
                    email = pref.getString(EMAIL, null);
                    Intent intent = new Intent(getActivity(), SoilActivity.class);
                    intent.putExtra(EMAIL, email);
                    startActivity(intent);
                }

            }
        }, 1500);
    }
}
