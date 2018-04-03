package kta999.entertainment.panamamonk;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.Profile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    BottomNavigationView navigation;
    FrameLayout frameLayout;
    Fragment fragment;
    private FragmentManager fragmentManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        fab = findViewById(R.id.fab);
        navigation = findViewById(R.id.navigation);
        frameLayout = findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();

        isAdmin = checkAdmin();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Feed");

        fab.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                getSupportActionBar().setTitle("News Feed");
                                fragment = new NewsFragment();
                                if (isAdmin)
                                    fab.setVisibility(View.VISIBLE);
                                break;
                            case R.id.navigation_dashboard:
                                getSupportActionBar().setTitle("Read through categories");
                                fragment = new CategoryFragment();
                                if (isAdmin)
                                    fab.setVisibility(View.VISIBLE);
                                break;
                            case R.id.navigation_notifications:
                                getSupportActionBar().setTitle("Profile and settings");
                                fragment = new MenuFragment();
                                    fab.setVisibility(View.GONE);
                                break;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.frameLayout, fragment).commit();
                        return true;
                    }
                });

    }

    private boolean checkAdmin() {

        if (Profile.getCurrentProfile().getId().equals("1887880987912501")) {

            fab.setVisibility(View.VISIBLE);
            return true;

        } else fab.setVisibility(View.GONE);
        return false;

    }


    @Override
    public void onClick(View view) {
        if(navigation.getSelectedItemId() == R.id.navigation_home)
            startActivity(new Intent(HomeActivity.this, PostActivity.class));
    }
}
