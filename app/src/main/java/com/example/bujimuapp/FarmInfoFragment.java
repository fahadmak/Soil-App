package com.example.bujimuapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bujimuapp.models.FarmInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class FarmInfoFragment extends Fragment {


    public FarmInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farm_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText nameText = view.findViewById(R.id.name);
        final EditText districtText = view.findViewById(R.id.district);
        final EditText subCountyText = view.findViewById(R.id.subCounty);
        final EditText parishText = view.findViewById(R.id.parish);
        final EditText villageText = view.findViewById(R.id.village);
        final EditText reasonText = view.findViewById(R.id.reasonForAnalysis);

        final NavController controller = Navigation.findNavController(view);

        view.findViewById(R.id.saveFarmer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String district = districtText.getText().toString();
                String subCounty = subCountyText.getText().toString();
                String parish = parishText.getText().toString();
                String village = villageText.getText().toString();
                String reason = reasonText.getText().toString();
                if (name.equals("") &&
                        district.equals("") &&
                        subCounty.equals("") &&
                        parish.equals("") &&
                        village.equals("") &&
                        reason.equals("")) {
                    Toast.makeText(getActivity(), "Fill in missing fields", Toast.LENGTH_SHORT).show();
                } else {
                    FarmInfo info = new FarmInfo(name,
                            district, subCounty, parish, village, reason);
                    FarmInfoFragmentDirections.ActionFarmInfoToSiteInfo3 action =
                            FarmInfoFragmentDirections.actionFarmInfoToSiteInfo3(info);
                    controller.navigate(action);
                }
            }
        });

    }
}
