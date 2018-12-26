package com.monir.framework.mvp.baseapplication.ui.splash;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/26/2018 at 3:10 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/26/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.monir.framework.mvp.R;
import com.monir.framework.mvp.baseapplication.ui.base.BaseActivity;
import com.monir.framework.mvp.baseapplication.ui.main.MainActivity;
import com.monir.framework.mvp.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity<SplashMvpView, SplashPresenter> implements SplashMvpView {
    private static final int SPLASH_TIME = 2300;

    private ActivitySplashBinding activitySplashBinding;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void startUI() {

        activitySplashBinding = (ActivitySplashBinding) getViewDataBinding();
        Animation downTop = AnimationUtils.loadAnimation(this, R.anim.downtop);
        activitySplashBinding.imageAppName.setAnimation(downTop);
        goToNextPage();
    }

    @Override
    protected void stopUI() {
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    public void goToNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.runActivity(SplashActivity.this);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
                finish();
            }
        }, SPLASH_TIME);
    }
}
