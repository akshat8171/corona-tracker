package com.akshatgarg.corona_tracker.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akshatgarg.corona_tracker.R;
import com.akshatgarg.corona_tracker.ui.dashboard.DashboardFragment;
import com.akshatgarg.corona_tracker.ui.dashboard.ExampleAdapter;
import com.akshatgarg.corona_tracker.ui.dashboard.example_item;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private news_adapter adapter;
    private ArrayList<news_item> newsList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.about, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("news_list", null);
        Type type = new TypeToken<ArrayList<news_item>>() {}.getType();
        newsList = gson.fromJson(json, type);
        if (newsList == null)
        {
            newsList = new ArrayList<>();
        }
        setUpRecyclerView(root);
        return root;
    }
    private void setUpRecyclerView(View root) {
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new news_adapter(root.getContext(),newsList );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}