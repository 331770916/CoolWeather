package com.android.coolweather.mvp.ui.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.coolweather.R;
import com.android.coolweather.mvp.modle.HistoryEntity;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by zhangwenbo on 2017/3/13.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHodler>{

    private List<HistoryEntity> mHistoryEntitiys;
    private Context mContext;

    public HistoryAdapter (Context context) {
        mContext = context;
    }

    public void setDatas(List<HistoryEntity> historyEntitiys) {
        mHistoryEntitiys = historyEntitiys;
        notifyDataSetChanged();
    }

    @Override
    public HistoryViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_history, null);
        return new HistoryViewHodler(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHodler holder, int position) {
        HistoryEntity historyEntity = mHistoryEntitiys.get(position);
        holder.name.setText(historyEntity.getName());
        holder.time.setText(historyEntity.getTime());
        holder.author.setText(mContext.getResources().getString(R.string.author) + historyEntity.getAuthor());
        Glide.with(mContext).load(historyEntity.getPicUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (mHistoryEntitiys != null && mHistoryEntitiys.size() > 0 ) {
            return mHistoryEntitiys.size();
        }
        return 0;
    }

    class HistoryViewHodler extends RecyclerView.ViewHolder {
        ImageView   imageView;
        TextView    name;
        TextView    time;
        TextView    author;

        public HistoryViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_historyItem);
            name = (TextView) itemView.findViewById(R.id.tv_historyItemName);
            time = (TextView) itemView.findViewById(R.id.tv_historyItemTime);
            author = (TextView) itemView.findViewById(R.id.tv_historyItemAuthor);
        }
    }
}
