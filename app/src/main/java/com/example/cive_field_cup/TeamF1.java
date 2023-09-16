package com.example.cive_field_cup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


public class TeamF1 extends Fragment {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String teamsURL = "http://192.168.43.33/field/teams.php";
    private TeamAdapter teamAdapter;
    private ArrayList<TeamModel> teamModelArrayList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_f1, container, false);

        recyclerView = view.findViewById(R.id.team_recycler);
        teamModelArrayList = new ArrayList<>(); // Your data source

// Create and set the adapter
        teamAdapter = new TeamAdapter(teamModelArrayList, getContext());
        recyclerView.setAdapter(teamAdapter);

// Set the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        getTeamsData();

        return view;
    }

    private void getTeamsData() {
        stringRequest = new StringRequest(Request.Method.GET, teamsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");
                            if (results.equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("teams_data");

                                teamModelArrayList = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    teamModelArrayList.add(new TeamModel(
                                            jsonObject1.getInt("team_id"),
                                            jsonObject1.getString("team"),
                                            jsonObject1.getString("coach"),
                                            jsonObject1.getInt("points"),
                                            jsonObject1.getInt("position"),
                                            jsonObject1.getInt("goals")

                                    ));
                                }

                                teamAdapter = new TeamAdapter(teamModelArrayList, getContext());
                                recyclerView.setAdapter(teamAdapter);
//
//                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//                                recyclerView.setNestedScrollingEnabled(false);

                            } else {
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
