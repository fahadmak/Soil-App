package com.example.bujimuapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

public class SoilAnalysisViewModel extends AndroidViewModel {

    private SoilAnalysisRepository mRepository;

    private LiveData<List<SoilAnalysis>> mAllSoilAnalysis;

    public SoilAnalysisViewModel(Application application) {
        super(application);
        mRepository = new SoilAnalysisRepository(application);
        mAllSoilAnalysis = mRepository.getAllSoilAnalysis();
    }

    public void insert(SoilAnalysis soilAnalysis) {
        mRepository.insert(soilAnalysis);
    }
}
