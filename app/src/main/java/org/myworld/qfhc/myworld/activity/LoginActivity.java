package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.MineFragment;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.LoginUtil;
import org.myworld.qfhc.myworld.util.ShareUtil;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/9 00:49
 * @备注：
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected int getContentResid() {
        return R.layout.login_layout;
    }

    public void click(View v) {
        switch (v.getId()) {

            case R.id.tv_back:
                finish();
                break;

            case R.id.iv_sina:

                LoginUtil.sinaLongin(this, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
                        String id = res.get("id").toString();//ID
                        String name = res.get("name").toString();//用户名
                        String description = res.get("description").toString();//描述
                        String profile_image_url = res.get("profile_image_url").toString();//头像链接
                        String str = "ID: " + id + ";\n" +
                                "用户名： " + name + ";\n" +
                                "描述：" + description + ";\n" +
                                "用户头像地址：" + profile_image_url;

                        if (str!=null){
                            ShareUtil.putString(Constant.KEYS.USER,name);
                            ShareUtil.putString(Constant.KEYS.URL,profile_image_url);
                            Intent intent=new Intent();
                            intent.putExtra("img_url",profile_image_url);
                            intent.putExtra("name",name);
                            setResult(RESULT_OK,intent);
                            finish();
                        }

                        L.e(str);
                        Platform sina = ShareSDK.getPlatform(LoginActivity.this, SinaWeibo.NAME);
                        if (sina.isValid()) {
                            sina.removeAccount();
                        }

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(LoginActivity.this, "登录错误", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(LoginActivity.this, "取消了登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                break;

            case R.id.iv_qq:

                LoginUtil.QzomLogin(this, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
                        String id = res.get("id").toString();//ID
                        String name = res.get("name").toString();//用户名
                        String description = res.get("description").toString();//描述
                        String profile_image_url = res.get("profile_image_url").toString();//头像链接
                        String str = "ID: " + id + ";\n" +
                                "用户名： " + name + ";\n" +
                                "描述：" + description + ";\n" +
                                "用户头像地址：" + profile_image_url;

                        if (str!=null){
                            ShareUtil.putString(Constant.KEYS.USER,name);
                            ShareUtil.putString(Constant.KEYS.URL,profile_image_url);
                            Intent intent=new Intent();
                            intent.putExtra("img_url",profile_image_url);
                            intent.putExtra("name",name);
                            setResult(RESULT_OK,intent);
                            finish();
                        }

                        L.e(str);
                        Platform sina = ShareSDK.getPlatform(LoginActivity.this, QZone.NAME);
                        if (sina.isValid()) {
                            sina.removeAccount();
                        }

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(LoginActivity.this, "登录错误", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(LoginActivity.this, "取消了登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                break;
        }
    }
}
