package buka.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Adik Widiasmono on 10/27/2015.
 */
public class SharedPrefBE {
    private final static String CURR_USER_PREF = "currentUserPref";
    private final static String CURR_USER = "currentUser";
    private final static String TIMER = "currentTime";

    public static boolean setCurrentUser(UserBE currentUser, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, CURR_USER_PREF, Context.MODE_PRIVATE);
        complexPreferences.putObject(CURR_USER, currentUser);
        return complexPreferences.commit();
    }

    public static UserBE getCurrentUser(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, CURR_USER_PREF, Context.MODE_PRIVATE);
        UserBE currentUser = complexPreferences.getObject(CURR_USER, UserBE.class);
        return currentUser;
    }

    public static boolean clearCurrentUser(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, CURR_USER_PREF, Context.MODE_PRIVATE);
        complexPreferences.clearObject();
        return complexPreferences.commit();
    }

    public static String checkTimer(Context ctx, boolean isInitial) {
        SharedPreferences shared = ctx.getSharedPreferences("timerCounter", Context.MODE_PRIVATE);
        if (isInitial) {
            long start_time = System.nanoTime();
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(TIMER, start_time + "");
            editor.commit();
            return "***INITIAL TIME***";
        }

        Long t2 = System.nanoTime();
        String t1String = shared.getString(TIMER, null);
        Long t1 = Long.valueOf(t1String);

        Double diff = (t2 - t1) / 1e6;

        SharedPreferences.Editor editor = shared.edit();
        editor.putString(TIMER, t2 + "");
        editor.commit();

        return diff + "";
    }
}
