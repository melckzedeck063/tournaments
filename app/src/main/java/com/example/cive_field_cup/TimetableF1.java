package com.example.cive_field_cup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TimetableF1 extends Fragment {

    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private String fixtureURL= "http://192.168.43.33/field/fixtures.php";
    private FixtureAdapter fixtureAdapter;
    private ArrayList<FixturesModel>  fixturesModelArrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_timetable_f1, container, false);

        recyclerView = view.findViewById(R.id.fixture_recycler);

        fixturesModelArrayList = new ArrayList<>();
        fixtureAdapter = new FixtureAdapter(fixturesModelArrayList,getContext());
        recyclerView.setAdapter(fixtureAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        getFixtures();

        return view;
    }

    private void getFixtures(){
        stringRequest = new StringRequest(Request.Method.GET, fixtureURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");

                            if(results.equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("fixtures_data");

                                fixturesModelArrayList =  new ArrayList<>();

                                for (int i =0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                    fixturesModelArrayList.add(new FixturesModel(
                                            jsonObject1.getString("date"),
                                            jsonObject1.getString("day"),
                                            jsonObject1.getString("time"),
                                            jsonObject1.getString("game"),
                                            jsonObject1.getString("ground")
                                    ));

                                }

                                fixtureAdapter = new FixtureAdapter(fixturesModelArrayList, getContext());
                                recyclerView.setAdapter(fixtureAdapter);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                recyclerView.setNestedScrollingEnabled(false);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}