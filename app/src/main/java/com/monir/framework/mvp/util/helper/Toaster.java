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
import android.widget.Toast;

public class Toaster {
    private static Context sContext;

    /*
     * Private constructor. Don't make it public
     * */
    private Toaster() {
    }

    /*
     * Init Toast message context only one time
     * */
    public static void init(Context context) {
        sContext = context;
    }

    /*
     * Show long toast message
     * */
    public static void show(String txt) {
        Toast.makeText(sContext, txt, Toast.LENGTH_SHORT).show();
    }

    /*
     * Show short toast message
     * */
    public static void showShort(String txt) {
        Toast.makeText(sContext, txt, Toast.LENGTH_SHORT).show();
    }
}
