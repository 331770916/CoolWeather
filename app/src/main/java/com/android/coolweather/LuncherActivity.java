package com.android.coolweather;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.coolweather.core.BaseActivity;
import com.android.coolweather.mvp.ui.main.MainActivity;
import com.jrummyapps.android.widget.AnimatedSvgView;

public class LuncherActivity extends BaseActivity implements AnimatedSvgView.OnStateChangeListener{

    @Override
    protected void initWindow() {
        super.initWindow();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        AnimatedSvgView  svgView = (AnimatedSvgView) findViewById(R.id.luncher_svgView);
        svgView.postDelayed(new Runnable() {

            @Override public void run() {
                svgView.start();
            }
        }, 1000);

        svgView.setOnStateChangeListener(this);
    }

    @Override
    public void onStateChange(int state) {
        if (state == AnimatedSvgView.STATE_TRACE_STARTED) {
        } else if (state == AnimatedSvgView.STATE_FINISHED) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void doAnimation(ImageView imageView) {
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_luncher;
    }
}
