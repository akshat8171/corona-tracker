package com.akshatgarg.corona_tracker.ui.home;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.akshatgarg.corona_tracker.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private RequestQueue queue;
    GridLayout gridLayout;
    public TextView t_case,t_recov , t_death , t_active , n_case , n_recover , n_death;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.india, container, false);
        queue = Volley.newRequestQueue(root.getContext());
        t_case = (TextView) root.findViewById(R.id.t_case);
        t_death = (TextView) root.findViewById(R.id.t_death);
        t_recov = (TextView) root.findViewById(R.id.t_recover);
        t_active = (TextView) root.findViewById(R.id.t_active);
        n_case = (TextView) root.findViewById(R.id.n_case);
        n_recover = (TextView) root.findViewById(R.id.n_recov);
        n_death = (TextView) root.findViewById(R.id.n_death);
        JsonObjectRequest stringRequest = updateValues();
        queue.add(stringRequest);
        return root;
    }
    private JsonObjectRequest updateValues(){
        String url = "https://corona.lmao.ninja/v2/all";
        SharedPreferences sh = getActivity().getSharedPreferences("world_data_set", Context.MODE_PRIVATE);
        final int pre_total =sh.getInt("pre_total",0);
        final int pre_active =sh.getInt("pre_active",0);
        final int pre_recover =sh.getInt("pre_recover",0);
        final int pre_death =sh.getInt("pre_death",0);
        final int pre_n_total =sh.getInt("pre_n_total",0);
        final int pre_n_recover =sh.getInt("pre_n_recov",0);
        final int pre_n_death =sh.getInt("pre_n_death",0);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int str_total = response.getInt("cases");
                    int str_active = response.getInt("active");
                    int str_recover = response.getInt("recovered");
                    int str_death = response.getInt("deaths");
                    int str_n_death = response.getInt("todayDeaths");
                    int str_n_total = response.getInt("todayCases");
                    int str_n_recover = response.getInt("todayRecovered");
                    ValueAnimator animator = new ValueAnimator();
                    animator.setObjectValues(pre_total, str_total);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_case.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator.setDuration(5000);
                    animator.start();
                    ValueAnimator animator1 = new ValueAnimator();
                    animator1.setObjectValues(pre_active, str_active);
                    animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_active.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator1.setDuration(5000);
                    animator1.start();
                    ValueAnimator animator2 = new ValueAnimator();
                    animator2.setObjectValues(pre_recover, str_recover);
                    animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_recov.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator2.setDuration(5000);
                    animator2.start();
                    ValueAnimator animator3 = new ValueAnimator();
                    animator3.setObjectValues(pre_death, str_death);
                    animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            t_death.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                    });
                    animator3.setDuration(5000);
                    animator3.start();
                    ValueAnimator animator4 = new ValueAnimator();
                    animator4.setObjectValues(pre_n_total, str_n_total);
                    animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            n_case.setText("[+"+String.valueOf(animation.getAnimatedValue())+"]");
                        }
                    });
                    animator4.setDuration(5000);
                    animator4.start();
                    ValueAnimator animator5 = new ValueAnimator();
                    animator5.setObjectValues(pre_n_recover, str_n_recover);
                    animator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            n_recover.setText("[+"+String.valueOf(animation.getAnimatedValue())+"]");
                        }
                    });
                    animator5.setDuration(5000);
                    animator5.start();
                    ValueAnimator animator6 = new ValueAnimator();
                    animator6.setObjectValues(pre_n_death, str_n_death);
                    animator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            n_death.setText("[+"+String.valueOf(animation.getAnimatedValue())+"]");
                        }
                    });
                    animator6.setDuration(5000);
                    animator6.start();
                    SharedPreferences sh = getActivity().getSharedPreferences("world_data_set", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sh.edit();
                    myEdit.putInt("pre_total",str_total);
                    myEdit.putInt("pre_active",str_active);
                    myEdit.putInt("pre_recover",str_recover);
                    myEdit.putInt("pre_death",str_death);
                    myEdit.putInt("pre_n_total",str_n_total);
                    myEdit.putInt("pre_n_recov",str_n_recover);
                    myEdit.putInt("pre_n_death",str_n_death);
                    myEdit.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Network Unavailable",Toast.LENGTH_LONG).show();
            }
        });
        Log.d(request.toString(),"mess");
        return request;
    }
}