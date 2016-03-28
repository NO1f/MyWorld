package org.myworld.qfhc.myworld.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @version V1.0
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：HC
 * @创建时间：${date} ${time}
 * @备注：FirstReceiver
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContentResid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(getContentResid());

        init();
        initData();
    }

    protected void initData() {
    }

    protected void init() {
    }

    public void startActivity(Intent intent, int enterAnim, int exitAnim) {
        super.startActivity(intent);
        overridePendingTransition(enterAnim,exitAnim);
    }
}
