package com.android.coolweather.mvp.ui.search;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.coolweather.R;
import com.android.coolweather.core.BaseActivity;
import com.android.coolweather.mvp.ui.mood.ColorSuggestion;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangwenbo on 2017/3/10.
 * 搜索界面
 */

public class SearchActivity extends BaseActivity implements FloatingSearchView.OnQueryChangeListener,
        FloatingSearchView.OnFocusChangeListener, SearchSuggestionsAdapter.OnBindSuggestionCallback,
        FloatingSearchView.OnMenuItemClickListener {
    private FloatingSearchView mSearchView;
    private SearchResultsListAdapter mSearchResultsAdapter;
    private List<ColorSuggestion> mResults;
    @Override
    protected void initWidget() {
        super.initWidget();
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setBackgroundColor(Color.WHITE);
        RecyclerView searchResultsList = (RecyclerView) findViewById(R.id.search_results_list);
        mSearchResultsAdapter = new SearchResultsListAdapter();
        searchResultsList.setAdapter(mSearchResultsAdapter);
        searchResultsList.setLayoutManager(new LinearLayoutManager(this));

        mSearchView.setOnQueryChangeListener(this);
        mSearchView.setOnFocusChangeListener(this);
        mSearchView.setOnBindSuggestionCallback(this);
        mSearchView.setOnMenuItemClickListener(this);
        mResults = new ArrayList<ColorSuggestion>();
    }

    @Override
    public void onActionMenuItemSelected(MenuItem item) {


        Toast.makeText(SearchActivity.this, "voice",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchTextChanged(String oldQuery, String newQuery) {
        if (!oldQuery.equals("") && newQuery.equals("")) {
            mSearchView.clearSuggestions();
        } else {
            mSearchView.showProgress();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ColorSuggestion colorSuggestion = new ColorSuggestion("aaa");
                    mResults.add(colorSuggestion);
                    mSearchView.swapSuggestions(mResults);
                    mSearchView.hideProgress();
                }
            }, 2000);
        }
    }

    @Override
    public void onFocus() {
        List<ColorSuggestion> suggestionList = new ArrayList<>();
        String [] colors = {"blue", "green", "red"};
        for (int i = 0; i < 3; i++) {
            ColorSuggestion colorSuggestion = new ColorSuggestion(colors[i]);
            colorSuggestion.setIsHistory(true);
            suggestionList.add(colorSuggestion);
        }

        mSearchView.swapSuggestions(suggestionList);
    }

    @Override
    public void onFocusCleared() {
        mSearchView.setSearchBarTitle("");
    }

    @Override
    public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
        ColorSuggestion colorSuggestion = (ColorSuggestion) item;

        if (colorSuggestion.getIsHistory()) {
            leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.ic_history_black_24dp, null));

            String textColor = "#999999";
            setIconColor(leftIcon, Color.parseColor(textColor));
            leftIcon.setAlpha(.36f);
        } else {
            leftIcon.setAlpha(0.0f);
            leftIcon.setImageDrawable(null);
        }
    }

    /**
     * 设置颜色
     * @param iconHolder
     * @param color
     */
    private void setIconColor(ImageView iconHolder, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(iconHolder.getDrawable());
        DrawableCompat.setTint(wrappedDrawable, color);
        iconHolder.setImageDrawable(wrappedDrawable);
        iconHolder.invalidate();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }
}
