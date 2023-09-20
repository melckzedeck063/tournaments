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

import com.android.volley.AuthFailureError;
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


public class MatchresultsFragment extends Fragment {
 TextInputLayout match, red_Cards,yellow_cards,match_goals,valuable_player;
 Button submitBtn;

 private RequestQueue requestQueue;
 private StringRequest stringRequest;
 private String ResultsURL = "http://192.168.43.33/field/match_result.php";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_matchresults, container, false);

        match = view.findViewById(R.id.match);
        red_Cards = view.findViewById(R.id.red_cards);
        yellow_cards = view.findViewById(R.id.yellow_cards);
        match_goals = view.findViewById(R.id.match_result_goals);
        valuable_player = view.findViewById(R.id.valuable_player);

        submitBtn = view.findViewById(R.id.match_result_submit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadResults();
            }
        });

        return view;
    }

    private void uploadResults(){
        final String game = match.getEditText().getText().toString();
        final String goals = match_goals.getEditText().getText().toString();
        final String redCards = red_Cards.getEditText().getText().toString();
        final String  yellowCards =  yellow_cards.getEditText().getText().toString();
        final String MVP = valuable_player.getEditText().getText().toString();

        if(game.isEmpty()){
            match.getEditText().setError("This is a required field");
            match.getEditText().requestFocus();
            return;
        }
        if(goals.isEmpty()){
            match_goals.getEditText().setError("This is a required field");
            match_goals.getEditText().requestFocus();
            return;
        }
        if(MVP.isEmpty()){
            valuable_player.getEditText().setError("This is a required field");
            valuable_player.getEditText().requestFocus();
            return;
        }

        stringRequest = new StringRequest(Request.Method.POST, ResultsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=  new JSONObject(response);
                            String results = jsonObject.getString("success");
                            if(results.equals("1")){
                                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                                valuable_player.getEditText().setText("");
                                match.getEditText().setText("");
                                match_goals.getEditText().setText("");
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
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params =  new HashMap<>();
                params.put("match",game);
                params.put("results", goals);
                params.put("yellow_cards", yellowCards);
                params.put("red_cards", redCards);
                params.put("valuable_player", MVP);

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}