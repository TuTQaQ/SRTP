package com.example.rpm.projectv2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar mBottomNavigationBar;
    private Toolbar mToorBar;
    private DrawerLayout mDrawerLayout;
    private TextView mTextView;

    private boolean mDrawerLayoutState=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        BottomNavigationBarInit();
        ToolBarInit();

        replaceFragment(0);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            public void onDrawerSlide(View drawerView, float slideOffset) {}
            public void onDrawerOpened(View drawerView) {}
            public void onDrawerClosed(View drawerView) {}
            public void onDrawerStateChanged(int newState) {
                mDrawerLayoutState=!mDrawerLayoutState;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //mDrawerLayout.openDrawer(GravityCompat.START);
                mDrawerLayoutState=!mDrawerLayoutState;
                if(mDrawerLayoutState==true)
                    mDrawerLayout.openDrawer(GravityCompat.START);
                else
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void findViews(){
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottomnavigationbar);
        mToorBar=(Toolbar)findViewById(R.id.toorbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mTextView=(TextView)findViewById(R.id.title);
    }

    private void ToolBarInit(){
        setSupportActionBar(mToorBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mTextView.setText("主页");
    }

    private void replaceFragment(int position){
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new InputFragment();
                break;
            case 1:
                fragment=new GraphicFragment();
                break;
            case 2:
                fragment=new MessageFragment();
                break;
            case 3:
                fragment=new PersonFragment();
                break;
            default:
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrameLayout,fragment);
        transaction.commit();
    }
    private void BottomNavigationBarInit(){
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher_round,"采集价格"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_round,"价格统计"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_round,"消息中心"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher_round,"我"))
                .initialise();




        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                setSupportActionBar(mToorBar);
                if(position==0||position==1){
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    getSupportActionBar().setHomeButtonEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                if(position==2||position==3){
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    getSupportActionBar().setHomeButtonEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }

                replaceFragment(position);
            }
            @Override
            public void onTabUnselected(int position) {}
            @Override
            public void onTabReselected(int position) {}
        });
    }
}
