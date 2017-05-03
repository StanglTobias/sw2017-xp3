package at.sw2017xp3.regionalo.model;



/**
 * Created by Kevin on 03.05.2017.
 */

public class CurrentUser {
    private static String user_id_;

    public static final void setUserId(String user_id) {
        user_id_ = user_id;
    }

    public static String getUserId() {
        return user_id_;
    }
}
