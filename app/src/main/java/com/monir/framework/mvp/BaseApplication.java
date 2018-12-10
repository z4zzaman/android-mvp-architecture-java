package com.monir.framework.mvp;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/8/2018 at 2:32 PM.
 *  * Email : zzaman08@gmail.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/8/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.monir.framework.mvp.baseapplication.data.local.dbhelper.DatabaseService;
import com.monir.framework.mvp.util.helper.Glider;
import com.monir.framework.mvp.util.helper.Notify;
import com.monir.framework.mvp.util.helper.PermissionUtil;
import com.monir.framework.mvp.util.helper.SharedPref;
import com.monir.framework.mvp.util.helper.Toaster;
import com.squareup.leakcanary.LeakCanary;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseApplication extends MultiDexApplication {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        //only needed in debug mode, comment this line before releasing
        debugLoader(sContext);
        //Always nned for release and debug mode
        releaseLoader(sContext);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        MultiDex.install(this);
    }

    private void debugLoader(Context context) {
        if (!BuildConfig.DEBUG) return;

        // Todo: Here we can initialise the stuff that are actually need only in debug mode
        LeakCanary();
    }

    private void releaseLoader(Context context) {
        Glider.init(context);
        Toaster.init(context);
        SharedPref.init(context);
        Notify.init(context);
        PermissionUtil.init(context);
        DatabaseService.init(context);

        calligraphy();
    }


    private void calligraphy() {
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    private void LeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

}
