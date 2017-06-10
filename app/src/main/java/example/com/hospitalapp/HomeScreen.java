package example.com.hospitalapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Niitn Khurana on 10-Jun-17.
 */
public class HomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    int which;

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton floatingActionButton;

    int total_fragments = 3;
    String[] tab_titles = {"Tab1","Dashboard","Tab3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        which = getIntent().getIntExtra("WHICH",LoginActivity.DOC_FRAG);

        initNavigationDrawer();

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                if(position == 2)
                    floatingActionButton.hide();
                else{
                    if (floatingActionButton.getVisibility() == View.GONE)
                        floatingActionButton.show();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fab is clicked
            }
        });
    }

    public void initNavigationDrawer() {


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    case R.id.option1:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.option2:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.option3:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.option4:
                        drawerLayout.closeDrawers();
                        break;


                }
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Drawer_open,R.string.Drawer_closed){
            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }
            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0 : fragment = new DummyFragment(); break;
                case 1 :

                    if(which == LoginActivity.DOC_FRAG){
                        fragment = new DocFragment();
                        tab_titles[1] = "DocDash";
                    }
                    else{
                        fragment = new PersonFragment();
                        tab_titles[1] = "PatientDash";
                    }

                    break;

                case 2 : fragment = new DummyFragment(); break;
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:return tab_titles[0];
                case 1:return tab_titles[1];
                case 2:return tab_titles[2];
            }

            return getString(R.string.app_name);
        }

        @Override
        public int getCount() {
            return total_fragments;
        }
    }
}
