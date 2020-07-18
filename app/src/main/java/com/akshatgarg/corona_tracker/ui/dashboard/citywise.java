package com.akshatgarg.corona_tracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akshatgarg.corona_tracker.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class citywise extends AppCompatActivity {
    private RequestQueue queue1,queue2;
    public TextView t_case,t_recov , t_death , t_active , state_name;
    private ExampleAdapter adapter;
    private ArrayList<example_item> citylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citywise);
        t_case = (TextView) findViewById(R.id.t_case);
        t_death = (TextView) findViewById(R.id.t_death);
        t_recov = (TextView) findViewById(R.id.t_recover);
        t_active = (TextView)findViewById(R.id.t_active);
        state_name = (TextView)findViewById(R.id.state_name);
        Intent intent = getIntent();
        String str = intent.getStringExtra("state_name");
        String total = intent.getStringExtra("total");
        String active = intent.getStringExtra("active");
        String recover = intent.getStringExtra("recover");
        String death = intent.getStringExtra("death");
        t_case.setText(""+total);
        t_active.setText(""+active);
        t_death.setText(""+death);
        t_recov.setText(""+recover);
        state_name.setText(str);
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("district_wise", null);
        Type type = new TypeToken<ArrayList<example_item>>() {}.getType();
        citylist = gson.fromJson(json, type);
        if (citylist == null) {
            citylist = new ArrayList<>();
        }
        setUpRecyclerView();
        EditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);
            }
        });
    }
    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(citywise.this, citylist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}