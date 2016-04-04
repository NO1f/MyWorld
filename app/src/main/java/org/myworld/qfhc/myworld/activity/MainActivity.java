package org.myworld.qfhc.myworld.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.CategoryFragment;
import org.myworld.qfhc.myworld.fragment.IndexFragment;
import org.myworld.qfhc.myworld.fragment.MineFragment;
import org.myworld.qfhc.myworld.fragment.TuanFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RadioGroup mRg;
    private List<Fragment> fragmentList;

    @Override
    protected int getContentResid() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mRg = (RadioGroup) findViewById(R.id.rg_tab);
        fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(CategoryFragment.newInstance());
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
                                    .hide(fragmentList.get(3))
                                    .show(fragmentList.get(0))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(2))
                                    .hide(fragmentList.get(3))
                                    .add(R.id.fl, fragmentList.get(0)).show(fragmentList.get(0)).commit();
                        }
                        break;

                    case R.id.rb_first_tab_category:
                        if (fragmentList.get(1).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(2))
                                    .hide(fragmentList.get(3))
                                    .show(fragmentList.get(1))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(2))
                                    .hide(fragmentList.get(3))
                                    .add(R.id.fl, fragmentList.get(1)).show(fragmentList.get(1)).commit();
                        }
                        break;

                    case R.id.rb_first_tab_tuan:
                        if (fragmentList.get(2).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(3))
                                    .show(fragmentList.get(2))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(0))
                                    .hide(fragmentList.get(3))
                                    .add(R.id.fl, fragmentList.get(2)).show(fragmentList.get(2)).commit();
                        }
                        break;

                    case R.id.rb_first_tab_mine:
                        if (fragmentList.get(3).isAdded()) {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(2))
                                    .hide(fragmentList.get(0))
                                    .show(fragmentList.get(3))
                                    .commit();

                        } else {
                            transaction
                                    .hide(fragmentList.get(1))
                                    .hide(fragmentList.get(2))
                                    .hide(fragmentList.get(0))
                                    .add(R.id.fl, fragmentList.get(3)).commit();
                        }
                        break;
                }

            }
        });

        mRg.getChildAt(0).performClick();
    }

}
