package com.monir.framework.mvp.ui.base;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/8/2018 at 2:30 PM.
 *  * Email : zzaman08@gmail.com
 *  *
 *  * Purpose: A base activity that will be extended by others activity in this project
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/8/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
        extends AppCompatActivity implements MvpView, View.OnClickListener, View.OnFocusChangeListener {


    protected P presenter;
    private int mDefaultValue = -1;
    private ViewDataBinding mViewDataBinding;
    private Menu mMenu;

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    /**
     * It's built in method in Fragment Activity
     * that is extends by AppCompatActivity
     *
     * @return
     */
    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId > mDefaultValue) {

            updateLayoutView(layoutId);

            BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

            boolean isPresenterCreated = false;

            if (viewModel.getPresenter() == null) {
                viewModel.setPresenter(initPresenter());
                isPresenterCreated = true;
            }

            presenter = viewModel.getPresenter();
            presenter.attachLifecycle(getLifecycle());
            presenter.attachView((V) this);

            if (isPresenterCreated) presenter.onPresenterCreated();

            int toolbarId = getToolbarId();

            setStatusBarColor();

            if (toolbarId > mDefaultValue) {
                Toolbar toolbar = findViewById(toolbarId);

                if (toolbar != null) {
                    setSupportActionBar(toolbar);
                }

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setDisplayShowHomeEnabled(true);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setActivity(this);

        this.startUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() > mDefaultValue) {
            getMenuInflater().inflate(getMenuId(), menu);
            this.mMenu = menu;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stopUI();

        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }


    private void updateLayoutView(int layoutId) {
        try {
            mViewDataBinding = DataBindingUtil.setContentView(this, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mViewDataBinding == null) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
    }

    /**
     * To set the status bar color
     */
    private void setStatusBarColor() {

        int statusBarColor = getStatusBarColor();

        if (statusBarColor > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
            }
        }
    }

    /**
     * To set title on toolbar
     *
     * @param title string value
     * @return void
     */
    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /**
     * To set sub title on toolbar
     *
     * @param subtitle string value
     * @return void
     */
    protected void setSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

    /**
     * To set both title and subtitle in toolbar
     *
     * @param title    string value
     * @param subtitle string value
     * @return void
     */
    protected void setToolbarText(String title, String subtitle) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setSubtitle(subtitle);
        }
    }

    /**
     * To set click listener on any view, You can pass multiple view at a time
     *
     * @param views View as params
     * @return void
     */
    protected void setClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }


    /*
     * Child class can(optional) override this method. On this method you will pass the color id
     * */
    protected int getStatusBarColor() {
        return mDefaultValue;
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the toolbar id of current layout
     * */
    protected int getToolbarId() {
        return mDefaultValue;
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the menu file of current activity
     * */
    protected int getMenuId() {
        return mDefaultValue;
    }

    /*
     * To get the current menu. It will return current menu if you set it. Otherwise return null.
     * */
    protected Menu getMenu() {
        return mMenu;
    }


    /*
     * Child class have to implement this Generic method and will pass the current new object of presenter
     * */
    protected abstract P initPresenter();

    /*
     * Child class have to implement this method. On this method you will pass the layout file of current activity
     * */
    protected abstract int getLayoutId();

    /*
     * Child class have to implement this method. This method run on onStart lifecycle
     * */
    protected abstract void startUI();

    /*
     * Child class have to implement this method. This method run on onDestroy lifecycle
     * */
    protected abstract void stopUI();

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}
