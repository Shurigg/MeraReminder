package com.yandrim.reminder.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.yandrim.reminder.dto.RemindDTO;

public abstract class AbstractTabsFragment extends Fragment{
    private String title;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void addData(RemindDTO remind){
    }
}
