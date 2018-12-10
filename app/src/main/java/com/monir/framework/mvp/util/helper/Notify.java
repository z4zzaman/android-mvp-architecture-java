package com.monir.framework.mvp.util.helper;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 4:39 PM.
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

public class Notify {
    private static Context sContext;
    private Notify() {
    }

    public static void init(Context context) {
        sContext = context;
    }
}
