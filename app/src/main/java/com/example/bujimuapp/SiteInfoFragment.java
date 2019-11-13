package com.example.bujimuapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bujimuapp.models.FarmInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiteInfoFragment extends Fragment {

    private static final String TAG = "Hello Boy";

    public SiteInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site_info2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            SiteInfoFragmentArgs args = SiteInfoFragmentArgs.fromBundle(getArguments());
            FarmInfo info = args.getFarmInfo();
            Log.i(TAG, "onViewCreated: " + info.getName());
        }

    }
}
