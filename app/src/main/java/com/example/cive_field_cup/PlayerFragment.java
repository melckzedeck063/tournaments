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


public class PlayerFragment extends Fragment {

     TextInputLayout playerAge,playerPosition,playerTeam,playerName;
     Button submitButton;

     private RequestQueue requestQueue;
     private StringRequest stringRequest;
     private  String playerURL = "http://192.168.43.33/field/player.php";


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);

       playerAge = view.findViewById(R.id.player_age);
       playerName = view.findViewById(R.id.player_name);
       playerPosition = view.findViewById(R.id.player_position);
       playerTeam = view.findViewById(R.id.player_team);

       submitButton = view.findViewById(R.id.submit_player);

       submitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               registerPlayer();
           }
       });

        return view;
    }

    private void registerPlayer(){
        final String name = playerName.getEditText().getText().toString();
        final String age = playerAge.getEditText().getText().toString();
        final String position = playerPosition.getEditText().getText().toString();
        final String team = playerTeam.getEditText().getText().toString();

        if(name.isEmpty()){
            playerName.getEditText().setError("player name is required");
            playerName.getEditText().requestFocus();
            return;
        }
        if(position.isEmpty()){
            playerPosition.getEditText().setError("player position is required");
            playerPosition.getEditText().requestFocus();
            return;
        }
        if(team.isEmpty()){
            playerTeam.getEditText().setError("player team is required");
            playerTeam.getEditText().requestFocus();
            return;
        }

        stringRequest =  new StringRequest(Request.Method.POST, playerURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");

                            if(results.equals("1")){
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                                playerName.getEditText().setText("");
                                playerAge.getEditText().setText("");


                            }
                            else {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("age",age);
                params.put("position", position);
                params.put("team",team);

                return  params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}