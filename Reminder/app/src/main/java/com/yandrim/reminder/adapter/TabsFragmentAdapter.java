package com.yandrim.reminder.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yandrim.reminder.R;
import com.yandrim.reminder.dto.RemindDTO;
import com.yandrim.reminder.fragment.AbstractTabsFragment;
import com.yandrim.reminder.fragment.BirthdaysFragment;
import com.yandrim.reminder.fragment.HistoryFragment;
import com.yandrim.reminder.fragment.MeetingsFragments;
import com.yandrim.reminder.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabsFragment> tabs;
    private Context context;
    private BirthdaysFragment birthdaysFragment;
    private List<RemindDTO> data;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.data = new ArrayList<>();
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        birthdaysFragment = BirthdaysFragment.getInstance(context, data);
        tabs = new HashMap<>();
        tabs.put(0, MeetingsFragments.getInstance(context));
        tabs.put(1, TodoFragment.getInstance(context));
        tabs.put(2, birthdaysFragment);
        tabs.put(3, HistoryFragment.getInstance(context));
    }

    public void setData(List<RemindDTO> data) {
        this.data = data;
        birthdaysFragment.refreshData(data);
    }
}
