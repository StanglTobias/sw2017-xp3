package at.sw2017xp3.regionalo.model;

/**
 * Created by Kevin on 03.05.2017.
 */

public class CurrentUser {
    private static String current_user_id_;

    public static final void setCurrentUserId(String user_id) {
        current_user_id_ = user_id;
    }

    public static String getCurrentUserId() {
        return current_user_id_;
    }


}
