package com.android.coolweather.mvp.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.coolweather.R;
import com.android.coolweather.core.BaseActivity;
import com.android.coolweather.mvp.ui.search.SearchActivity;
import com.android.coolweather.widget.main.NavigationButton;


/**
 * Created by zhangwenbo on 2017/3/1.
 * 主页
 */

public class MainActivity extends BaseActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, NavFragment.OnNavigationReselectListener {

    private DrawerLayout mDrawerLayout;
    private NavFragment mNavBar;

    @Override
    protected void initWidget() {
        super.initWidget();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //静态的菜单图标
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_reorder_black_24dp);
//        }

        //动态的菜单图标
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_menu_account);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "onclick", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
//        Fragment fragment = navigationButton.getFragment();
//        if (fragment != null
//                && fragment instanceof OnTabReselectListener) {
//            OnTabReselectListener listener = (OnTabReselectListener) fragment;
//            listener.onTabReselect();
//        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
