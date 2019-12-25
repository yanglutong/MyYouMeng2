package com.yang.myyoumeng;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 点击分享
     */
    private Button mBtShare;
    /**
     * qq
     */
    private Button mQq;
    /**
     * 微信
     */
    private Button mWeiChat;
    /**
     * 微博
     */
    private Button mWeiBo;
    /**
     * 登陆QQ
     */
    private Button mBtLoginQQ;
    /**
     * 登陆微信
     */
    private Button mBtLoginWeiChat;
    /**
     * 登陆微博
     */
    private Button mBtLoginWeiBo;

    //5e032285cb23d2244500037a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限
        initPre();
        initView();
    }

    private void initPre() {
        ActivityCompat.requestPermissions(this
                , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        mBtShare = (Button) findViewById(R.id.BtShare);
        mBtShare.setOnClickListener(this);
        mQq = (Button) findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mWeiChat = (Button) findViewById(R.id.WeiChat);
        mWeiChat.setOnClickListener(this);
        mWeiBo = (Button) findViewById(R.id.WeiBo);
        mWeiBo.setOnClickListener(this);
        mBtLoginQQ = (Button) findViewById(R.id.BtLoginQQ);
        mBtLoginQQ.setOnClickListener(this);
        mBtLoginWeiChat = (Button) findViewById(R.id.BtLoginWeiChat);
        mBtLoginWeiChat.setOnClickListener(this);
        mBtLoginWeiBo = (Button) findViewById(R.id.BtLoginWeiBo);
        mBtLoginWeiBo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //带面板的分享
            case R.id.BtShare:
                share();
                break;
            //不带面板的分享
            case R.id.qq:
                shareQQ(SHARE_MEDIA.QQ);
                break;
            //不带面板的分享
            case R.id.WeiChat:
                shareWeiChat(SHARE_MEDIA.WEIXIN);
                break;
            //不带面板的分享
            case R.id.WeiBo:
                shareWeiBo(SHARE_MEDIA.SINA);
                break;
            case R.id.BtLoginQQ:
                loginQQ(SHARE_MEDIA.QQ);
                break;
            case R.id.BtLoginWeiChat:
                loginWeiCat(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.BtLoginWeiBo:
                longinWeiBo(SHARE_MEDIA.SINA);
                break;
        }
    }

    private void longinWeiBo(SHARE_MEDIA sina) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(MainActivity.this, sina,authListener);
    }

    private void loginWeiCat(SHARE_MEDIA weixin) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(MainActivity.this, weixin,authListener);
    }

    private void loginQQ(SHARE_MEDIA qq) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
                umShareAPI.getPlatformInfo(MainActivity.this, qq,authListener);

    }

    private void shareWeiBo(SHARE_MEDIA sina) {
        new ShareAction(MainActivity.this)
                .setPlatform(sina)//传入平台
                .withText("hello")//分享内容
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private void shareWeiChat(SHARE_MEDIA weixin) {
        new ShareAction(MainActivity.this)
                .setPlatform(weixin)//传入平台
                .withText("hello")//分享内容
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private void shareQQ(SHARE_MEDIA qq) {
        UMImage image = new UMImage(MainActivity.this, R.drawable.umeng_socialize_fav);//资源文件
        new ShareAction(MainActivity.this)
                .setPlatform(qq)//传入平台
                .withText("hello")//分享内容
                .withMedia(image)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private void share() {
        UMImage image = new UMImage(MainActivity.this, R.drawable.umeng_socialize_fav);//资源文件
        new ShareAction(MainActivity.this)
                .withText("hello").//分享的内容
                withMedia(image).
                //设置分享的平台
                        setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                //回调
                .setCallback(shareListener)
                .open();
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String,String> entry:data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.i("杨路通", "onComplete: "+key+"-------------"+value);
            }
             

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除授权
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.QQ, authListener);
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.SINA, authListener);
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
    }
}
