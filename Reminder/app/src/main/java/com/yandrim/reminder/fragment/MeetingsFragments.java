package com.yandrim.reminder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandrim.reminder.R;
import com.yandrim.reminder.adapter.RemindListAdapter;
import com.yandrim.reminder.dto.RemindDTO;

import java.util.ArrayList;
import java.util.List;

public class MeetingsFragments extends AbstractTabsFragment{
    private static final int LAYOUT = R.layout.fragment_meetings;

    public static MeetingsFragments getInstance(Context context){
        Bundle args = new Bundle();
        MeetingsFragments fragment = new MeetingsFragments();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_meetings));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager((context)));
        rv.setAdapter(new RemindListAdapter(createMockRemindListData()));
        return view;
    }

    private List<RemindDTO> createMockRemindListData() {
        List<RemindDTO> data = new ArrayList<>();
        data.add(new RemindDTO("Встреча 1"));
        data.add(new RemindDTO("Встреча 2"));
        data.add(new RemindDTO("Встреча 3"));
        data.add(new RemindDTO("Встреча 4"));
        data.add(new RemindDTO("Встреча 5"));
        data.add(new RemindDTO("Встреча 6"));
        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
