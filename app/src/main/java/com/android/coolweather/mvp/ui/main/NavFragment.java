package com.android.coolweather.mvp.ui.main;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.coolweather.R;
import com.android.coolweather.core.BaseFragment;
import com.android.coolweather.mvp.ui.channel.ChannelFragment;
import com.android.coolweather.mvp.ui.history.HistoryFragment;
import com.android.coolweather.mvp.ui.mood.MoodFragment;
import com.android.coolweather.widget.main.BorderShape;
import com.android.coolweather.widget.main.NavigationButton;

import java.util.List;

/**
 * Created by zhangwenbo on 2017/3/9.
 */

public class NavFragment extends BaseFragment implements View.OnClickListener{

    NavigationButton        mHistoryNBtn;
    NavigationButton        mChannelNBtn;
    NavigationButton        mMoodNBtn;


    private Context         mContext;
    private int             mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mHistoryNBtn = (NavigationButton) root.findViewById(R.id.historyRadioBtn);
        mChannelNBtn = (NavigationButton) root.findViewById(R.id.channelRadioBtn);
        mMoodNBtn    = (NavigationButton) root.findViewById(R.id.moodRadioBtn);


        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(android.R.color.white)),
                lineDrawable
        });
        root.setBackgroundDrawable(layerDrawable);


        mHistoryNBtn.init(R.drawable.ic_select_history,
                R.string.history,
                HistoryFragment.class);

        mChannelNBtn.init(R.drawable.ic_select_channel,
                R.string.channel,
                ChannelFragment.class);

        mMoodNBtn.init(R.drawable.ic_select_mood,
                R.string.mood,
                MoodFragment.class);

        mHistoryNBtn.setOnClickListener(this);
        mChannelNBtn.setOnClickListener(this);
        mMoodNBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) v;
            doSelect(nav);
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mHistoryNBtn);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {
        // If the new navigation is me info fragment, we intercept it
        /*
        if (newNavButton == mNavMe) {
            if (interceptMessageSkip())
                return;
        }
        */

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}
