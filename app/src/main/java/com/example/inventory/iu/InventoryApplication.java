package com.example.inventory.iu;

import android.app.Application;

import com.example.inventory.iu.preferences.InventoryPreferences;

public class InventoryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InventoryPreferences.newInstance(this);
    }
}
