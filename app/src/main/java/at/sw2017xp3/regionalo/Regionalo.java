package at.sw2017xp3.regionalo;

import android.app.Application;
import android.content.Context;
import android.location.Location;

/**
 * Created by jo on 17.05.17.
 */

public class Regionalo extends Application {

    private static Regionalo instance_;

    public static Regionalo getInstance() {
        return instance_;
    }

    public Location LastKnownLocation_;

    public void setLastKnownLocation(Location loc) {
        LastKnownLocation_ = loc;
    }


    public Location getLastKnownLocation() {

        if (LastKnownLocation_ == null) {
            Location targetLocation = new Location("");
            targetLocation.setLatitude(47.076668d);
            targetLocation.setLongitude(15.421371d);
            return targetLocation;
        }
        return LastKnownLocation_;
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
