package com.demo.task.taskmanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPaper = (ViewPager) findViewById(R.id.viewPaper);
        tabLayout.setupWithViewPager(viewPaper);
        viewPaper.setAdapter(new TimeSlidePagerAdapter(getSupportFragmentManager()));
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class TimeSlidePagerAdapter extends FragmentStatePagerAdapter {

        public TimeSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ListTaskFragment fragment = new ListTaskFragment();
            fragment.setPosition(position);
            fragment.setCurrentDate(Utils.getDateByPosition(position));
            return fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.days_in_week)[position];
        }
    }
}
