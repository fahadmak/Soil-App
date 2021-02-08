package com.example.bujimuapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bujimuapp.converters.DateConverter;
import com.example.bujimuapp.dao.SoilAnalysisDao;
import com.example.bujimuapp.dao.UserDao;
import com.example.bujimuapp.models.SoilAnalysis;
import com.example.bujimuapp.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SoilAnalysis.class, User.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SoilAnalysisDatabase extends RoomDatabase {

    public static SoilAnalysisDatabase instance;

    public abstract SoilAnalysisDao soilAnalysisDao();

    public abstract UserDao userDao();

    private static volatile SoilAnalysisDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SoilAnalysisDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (SoilAnalysisDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            SoilAnalysisDatabase.class, "soil_analysis_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateDbAsyncTask(SoilAnalysisDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("title@gmail.com", "food", "HSBC"
                    ));
            return null;
        }
    }
}
