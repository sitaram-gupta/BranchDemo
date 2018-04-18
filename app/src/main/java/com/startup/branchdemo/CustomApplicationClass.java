package com.startup.branchdemo;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by lenovo on 4/18/2018.
 */

public class CustomApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Branch logging for debugging
        Branch.enableLogging();

        // Branch object initialization
        Branch.getAutoInstance(this);
    }
}
