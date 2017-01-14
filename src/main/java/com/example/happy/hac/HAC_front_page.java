package com.example.happy.hac;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.example.happy.hac.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/12/2016.
 */

public class HAC_front_page extends AppCompatActivity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hac_fornt_page_view_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hac_front_page_tollbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.hac_front_page_view_pager_view);
        viewpagerAdapter adapter = new viewpagerAdapter(getSupportFragmentManager());
        adapter.add(new hac_hospital_fragment(), "Hospital");
        adapter.add(new hac_login_hosptial_admin(),"Login");
        pager.setAdapter(adapter);
        TabLayout tablayout = (TabLayout) findViewById(R.id.hac_front_page_tablayout);
        tablayout.setupWithViewPager(pager);


    }

    public class viewpagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        public viewpagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public void add(Fragment fragment, String string){
            fragmentList.add(fragment);
            stringList.add(string);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
    }
}
