package com.example.happy.hac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/19/2016.
 */

public class hac_hospital_map_path extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hac_hospital_map_path);

        Toolbar  toolbar = (Toolbar) findViewById(R.id.hac_hosptial_map_path_tollbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.hac_hospital_map_path_view_pager_view);
        viewPager.setAdapter(new viewpagerAdapter(getSupportFragmentManager()));

    }
    public class viewpagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<>();

        public viewpagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Fragment getItem(int position) {
            fragmentList.add(new hac_front_fragment());
            return fragmentList.get(position);
        }

        public void add(){

        }
    }
}
