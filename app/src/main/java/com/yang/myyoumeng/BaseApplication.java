package com.yang.myyoumeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //不配置ak登陆和分享功能都不可以

        //开启友盟自带的logi
        UMConfigure.setLogEnabled(true);
        //1上下文 2apk 3友盟的渠道号 4设备类型 推送的ak
        UMConfigure.init(this,"5e032285cb23d2244500037a"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

        //第三方分享 qq可以不加ak分享 其他需要加
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }
}
