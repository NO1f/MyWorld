package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.os.Handler;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;

/**
 * Created by Ken on 2016/3/7.
 */
public class WelcomeActivity extends BaseActivity {

    private static Handler handler = new Handler();

    @Override
    protected int getContentResid() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class), R.anim.activity_in_right, R.anim.activity_out_left);
                finish();
            }
        }, 2000);
    }
}
