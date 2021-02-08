package com.example.bujimuapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bujimuapp.models.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE email_address = :email AND password = :password LIMIT 1")
    LiveData<User> isValidUser(String email, String password);
}
