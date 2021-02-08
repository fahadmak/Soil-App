package com.example.bujimuapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bujimuapp.dao.SoilAnalysisDao;
import com.example.bujimuapp.dao.UserDao;
import com.example.bujimuapp.models.SoilAnalysis;
import com.example.bujimuapp.models.User;

import java.util.List;

public class SoilAnalysisRepository {
    private SoilAnalysisDao mSoilAnalysisDao;
    private LiveData<List<SoilAnalysis>> mAllSoilAnalysis;

    private UserDao userDao;

    private LiveData<User> userLiveData;

    public SoilAnalysisRepository(Application application) {
        SoilAnalysisDatabase db = SoilAnalysisDatabase.getDatabase(application);
        mSoilAnalysisDao = db.soilAnalysisDao();
        mAllSoilAnalysis = mSoilAnalysisDao.getAllSoilAnalysis();
        userDao = db.userDao();
    }

    public LiveData<User> getUserLiveData(String email, String password) {
        return userDao.isValidUser(email, password);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<SoilAnalysis>> getAllSoilAnalysis() {
        return mAllSoilAnalysis;
    }

    public LiveData<List<SoilAnalysis>> getUserAnalyses(long userId) {
        return mSoilAnalysisDao.findAnalysesForUser(userId);
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(SoilAnalysis soilAnalysis) {
        SoilAnalysisDatabase.databaseWriteExecutor.execute(() -> {
            mSoilAnalysisDao.insert(soilAnalysis);
        });
    }

    public void insertUser(User user) {
        SoilAnalysisDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
}
