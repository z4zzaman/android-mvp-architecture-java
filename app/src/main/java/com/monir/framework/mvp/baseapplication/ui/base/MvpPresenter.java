package com.monir.framework.mvp.baseapplication.ui.base;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/9/2018 at 8:38 PM.
 *  * Email : zzaman08@gmail.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/9/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.lifecycle.Lifecycle;

public interface MvpPresenter<V extends MvpView> {
    /**
     * Called when MvpView TYPE object attach to presenter
     *
     * @param mvpView The MvpView that have to attach.
     */
    void attachView(V mvpView);

    /**
     * Called when MvpView TYPE object detach from presenter
     */
    void detachView();

    /**
     * Called when Lifecycle TYPE object attach to presenter
     *
     * @param lifecycle The Lifecycle that have to attach.
     */
    void attachLifecycle(Lifecycle lifecycle);

    /**
     * Called when Lifecycle TYPE object detach from presenter
     */
    void detachLifecycle(Lifecycle lifecycle);

    /**
     * Called when presenter TYPE object creation done
     */
    void onPresenterCreated();

    /**
     * Called when presenter TYPE object destroy done
     */
    void onPresenterDestroy();
}
