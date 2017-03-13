package com.android.coolweather.mvp.modle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangwenbo on 2017/3/13.
 * 历史实体类
 */

public class HistoryEntity implements Parcelable {

    private String name;        //标题名称
    private String type;        //当前类型（视频， 有声小说， 音乐）
    private String time;        //历史时间
    private String picUrl;      //图片地址
    private String localPicUrl; //本地图片
    private String author;      //作者

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(time);
        dest.writeString(picUrl);
        dest.writeString(localPicUrl);
        dest.writeString(author);
    }

    public static final Creator<HistoryEntity> CREATOR = new Creator<HistoryEntity>(){

        @Override
        public HistoryEntity createFromParcel(Parcel source) {
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.name = source.readString();
            historyEntity.type = source.readString();
            historyEntity.time = source.readString();
            historyEntity.picUrl = source.readString();
            historyEntity.localPicUrl = source.readString();
            historyEntity.author = source.readString();

            return historyEntity;
        }

        @Override
        public HistoryEntity[] newArray(int size) {
            return new HistoryEntity[size];
        }
    };



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLocalPicUrl() {
        return localPicUrl;
    }

    public void setLocalPicUrl(String localPicUrl) {
        this.localPicUrl = localPicUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
