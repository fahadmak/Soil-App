package com.example.bujimuapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bujimuapp.models.SiteInfo;
import com.example.bujimuapp.models.SoilAnalysis;
import com.example.bujimuapp.ui.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Date;

import static com.example.bujimuapp.AppConfig.NITROGEN_REC;
import static com.example.bujimuapp.AppConfig.NITROGEN_STATE;
import static com.example.bujimuapp.AppConfig.PHOSPHOROUS_REC;
import static com.example.bujimuapp.AppConfig.PHOSPHOROUS_STATE;
import static com.example.bujimuapp.AppConfig.PH_REC;
import static com.example.bujimuapp.Constants.MYPREF;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoilAnalysisFragment extends Fragment {

    private String cropName;
    private String organicLevel;
    private CardView nitrogenCard;
    private CardView phosphorousCard;
    private CardView phCard;
    private TextView nitrogenText;
    private TextView phosphorousText;
    private TextView phText;
    private Button recommendationButton;
    private SoilAnalysis soilAnalysis;
    private SiteInfo siteInfo;
    private String recommendation = "";

    SharedPreferences pref;

    private SoilAnalysisViewModel soilAnalysisViewModel;

    static final int COLOR_REQUEST_CODE = 1;  // The request code

    public SoilAnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soil_analysis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            SoilAnalysisFragmentArgs args = SoilAnalysisFragmentArgs.fromBundle(getArguments());
            siteInfo = args.getSiteInfo();
        }
        soilAnalysisViewModel = ViewModelProviders.of(requireActivity()).get(SoilAnalysisViewModel.class);
        Spinner cropSpinner = view.findViewById(R.id.crop_grown);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.crop_names,
                R.layout.spinner_item);
        cropSpinner.setAdapter(adapter);
        cropName = cropSpinner.getSelectedItem().toString();
        cropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cropName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner organicSpinner = view.findViewById(R.id.organic_spinner);
        ArrayAdapter organicAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.oragnic_levels,
                R.layout.spinner_item);
        organicSpinner.setAdapter(organicAdapter);
        organicLevel = organicSpinner.getSelectedItem().toString();
        organicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                organicLevel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nitrogenText = view.findViewById(R.id.nitrogen_text);

        nitrogenCard = view.findViewById(R.id.nitrogen_card_view);

        nitrogenCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NitrogenCameraActivity.class);
                startActivityForResult(intent, COLOR_REQUEST_CODE);
            }
        });

        phosphorousText = view.findViewById(R.id.phosphorous_text);

        phosphorousCard = view.findViewById(R.id.phosphorous_card_view);

        phosphorousCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhosphorousCameraActivity.class);
                startActivityForResult(intent, COLOR_REQUEST_CODE);
            }
        });

        phText = view.findViewById(R.id.ph_text);

        phCard = view.findViewById(R.id.ph_card_view);

        phCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhCameraActivity.class);
                startActivityForResult(intent, COLOR_REQUEST_CODE);
            }
        });
        recommendationButton = view.findViewById(R.id.recommend);

        recommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recommendation.equals("")) {
                    Toast.makeText(getActivity(), "No Analysis Done", Toast.LENGTH_SHORT).show();
                    return;
                }
                pref = getContext().getSharedPreferences(MYPREF, 0);
                long userId = pref.getLong("userId", 1);
                soilAnalysis = new SoilAnalysis(siteInfo, cropName,
                        recommendation, new Date(), userId);
                new MaterialAlertDialogBuilder(getActivity()).setTitle("Fertilizer recommendation")
                        .setMessage(recommendation)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                soilAnalysisViewModel.insert(soilAnalysis);
                                Toast.makeText(getActivity(), "Analysis Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (COLOR_REQUEST_CODE) : {
                if (resultCode == AppConfig.NITROGEN_COLOR_CODE) {
                    Bundle bundle = data.getExtras();
                    int red = bundle.getInt(AppConfig.COLOR_RED);
                    int blue = bundle.getInt(AppConfig.COLOR_BLUE);
                    int green = bundle.getInt(AppConfig.COLOR_GREEN);
                    // TODO Update your TextView.
                    int color = Color.rgb(red, blue, green);
                    Log.d("Batman", "onActivityResult: " + Color.blue(color));
                    recommendation += bundle.getString(NITROGEN_REC);
                    nitrogenCard.setCardBackgroundColor(color);
                    nitrogenText.setTextColor(Color.parseColor("#ffffff"));
                } else if (resultCode == AppConfig.PHOSPHOROUS_COLOR_CODE) {
                    Bundle bundle = data.getExtras();
                    int red = bundle.getInt(AppConfig.COLOR_RED);
                    int blue = bundle.getInt(AppConfig.COLOR_BLUE);
                    int green = bundle.getInt(AppConfig.COLOR_GREEN);
                    // TODO Update your TextView.
                    recommendation += bundle.getString(PHOSPHOROUS_REC);
                    int color = Color.rgb(red, blue, green);
                    phosphorousCard.setCardBackgroundColor(color);
                    phosphorousText.setTextColor(Color.parseColor("#ffffff"));
                } else if (resultCode == AppConfig.PH_COLOR_CODE) {
                    Bundle bundle = data.getExtras();
                    int red = bundle.getInt(AppConfig.COLOR_RED);
                    int blue = bundle.getInt(AppConfig.COLOR_BLUE);
                    int green = bundle.getInt(AppConfig.COLOR_GREEN);
                    // TODO Update your TextView.
                    recommendation += bundle.getString(PH_REC);
                    int color = Color.rgb(red, blue, green);
                    Log.d("Batman", "onActivityResult: " + Color.blue(color));
                    phCard.setCardBackgroundColor(color);
                    phText.setTextColor(Color.parseColor("#ffffff"));
                }
                break;
            }
        }
    }
}
