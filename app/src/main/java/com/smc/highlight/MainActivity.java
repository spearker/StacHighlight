package com.smc.highlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.smc.highlight.Fragment.Fragment_Search;
import com.smc.highlight.Fragment.Fragment_Home;
import com.smc.highlight.Fragment.Fragment_Mypage;
import com.smc.highlight.Fragment.Fragment_Setting;
import com.smc.highlight.Fragment.Highlighting.HighlightingActivity;
import com.smc.highlight.Fragment.Post.PostCamera;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{
    public static final int page1 = 0;
    public static final int page2 = 1;
    public static final int page3 = 2;
    public static final int page4 = 3;

    private FloatingActionButton fab;

    public static final int[] TAB_ARRAY = {page1, page2, page3, page4};

    private BottomNavigationView navigation;
    private DatabaseReference myRef; //database 정의
    private Toolbar main_toolbar;
    private ViewPager viewpager;
    private PageAdapter pageAdapter;
    private MenuItem prevBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setSupportActionBar(main_toolbar);

        //mTextMessage = (TextView) findViewById(R.id.message); textview 설정 인데 없어짐

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);

        pageAdapter = new PageAdapter(getSupportFragmentManager());

        viewpager = (ViewPager) findViewById(R.id.viewPager);
        viewpager.addOnPageChangeListener(this);
        viewpager.setAdapter(pageAdapter);

    }

    public void highlightingClicked(View view){
        Intent intent = new Intent(getApplicationContext(),HighlightingActivity.class);
        startActivity(intent);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    viewpager.setCurrentItem(0);
                    return true;
                case R.id.bottom_search:
                    viewpager.setCurrentItem(1);
                    return true;
                case R.id.bottom_mypage:
                    viewpager.setCurrentItem(2);
                    return true;
                case R.id.bottom_setting:
                    viewpager.setCurrentItem(3);
                      return true;
            }
            return false;
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(prevBottomNavigation != null){
            prevBottomNavigation.setChecked(false);
        }
        prevBottomNavigation = navigation.getMenu().getItem(position);
        prevBottomNavigation.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class PageAdapter extends FragmentStatePagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }


    
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case page1:
                    fragment = Fragment_Home.newInstance(page1);
                    break;
                case page2:
                    fragment = Fragment_Search.newInstance(page2);
                    break;
                case page3:
                    fragment = Fragment_Mypage.newInstance(page3);
                    break;
                case page4:
                    fragment = Fragment_Setting.newInstance(page4);
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_ARRAY.length;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.fab :
                intent = new Intent(this, PostCamera.class);
                startActivity(intent);
                break;
        }
    }
}
