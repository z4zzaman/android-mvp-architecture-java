package com.monir.framework.mvp.ui.base;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/9/2018 at 8:38 PM.
 *  * Email : zzaman08@gmail.com
 *  *
 *  * Purpose: A base presenter class that will be extended by other presenter in this project
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/9/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.app.Activity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

public abstract class BasePresenter<V extends MvpView> implements LifecycleObserver, MvpPresenter<V> {

    private Activity mActivity = null;
    private volatile V mMvpView;
    private Bundle mStateBundle;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    public void onPresenterCreated() {

    }

    @Override
    public void onPresenterDestroy() {
        if (mStateBundle != null && !mStateBundle.isEmpty()) mStateBundle.clear();
    }

    /**
     *
     * @return Activity current active activity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * To set current Activity on presenter
     *
     * @param activity Activity as param
     * @return void
     */
    protected void setActivity(Activity activity) {
        mActivity = activity;
    }

    /**
     * Check MvpView attached with presenter or not
     *
     * @return boolean value is view attached state
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * @return current MvpView
     */
    public V getMvpView() {
        return mMvpView;
    }


    /**
     * @return Bundle value
     */
    public Bundle getStateBundle() {
        return mStateBundle == null ? mStateBundle = new Bundle() : mStateBundle;
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    @NonNull
    protected <T extends AndroidViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return ViewModelProviders.of((FragmentActivity) mActivity).get(modelClass);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }

    /**
     * To get current LifecycleOwner of active activity
     *
     * @return LifecycleOwner object
     */
    protected LifecycleOwner getLifecycleOwner() {
        return (LifecycleOwner) mActivity;
    }
}
