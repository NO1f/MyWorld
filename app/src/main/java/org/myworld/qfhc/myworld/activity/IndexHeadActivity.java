package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.util.Constant;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 01:11
 * @备注：
 */
public class IndexHeadActivity extends BaseActivity{

    private WebView mWv;
    private ImageView ivBack,ivKill;

    @Override
    protected int getContentResid() {
        return R.layout.index_head_content;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String wv_head_url = intent.getStringExtra(Constant.KEYS.INDEX_HEAD_CONTENT);

        mWv= (WebView) findViewById(R.id.wv_first_head_content);
        WebSettings settings = mWv.getSettings();
        settings.setSupportZoom(false);
        settings.setLoadWithOverviewMode(true);
        mWv.loadUrl(wv_head_url);

        mWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWv.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }


    public void click(View v){
        switch (v.getId()){
            case R.id.iv_head_back:
                finish();
                break;
            case R.id.iv_head_kill:
                finish();
        }
    }
}