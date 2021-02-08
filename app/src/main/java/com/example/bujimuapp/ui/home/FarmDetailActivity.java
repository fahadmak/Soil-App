package com.example.bujimuapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bujimuapp.R;
import com.example.bujimuapp.models.SiteInfo;

public class FarmDetailActivity extends AppCompatActivity {

    SiteInfo siteInfo;
    String crop;
    String date;
    String recommendation;

    TextView farm;
    TextView district;
    TextView subCounty;
    TextView parish;
    TextView village;
    TextView fieldArea;
    TextView fieldLocation;
    TextView slopeNature;
    TextView soilNature;
    TextView currentCover;
    TextView cropType;
    TextView vegCover;
    TextView reasonForAnalysis;
    TextView reccomendation;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle analysis = intent.getExtras();

        if (analysis != null) {
            siteInfo = analysis.getParcelable("site_info");
            crop = analysis.getString("crop_grown");
            date = analysis.getString("date");
            recommendation = analysis.getString("recommendation");

            dateText = findViewById(R.id.date);
            dateText.setText(date);

            farm = findViewById(R.id.farm_name);
            farm.setText(siteInfo.getName());

            district = findViewById(R.id.district);
            district.setText(siteInfo.getDistrict());

            subCounty = findViewById(R.id.sub_county);
            subCounty.setText(siteInfo.getSubCounty());

            parish = findViewById(R.id.parish);
            parish.setText(siteInfo.getParish());

            village = findViewById(R.id.village);
            village.setText(siteInfo.getVilage());

            fieldArea = findViewById(R.id.field_area);
            fieldArea.setText(siteInfo.getArea());

            fieldLocation = findViewById(R.id.slope_location);
            fieldLocation.setText(siteInfo.getLocation());

            slopeNature = findViewById(R.id.slope_nature);
            slopeNature.setText(siteInfo.getSlopeNature());

            soilNature = findViewById(R.id.soil_nature);
            soilNature.setText(siteInfo.getSoilNature());

            currentCover = findViewById(R.id.current_cover);
            currentCover.setText(siteInfo.getCurrentCover());

            cropType = findViewById(R.id.type_of_crop);
            cropType.setText(siteInfo.getCropType());

            vegCover = findViewById(R.id.vegetation_cover);
            vegCover.setText(siteInfo.getVegCover());

            reasonForAnalysis = findViewById(R.id.analysis);
            reasonForAnalysis.setText(siteInfo.getReason());

            reccomendation = findViewById(R.id.recommendation);
            reccomendation.setText(recommendation);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
