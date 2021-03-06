package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.L;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 01:11
 * @备注：
 */
public class IndexHeadActivity extends BaseActivity{

    private WebView mWv;
    @Override
    protected int getContentResid() {
        return R.layout.index_head_content;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String wv_head_url = intent.getStringExtra(Constant.KEYS.INDEX_HEAD_CONTENT);
        L.e(wv_head_url+"================================================");

        mWv= (WebView) findViewById(R.id.wv_first_head_content);
        WebSettings settings = mWv.getSettings();
        settings.setSupportZoom(false);
        settings.setDefaultFontSize(18);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        mWv.loadUrl(wv_head_url);

        mWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWv.loadUrl(url);
                return false;
            }
        });
    }


    public void clickBack(View v){
        switch (v.getId()){
            case R.id.iv_head_back:
                if(mWv.canGoBack()){
                    mWv.goBack();
                }else {
                    finish();
                }
                break;
            case R.id.iv_head_kill:
                finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&mWv.canGoBack()){
            mWv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}