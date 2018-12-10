package com.monir.framework.mvp.util.helper;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 3:36 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {
    private static SharedPreferences preferences;
    public static final String KEY_BOARD_HEIGHT = "keyboard_height";
    private SharedPref() {
    }

    public static void init(Context context) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static boolean write(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);

        return editor.commit();
    }

    public static boolean write(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(key, value);

        return editor.commit();
    }

    public static boolean write(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(key, value);

        return editor.commit();
    }

    public static boolean write(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong(key, value);

        return editor.commit();
    }

    public static String read(String key) {
        return preferences.getString(key, "");
    }

    public static long readLong(String key) {
        return preferences.getLong(key, 0);
    }

    public static int readInt(String key) {
        return preferences.getInt(key, 0);
    }
    public static boolean readBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static boolean readBooleanDefaultTrue(String key){
        return preferences.getBoolean(key, true);
    }

    public static boolean contains(String key) {
        return preferences.contains(key);
    }
}
