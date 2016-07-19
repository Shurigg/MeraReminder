package com.yandrim.reminder.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandrim.reminder.Constants;
import com.yandrim.reminder.R;
import com.yandrim.reminder.adapter.RemindListAdapter;
import com.yandrim.reminder.dto.RemindDTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingsFragments extends AbstractTabsFragment{
    private static final int LAYOUT = R.layout.fragment_meetings;
    private RemindListAdapter adapter;
    private Context context;
    private List<RemindDTO> data;

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
        data = new ArrayList<>();
        adapter = new RemindListAdapter(data);
        view = inflater.inflate(LAYOUT, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager((context)));
        rv.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                RemindDTO[] list1 = template.getForObject(Constants.URL.GET_MEETINGS, RemindDTO[].class);
                data = Arrays.asList(list1);
                refreshData(data);
            }
        }).start();
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void refreshData(List<RemindDTO> data) {
        if (adapter != null) {
            adapter.setData(data);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void addData(final RemindDTO birthday){
        if (adapter != null) {
            birthday.setId(data.size());
            data.add(birthday);
            adapter.setData(data);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RestTemplate template = new RestTemplate();
                    template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    template.postForObject(Constants.URL.GET_BIRTHDAYS, birthday ,RemindDTO.class);
                }
            }).start();
        }
    }
}
