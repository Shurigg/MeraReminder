package com.yandrim.reminder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.yandrim.reminder.adapter.TabsFragmentAdapter;
import com.yandrim.reminder.dto.RemindDTO;
import com.yandrim.reminder.fragment.AbstractTabsFragment;
import com.yandrim.reminder.fragment.CustomDialogFragment;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabsFragmentAdapter adapter;

    private RemindDTO mRemindDTO;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);

    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionNotificationItem:
                        showNotificationTab();
                }
                return false;
            }
        });
    }

    private void initTabs() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
            adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
            viewPager.setOffscreenPageLimit(adapter.getCount());
            viewPager.setAdapter(adapter);
            TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager);
        }

    private void showNotificationTab(){
            viewPager.setCurrentItem(Constants.TAB_TWO);
        }

    public void setNewRemind(View view){
        new CustomDialogFragment().show(getSupportFragmentManager(), "remind");
    }

    public void deleteReminder(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Удалить напоминание?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int tab = viewPager.getCurrentItem();
                                AbstractTabsFragment fragment = (AbstractTabsFragment) adapter.getItem(type);
                                if (fragment != null) {
                                    //fragment.deleteRemind(номер кард вью);
                                }
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setRemindDTO(RemindDTO dto, int type) {
        mRemindDTO = dto;
        this.type = type;

        AbstractTabsFragment fragment = (AbstractTabsFragment) adapter.getItem(type);
        if (fragment != null) {
            fragment.addData(mRemindDTO);
        }
    }
}
