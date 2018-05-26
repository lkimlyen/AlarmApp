package com.demo.alarm.app.yenyen;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

     private final String TAG = MainActivity.class.getName();
    private static final int REQUEST_RING_SELECT = 1;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    SettingFragment settingFragment;
    AlarmFragment alarmFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        if (settingFragment == null) {
            settingFragment = SettingFragment.newInstance();
        }

        if (alarmFragment == null) {
            alarmFragment = AlarmFragment.newInstance();
        }
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabItem(tabLayout);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(alarmFragment);
        adapter.addFragment(settingFragment);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Alarm";
                case 1:
                    return "Setting";
            }
            return mFragmentList.get(position).getClass().getSimpleName();
        }
    }
}
