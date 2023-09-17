package com.example.cive_field_cup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TimetableFragment extends Fragment {
   TextInputLayout  tableDate,tableTeams,tableGroud,tableDay,tableTime;
   Button uploadBtn;

   private StringRequest stringRequest;
   private RequestQueue requestQueue;
   private String TimeTableURL="http://192.168.43.33/field/time_table.php";


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        tableDate = view.findViewById(R.id.timetable_date);
        tableDay = view.findViewById(R.id.timetable_match_day);
        tableGroud = view.findViewById(R.id.timetable_ground);
        tableTime = view.findViewById(R.id.timetable_time);
        tableTeams = view.findViewById(R.id.timetable_team);

        uploadBtn = view.findViewById(R.id.timetable_upload);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 newTimeTable();
            }
        });

        return view;
    }

    private void newTimeTable() {
        final String date = tableDate.getEditText().getText().toString();
        final String day = tableDay.getEditText().getText().toString();
        final String ground = tableGroud.getEditText().getText().toString();
        final String time = tableTime.getEditText().getText().toString();
        final String teams = tableTeams.getEditText().getText().toString();


        if (teams.isEmpty()) {
            tableTeams.getEditText().setError("Team name is required");
            tableTeams.getEditText().requestFocus();
            return;
        }

        if (date.isEmpty()) {
            tableDate.getEditText().setError("date is required");
            tableDate.getEditText().requestFocus();
            return;
        }

        if (ground.isEmpty()) {
            tableGroud.getEditText().setError("ground name is required");
            tableGroud.getEditText().requestFocus();
            return;
        }

        stringRequest = new StringRequest(Request.Method.POST, TimeTableURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");
                            if(results.equals("1")){
                                tableDate.getEditText().setText("");
                                tableDay.getEditText().setText("");
                                tableTeams.getEditText().setText("");
                                tableTime.getEditText().setText("");

                                Toast.makeText(getContext(), jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("date", date);
                params.put("day", day);
                params.put("time", time);
                params.put("teams", teams);
                params.put("ground", ground);

                return params;
            }

            ;

        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}