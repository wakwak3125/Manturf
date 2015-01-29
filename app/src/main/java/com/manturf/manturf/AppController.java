package com.manturf.manturf;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by RyoSakaguchi on 15/01/22.
 */
public class AppController extends Application {
    private static AppController sInstance;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;

        Configuration.Builder builder = new Configuration.Builder(getBaseContext());
        builder.setCacheSize(1024*1024*1);
        builder.setDatabaseName("test.db");
        builder.setDatabaseVersion(1);
        ActiveAndroid.initialize(builder.create(), true);
    }

    public synchronized static AppController getInstance(){
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
