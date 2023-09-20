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

public class ResultsF1 extends Fragment {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String ResultsURL = "http://192.168.43.33/field/results.php";
    private ArrayList<ResulstModel> resulstModelArrayList;
    private ResultsAdapter resultsAdapter;
    private RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results_f1, container, false);

        recyclerView = view.findViewById(R.id.resulst_recycler);

        resulstModelArrayList =  new ArrayList<>();

        resultsAdapter =  new ResultsAdapter(resulstModelArrayList, getContext());
        recyclerView.setAdapter(resultsAdapter);


        getResults();

        return view;
    }

    private void getResults(){
        stringRequest = new StringRequest(Request.Method.GET, ResultsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");

                            if(results.equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("match_results_data");

                                resulstModelArrayList =  new ArrayList<>();

                                for (int i =0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                    resulstModelArrayList.add(new ResulstModel(
                                            jsonObject1.getString("match_played"),
                                            jsonObject1.getString("results"),
                                            jsonObject1.getString("yellow_card"),
                                            jsonObject1.getString("red_card"),
                                            jsonObject1.getString("valuable_player")
                                    ));

                                }

                                resultsAdapter = new ResultsAdapter(resulstModelArrayList, getContext());
                                recyclerView.setAdapter(resultsAdapter);

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