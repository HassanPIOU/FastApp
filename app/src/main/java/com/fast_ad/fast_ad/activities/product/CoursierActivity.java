package com.fast_ad.fast_ad.activities.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.fragments.coursier.ConfigFragment;
import com.fast_ad.fast_ad.fragments.coursier.ConfirmFragment;
import com.fast_ad.fast_ad.fragments.coursier.MapFragment;
import com.google.android.material.tabs.TabLayout;

public class CoursierActivity extends AppCompatActivity {


    private final String LOG_TAG = CoursierActivity.class.getSimpleName();

    private final String[] PAGE_TITLES = new String[] {
            "Etape 1",
            "Etape 2",
            "Etape 3"
    };

    // The fragments that are used as the individual pages
    private final Fragment[] PAGES = new Fragment[] {
            new ConfigFragment(),
            new ConfirmFragment(),
            new MapFragment()
    };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursier);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

    String confirmFragment;

    public void setConfirmFragment(String t){
        confirmFragment = t;
    }

    public String getConfirmFragment(){
        return confirmFragment;
    }
}
