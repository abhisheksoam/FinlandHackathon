package example.com.hospitalapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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

    FrameLayout fragment;

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
        fragment = (FrameLayout) findViewById(R.id.fragment);

        which = getIntent().getIntExtra("WHICH",LoginActivity.DOC_FRAG);

        if(which == LoginActivity.DOC_FRAG)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new DocFragment()).commit();// load doc fragment;
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new PersonFragment()).commit();// load person fragment;

        initNavigationDrawer();
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
}
