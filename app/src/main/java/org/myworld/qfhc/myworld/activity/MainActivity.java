package org.myworld.qfhc.myworld.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.IndexFragment;
import org.myworld.qfhc.myworld.fragment.MineFragment;
import org.myworld.qfhc.myworld.fragment.TuanFragment;
import org.myworld.qfhc.myworld.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RadioGroup mRg;
    private List<Fragment> fragmentList;

    private long exitTime = 0;
    private BroadcastReceiver connectionReceiver;

    @Override
    protected int getContentResid() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mRg = (RadioGroup) findViewById(R.id.rg_tab);
        fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(TuanFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());


        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.rb_first_tab_index:
                        if (fragmentList.get(0).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(2))
                                    .show(fragmentList.get(0))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(2))
                                    .add(R.id.fl, fragmentList.get(0)).show(fragmentList.get(0)).commit();
                        }
                        break;

                    case R.id.rb_first_tab_tuan:
                        if (fragmentList.get(1).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(2))
                                    .show(fragmentList.get(1))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(2))
                                    .add(R.id.fl, fragmentList.get(1)).show(fragmentList.get(1)).commit();
                        }
                        break;

                    case R.id.rb_first_tab_mine:
                        if (fragmentList.get(2).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(0))
                                    .show(fragmentList.get(2))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(0))
                                    .add(R.id.fl, fragmentList.get(2)).show(fragmentList.get(2)).commit();
                        }
                        break;
                }

            }
        });

        mRg.getChildAt(0).performClick();

        // unconnect network
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        // connect network
        connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, Intent intent) {

                if (NetworkUtil.isNetworkConnected(MainActivity.this)==false) {
                    // unconnect network

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = null;
                            //判断手机系统的版本  即API大于10 就是3.0或以上版本
                            if (android.os.Build.VERSION.SDK_INT > 10) {
                                intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            } else {
                                intent = new Intent();
                                ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                                intent.setComponent(component);
                                intent.setAction("android.intent.action.VIEW");
                            }
                            context.startActivity(intent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else {
                    // connect network
                }
            }
        };


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
    }

    /**********************************网络判断*********************************/


    /***********************************
     * 双击退出App
     ************************************/
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    //双击退出程序
    private void exitApp() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "双击退出Beauty", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }
    }

}

