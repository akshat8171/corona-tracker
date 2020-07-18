package com.akshatgarg.corona_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.akshatgarg.corona_tracker.ui.dashboard.example_item;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    public RequestQueue queue;
    public ArrayList<example_item> exampleList;
    public DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        queue = Volley.newRequestQueue(this);
        JsonArrayRequest stringRequest = fillStatelsit();
        queue.add(stringRequest);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.\
        BottomNavigationView b_navView = findViewById(R.id.bottom_navigation_view);
        drawer = findViewById(R.id.drawer_layout);
        findViewById(R.id.menu_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView d_navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(b_navView, navController);
        d_navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                switch (id){
                    case R.id.nav_creator:
                        Toast.makeText(MainActivity.this, "yess", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_tof:
                        Toast.makeText(MainActivity.this, "yess", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,terms_of_use.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_pri:
                        Toast.makeText(MainActivity.this, "yess", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_ver:
                        Toast.makeText(MainActivity.this, "yess", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_rate_us:
                        Toast.makeText(MainActivity.this, "yess", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }

        });
    }
    public JsonArrayRequest fillStatelsit(){
        String url = "https://api.covidindiatracker.com/state_data.json";
        exampleList = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0 ; i<response.length() ; i++){
                        JSONObject statewise= response.getJSONObject(i);
                        String state = statewise.getString("state");
                        int str_cases = statewise.getInt("confirmed");
                        int str_active = statewise.getInt("active");
                        int str_recover = statewise.getInt("recovered");
                        int str_death = statewise.getInt("deaths");
                        exampleList.add(new example_item(state,str_cases,str_active,str_death,str_recover));
                    }
                    Collections.sort(exampleList, new Comparator<example_item>() {
                        @Override
                        public int compare(example_item example_item, example_item t1) {
                            return 0;
                        }
                    });
                    savedata();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Log.d(request.toString(),"mess");
        return request;
    }
    private void savedata(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exampleList);
        editor.putString("state_wise_list", json);
        editor.apply();
    }
}