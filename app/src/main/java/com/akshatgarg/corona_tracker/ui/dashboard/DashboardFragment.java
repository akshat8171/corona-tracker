package com.akshatgarg.corona_tracker.ui.dashboard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akshatgarg.corona_tracker.MainActivity;
import com.akshatgarg.corona_tracker.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DashboardFragment extends Fragment implements ExampleAdapter.OnItemClickListener{
    private RequestQueue queue1,queue2;
    public TextView t_case,t_recov , t_death , t_active ,n_case , n_recov ,n_death;
    private ExampleAdapter adapter;
    private ArrayList<example_item> exampleList , citylist;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.global, container, false);
        t_case = (TextView) root.findViewById(R.id.t_case);
        t_death = (TextView) root.findViewById(R.id.t_death);
        t_recov = (TextView) root.findViewById(R.id.t_recover);
        t_active = (TextView) root.findViewById(R.id.t_active);
        n_case = (TextView) root.findViewById(R.id.n_confirmed);
        n_recov = (TextView) root.findViewById(R.id.n_recovered);
        n_death = (TextView) root.findViewById(R.id.n_death);
        SharedPreferences preferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("state_wise_list", null);
        Type type = new TypeToken<ArrayList<example_item>>() {}.getType();
        exampleList = gson.fromJson(json, type);
        if (exampleList == null)
        {
            exampleList = new ArrayList<>();
        }
        setUpRecyclerView(root);

        queue1 = Volley.newRequestQueue(getActivity());
        JsonObjectRequest stringRequest = updateValues();
        queue1.add(stringRequest);
        EditText editText = root.findViewById(R.id.edit_text);
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
        return root;
    }
    private void setUpRecyclerView(View root) {
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        adapter = new ExampleAdapter(root.getContext(), exampleList);
        adapter.setOnItemClickListener(DashboardFragment.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private JsonObjectRequest updateValues(){
        String url = "https://corona.lmao.ninja/v2/countries/INDIA";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int str_total = response.getInt("cases");
                    int str_active = response.getInt("active");
                    int str_recover = response.getInt("recovered");
                    int str_death = response.getInt("deaths");
                    int str_n_total = response.getInt("todayCases");
                    int str_n_recover = response.getInt("todayRecovered");
                    int str_n_death = response.getInt("todayDeaths");
                    n_case.setText("[+"+str_n_total+"]");
                    n_recov.setText("[+"+str_n_recover+"]");
                    n_death.setText("[+"+str_n_death+"]");
                    ValueAnimator animator = new ValueAnimator();
                    animator.setObjectValues(0, str_total);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_case.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator.setDuration(5000);
                    animator.start();
                    ValueAnimator animator1 = new ValueAnimator();
                    animator1.setObjectValues(0, str_active);
                    animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_active.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator1.setDuration(5000);
                    animator1.start();
                    ValueAnimator animator2 = new ValueAnimator();
                    animator2.setObjectValues(0, str_recover);
                    animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_recov.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator2.setDuration(5000);
                    animator2.start();
                    ValueAnimator animator3 = new ValueAnimator();
                    animator3.setObjectValues(0, str_death);
                    animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_death.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator3.setDuration(5000);
                    animator3.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Log.d(request.toString(),"mess");
        return request;
    }
    @Override
    public void onItemClick(int position) {
        final example_item clickedItem = exampleList.get(position);
        queue2 = Volley.newRequestQueue(getActivity());
        JsonObjectRequest stringRequest = citylist(clickedItem.get_country());
        queue2.add(stringRequest);
        new CountDownTimer(100,1000){
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                Intent detailIntent = new Intent(getActivity(), citywise.class);
                detailIntent.putExtra("state_name",clickedItem.get_country());
                detailIntent.putExtra("total",Integer.toString(clickedItem.total_case()));
                detailIntent.putExtra("active",Integer.toString(clickedItem.active()));
                detailIntent.putExtra("recover",Integer.toString(clickedItem.total_recover()));
                detailIntent.putExtra("death",Integer.toString(clickedItem.total_death()));
                startActivity(detailIntent);
            }
    }.start();
    }
    private JsonObjectRequest citylist(final String state){
        String url = "https://api.covid19india.org/state_district_wise.json";
        citylist = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jresponse = response.getJSONObject(state);
                    JSONObject districts = jresponse.getJSONObject("districtData");
                    for(int i=0 ; i<districts.names().length();i++){
                        JSONObject city = districts.getJSONObject(districts.names().getString(i));
                        int str_cases = city.getInt("confirmed");
                        int str_active = city.getInt("active");
                        int str_recover = city.getInt("recovered");
                        int str_death = city.getInt("deceased");
                        citylist.add(new example_item(districts.names().getString(i),str_cases,str_active,str_death,str_recover));
                    }
                    Collections.sort(citylist, new Comparator<example_item>() {
                        @Override
                        public int compare(example_item t, example_item t1) {
                            return t1.total_case()-t.total_case();
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
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Log.d(request.toString(),"mess");
        return request;
    }
    private void savedata(){
        SharedPreferences a = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = a.edit();
        Gson gson = new Gson();
        String json = gson.toJson(citylist);
        editor.putString("district_wise", json);
        editor.apply();
    }
}