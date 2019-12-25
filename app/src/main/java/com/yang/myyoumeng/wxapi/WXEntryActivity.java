package com.yang.myyoumeng.wxapi;

import android.os.Bundle;

import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.yang.myyoumeng.R;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
