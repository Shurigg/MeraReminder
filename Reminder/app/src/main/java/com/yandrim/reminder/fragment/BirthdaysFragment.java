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

import java.util.List;

public class BirthdaysFragment extends AbstractTabsFragment{
    private static final int LAYOUT = R.layout.fragment_birthdays;
    private RemindListAdapter adapter;

    private List<RemindDTO> data;

    public static BirthdaysFragment getInstance(Context context, List<RemindDTO> data){
        Bundle args = new Bundle();
        BirthdaysFragment fragment = new BirthdaysFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_birthdays));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager((context)));
        adapter = new RemindListAdapter(data);
        rv.setAdapter(adapter);
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<RemindDTO> data) {
        this.data = data;
    }

    public void refreshData(List<RemindDTO> data){
        if(adapter!=null){
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
    }
}
