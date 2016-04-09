package org.myworld.qfhc.myworld.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.BuildConfig;
import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.DingDanActivity;
import org.myworld.qfhc.myworld.activity.LoginActivity;
import org.myworld.qfhc.myworld.activity.MainActivity;
import org.myworld.qfhc.myworld.activity.MineCollectActivity;
import org.myworld.qfhc.myworld.activity.MineLikesActivity;
import org.myworld.qfhc.myworld.adapter.PullToZoomAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.PullToZoomListView;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.ErCiCaiYangUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.ShareUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private PullToZoomListView ptv;
    private View headView;
    private View contentView;
    private TextView tvXaingCe, tvXaingJi, tvCancle, tvUser;
    private PopupWindow mWindow;
    private File tempFile;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private SimpleDraweeView sdv;
    private TextView tvCancleLogin;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        ptv = (PullToZoomListView) view.findViewById(R.id.ptz);
        headView = ptv.getView();
        sdv = (SimpleDraweeView) this.headView.findViewById(R.id.sdv_user);
        tvUser = (TextView) this.headView.findViewById(R.id.tv_user);
        tvCancleLogin = (TextView) headView.findViewById(R.id.tv_canclelogin);
        tvCancleLogin.setOnClickListener(this);

        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.picpopu, null);
        tvXaingCe = (TextView) contentView.findViewById(R.id.tv_xiangce);
        tvXaingCe.setOnClickListener(this);
        tvXaingJi = (TextView) contentView.findViewById(R.id.tv_xiangji);
        tvXaingJi.setOnClickListener(this);
        tvCancle = (TextView) contentView.findViewById(R.id.tv_cancle);
        tvCancle.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        String name = ShareUtil.getString(Constant.KEYS.USER);
        String img_url = ShareUtil.getString(Constant.KEYS.URL);

        if (name != null && img_url != null) {
            sdv.setImageURI(Uri.parse(img_url));
            tvUser.setText(name);
            tvCancleLogin.setVisibility(View.VISIBLE);
        }else {
            tvCancleLogin.setVisibility(View.INVISIBLE);
        }

        initLogin();

        PullToZoomAdapter adapter = new PullToZoomAdapter(getActivity());
        ImageView headerView = ptv.getHeaderView();
        headerView.setImageResource(R.drawable.bg_stylist_intro);

        String[] title = new String[]{"我的喜欢", "我的收藏", "清理缓存", "我的订单", "版本更新", "关于Beauty"};
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            titles.add(title[i]);
        }
        adapter.setDatas(titles);
        headerView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ptv.setOnItemClickListener(this);
        ptv.setAdapter(adapter);
    }

    private void initLogin() {
        final String user_name = ShareUtil.getString(Constant.KEYS.USER);
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                L.e("-----------------------------");

                if (null == (user_name)) {

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {

                    if (mWindow != null && mWindow.isShowing()) {
                        mWindow.dismiss();
                    } else {

                        int widthPixels = getResources().getDisplayMetrics().widthPixels / 2 * 1;
                        int heigh = getResources().getDisplayMetrics().heightPixels / 7 * 2;
                        mWindow = new PopupWindow(contentView, widthPixels, heigh);
                        mWindow.setOutsideTouchable(true);

                        // 设置PopupWindow获得焦点的能力
                        // mWindow.setFocusable(true);

                        // 设置背景图片
                        Drawable background = getResources().getDrawable(
                                R.drawable.popu_bg);
                        mWindow.setBackgroundDrawable(background);

                        mWindow.setAnimationStyle(R.style.popwin_anim_style);
                        // 让PopupWindow显示出来
                        // mWindow.showAtLocation(parent, gravity, x, y);
                        mWindow.showAtLocation(ptv, Gravity.CENTER, 0, 0);
                    }

                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == PHOTO_REQUEST_GALLERY) {
                if (data != null) {
                    //  sdv.setImageURI(data.getData());
                    ErCiCaiYangUtil.CustomErCiCaiYang(150, data.getData().toString(), sdv);
                }
            } else if (requestCode == PHOTO_REQUEST_CAREMA) {
                // 从相机返回的数据
                if (hasSdcard()) {
                    crop(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == PHOTO_REQUEST_CUT) {
                // 从剪切图片返回的数据
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    this.sdv.setImageBitmap(bitmap);
                }
                try {
                    // 将临时文件删除
                    tempFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xiangce:

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

                break;
            case R.id.tv_xiangji:
                // 激活相机
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            PHOTO_FILE_NAME);
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent1, PHOTO_REQUEST_CAREMA);

                break;
            case R.id.tv_cancle:
                if (mWindow != null) {
                    mWindow.dismiss();
                }
                break;

            case R.id.tv_canclelogin:

                ShareUtil.clear();
                sdv.setImageResource(R.drawable.dialog_head_bg);
                tvUser.setText("用户");
                tvCancleLogin.setVisibility(View.INVISIBLE);

                break;
        }
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /*
    * 剪切图片
    */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                Intent intent1 = new Intent(getActivity(), MineLikesActivity.class);
                startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(getActivity(), MineCollectActivity.class);
                startActivity(intent2);
                break;

            case 3:
                VolleyUtil.CleanCache(getActivity());
                break;

            case 4:
                Intent intent=new Intent(getActivity(),DingDanActivity.class);
                startActivity(intent);
                break;

            case 5:
                Toast.makeText(getActivity(), "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;

            case 6:
                Intent intent6 = new Intent(getActivity(), MineCollectActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
