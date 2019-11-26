package com.example.bujimuapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bujimuapp.dao.SoilAnalysisDao;
import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

public class SoilAnalysisRepository {
    private SoilAnalysisDao mSoilAnalysisDao;
    private LiveData<List<SoilAnalysis>> mAllSoilAnalysis;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public SoilAnalysisRepository(Application application) {
        SoilAnalysisDatabase db = SoilAnalysisDatabase.getDatabase(application);
        mSoilAnalysisDao = db.soilAnalysisDao();
        mAllSoilAnalysis = mSoilAnalysisDao.getAllSoilAnalysis();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<SoilAnalysis>> getAllSoilAnalysis() {
        return mAllSoilAnalysis;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(SoilAnalysis soilAnalysis) {
        SoilAnalysisDatabase.databaseWriteExecutor.execute(() -> {
            mSoilAnalysisDao.insert(soilAnalysis);
        });
    }
}
