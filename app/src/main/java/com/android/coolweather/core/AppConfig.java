package com.android.coolweather.core;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by zhangwenbo on 2017/3/9.
 * app配置类
 */

public class AppConfig {

    private final static String APP_CONFIG = "config";
    public static final String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";       //貌似是加载时的dialog图片诶
    public static final String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
    public static final String KEY_CHECK_UPDATE = "KEY_CHECK_UPDATE";//检查更新的标志
    public static final String KEY_DOUBLE_CLICK_EXIT = "KEY_DOUBLE_CLICK_EXIT";//双击退出的一个标志

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "HuoZhuMing"
            + File.separator + "hzm_img" + File.separator;

    // 默认存放文件下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "HuoZhuMing"
            + File.separator + "download" + File.separator;

    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

}
