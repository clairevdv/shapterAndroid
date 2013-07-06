package com.shapter;
import android.app.Application;
public class ShapterApp extends Application {
    //add this variable declaration:
    public static String username = null;
    private static ShapterApp singleton;
    public static ShapterApp getInstance() {
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
