package com.akshatgarg.corona_tracker.ui.home;

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
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String str_cases = response.getString("cases");
                    String str_active = response.getString("active");
                    String str_recover = response.getString("recovered");
                    String str_death = response.getString("deaths");
                    String str_n_death = response.getString("todayDeaths");
                    String str_n_case = response.getString("todayCases");
                    String str_n_recov = response.getString("todayRecovered");
                    t_case.setText(str_cases);
                    t_active.setText(str_active);
                    t_death.setText(str_death);
                    t_recov.setText(str_recover);
                    n_case.setText(str_n_case);
                    n_recover.setText(str_n_recov);
                    n_death.setText(str_n_death);
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
}