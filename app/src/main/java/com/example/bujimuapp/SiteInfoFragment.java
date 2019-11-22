package com.example.bujimuapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bujimuapp.models.FarmInfo;
import com.example.bujimuapp.models.SiteInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiteInfoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Hello Boy";
    private String area;
    private String location;
    private String slopeNature;
    private String soilNature;
    private String degrade;
    private String antHill;
    private String footPath;
    private String tree;
    private String currentCover;
    private String cropType;
    private String vegCover;
    private String dominantCover;
    private FarmInfo info;

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
            info = args.getFarmInfo();
        }

        Spinner areaSpinner = view.findViewById(R.id.sampled_area);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.sample_area,
                R.layout.spinner_item);
        areaSpinner.setAdapter(adapter);
        area = areaSpinner.getSelectedItem().toString();
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner locationSpinner = view.findViewById(R.id.slope_location);
        ArrayAdapter locationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.field_location, R.layout.spinner_item);
        locationSpinner.setAdapter(locationAdapter);
        location = locationSpinner.getSelectedItem().toString();
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner slopeNatureSpinner = view.findViewById(R.id.slope_nature);
        ArrayAdapter slopeAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.slope_nature, R.layout.spinner_item);
        slopeNatureSpinner.setAdapter(slopeAdapter);
        slopeNature = slopeNatureSpinner.getSelectedItem().toString();
        slopeNatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slopeNature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner soilNatureSpinner = view.findViewById(R.id.soil_nature);
        ArrayAdapter soilNatureAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.soil_nature, R.layout.spinner_item);
        soilNatureSpinner.setAdapter(soilNatureAdapter);
        soilNature = soilNatureSpinner.getSelectedItem().toString();
        soilNatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                soilNature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner degradeSpinner = view.findViewById(R.id.soil_degradation);
        ArrayAdapter degradeAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.degradation_signs, R.layout.spinner_item);
        degradeSpinner.setAdapter(degradeAdapter);
        degrade = degradeSpinner.getSelectedItem().toString();
        degradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degrade = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner antHillSpinner = view.findViewById(R.id.anthill_number);
        ArrayAdapter antHillAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.anthills, R.layout.spinner_item);
        antHillSpinner.setAdapter(antHillAdapter);
        antHill = antHillSpinner.getSelectedItem().toString();
        antHillSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                antHill = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner footPathSpinner = view.findViewById(R.id.footpaths);
        ArrayAdapter footPathAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.foot_paths, R.layout.spinner_item);
        footPathSpinner.setAdapter(footPathAdapter);
        footPath = antHillSpinner.getSelectedItem().toString();
        footPathSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                footPath = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner treeSpinner = view.findViewById(R.id.big_trees);
        ArrayAdapter treeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.big_trees,
                R.layout.spinner_item);
        treeSpinner.setAdapter(treeAdapter);
        tree = antHillSpinner.getSelectedItem().toString();
        treeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tree = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner currentCoverSpinner = view.findViewById(R.id.current_cover);
        ArrayAdapter currentCoverAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.current_cover, R.layout.spinner_item);
        currentCoverSpinner.setAdapter(currentCoverAdapter);
        currentCover = currentCoverSpinner.getSelectedItem().toString();
        currentCoverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCover = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner cropTypeSpinner = view.findViewById(R.id.crop_type);
        ArrayAdapter cropTypeAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.crop_type, R.layout.spinner_item);
        cropTypeSpinner.setAdapter(cropTypeAdapter);
        cropType = cropTypeSpinner.getSelectedItem().toString();
        cropTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cropType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner vegCoverSpinner = view.findViewById(R.id.vegetation_cover);
        ArrayAdapter vegCoverAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.vegetation_cover, R.layout.spinner_item);
        vegCoverSpinner.setAdapter(vegCoverAdapter);
        vegCover = vegCoverSpinner.getSelectedItem().toString();
        vegCoverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vegCover = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dominantCoverSpinner = view.findViewById(R.id.dominant_cover);
        ArrayAdapter dominantCoverAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.dominant_cover, R.layout.spinner_item);
        dominantCoverSpinner.setAdapter(dominantCoverAdapter);
        dominantCover = dominantCoverSpinner.getSelectedItem().toString();
        dominantCoverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dominantCover = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button saveButton = view.findViewById(R.id.save_site);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final NavController controller = Navigation.findNavController(view);
        SiteInfo siteInfo = new SiteInfo(info, area, location, slopeNature, soilNature, degrade,
                antHill, footPath, tree, currentCover, cropType, vegCover, dominantCover);
        SiteInfoFragmentDirections.ActionSiteInfo3ToSiteHistory
                actions = SiteInfoFragmentDirections.actionSiteInfo3ToSiteHistory(siteInfo);
        controller.navigate(actions);
    }
}
