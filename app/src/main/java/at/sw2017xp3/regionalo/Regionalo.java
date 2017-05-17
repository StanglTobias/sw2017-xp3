package at.sw2017xp3.regionalo;

import android.app.Application;
import android.content.Context;

/**
 * Created by jo on 17.05.17.
 */

public class Regionalo extends Application {

    private static Regionalo instance_;

    public static Regionalo getInstance() {
        return instance_;
    }

    public static Context getContext() {
        return instance_.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance_ = this;
        super.onCreate();
    }

}
