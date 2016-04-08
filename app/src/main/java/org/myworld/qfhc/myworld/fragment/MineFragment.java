package org.myworld.qfhc.myworld.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.BuildConfig;
import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.PullToZoomAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.PullToZoomListView;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.ShareUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class MineFragment extends BaseFragment {

    private PullToZoomListView ptv;
    private SimpleDraweeView sdv;
    private View contentView;

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
        ptv = (PullToZoomListView) view.findViewById(R.id.ptz);
        initLogin();

        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.picpopu, null);


        PullToZoomAdapter adapter = new PullToZoomAdapter(getActivity());
        ImageView headerView = ptv.getHeaderView();
        headerView.setImageResource(R.drawable.bg_me);

        String[] title = new String[]{"我的喜欢", "我的收藏", "清理缓存", "我的订单", "版本更新", "关于Beauty"};
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            titles.add(title[i]);
        }
        adapter.setDatas(titles);
        headerView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ptv.setAdapter(adapter);

    }

    private void initLogin() {
        final String user_name = ShareUtil.getString(Constant.KEYS.USER, "null");
        sdv = (SimpleDraweeView) ptv.getView();
        sdv.setOnClickListener(new View.OnClickListener() {

            private PopupWindow mWindow;

            @Override
            public void onClick(View v) {

                L.e("-----------------------------");

                if (!"null".equals(user_name)) {


                } else {

                    if (mWindow!=null&&mWindow.isShowing()) {
                        mWindow.dismiss();
                    } else {

                        int widthPixels = getResources().getDisplayMetrics().widthPixels / 3 * 2;
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

                    /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("修改头像");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image*//*");
                            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                            startActivityForResult(intent, 100);
                        }
                    });
                    builder.show();*/

                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (data != null) {
                sdv.setImageURI(data.getData());
            }
        }

    }
}
