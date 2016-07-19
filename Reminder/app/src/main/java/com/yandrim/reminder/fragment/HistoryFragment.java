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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryFragment extends AbstractTabsFragment{
    private static final int LAYOUT = R.layout.fragment_history;

    private Context context;

    public static HistoryFragment getInstance(Context context){
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_history));

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
        RemindDTO history = new RemindDTO("ДР 1");
        history.setRemindDate(new Date());
        history.setDescription("Как подарок нужно купить резиновую женщину\nПозвать коллег\nАлкоголь приносить с собой");
        data.add(history);
        history = new RemindDTO("Встреча 1");
        history.setRemindDate(new Date());
        history.setDescription("Принести с собой документы");
        data.add(history);
        history = new RemindDTO("ДР 2");
        history.setRemindDate(new Date());
        history.setDescription("Собрать на подарок с друзей до 16го\nЗаказать планшет на собранные деньги");
        data.add(history);
        history = new RemindDTO("Дело 1");
        history.setRemindDate(new Date());
        data.add(history);
        history = new RemindDTO("Дело 2");
        history.setRemindDate(new Date());
        data.add(history);
        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
