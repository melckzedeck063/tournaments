package com.example.cive_field_cup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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
import java.util.Objects;

public class Login extends Fragment {
Button loginButton;
TextView error;
    TextInputLayout username,password;
MainActivity mainActivity;

private String LoginURL =  "http://192.168.43.33/field/login.php";
private RequestQueue  requestQueue;
private StringRequest stringRequest;


    @SuppressLint({"SetTextI18n", "UseRequireInsteadOfGet"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton=view.findViewById(R.id.login_button);
        username = view.findViewById(R.id.username);
        error = view.findViewById(R.id.error_login);
        password = view.findViewById(R.id.password);

        mainActivity = (MainActivity) getActivity();


//            String username1 = Objects.requireNonNull(username.getEditText()).getText().toString();
//            String password1 = Objects.requireNonNull(password.getEditText()).getText().toString();
//            if(username1.equals("admin")&& !password1.isEmpty()&& password1.length()>=5 && password1.length()<=25)
//            {
//                Intent intent = new Intent(getActivity(),Admin.class);
//                startActivity(intent);
//                Objects.requireNonNull(getActivity()).finish();
//                error.setVisibility(View.INVISIBLE);
//            }
//            else if(username1.equals("student") && !password1.isEmpty()&& password1.length()>=5 && password1.length()<=25)
//            {
//                Intent intent = new Intent(getActivity(),Student.class);
//                startActivity(intent);
//                Objects.requireNonNull(getActivity()).finish();
//                error.setVisibility(View.INVISIBLE);
//            }
//            else if(username1.isEmpty())
//            {
//                username.setError("");
//                error.setVisibility(View.VISIBLE);
//                error.setText("Username is required!");
//            }
//            else if(password1.isEmpty()){
//                password.setError("");
//                error.setVisibility(View.VISIBLE);
//                error.setText("password is required");
//            }
//            else{
//                Toast.makeText(getContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
//            }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        return view;
    }

    private void LoginUser(){
        final String email  = username.getEditText().getText().toString();
        final String passcode = password.getEditText().getText().toString();

        if(email.isEmpty()){
            username.getEditText().setError("Usernamme is required");
            username.getEditText().requestFocus();
            return;
        }
        if(passcode.isEmpty()){
          password.getEditText().setError("Password is required");
          password.getEditText().requestFocus();
          return;
        }

          stringRequest = new StringRequest(Request.Method.POST, LoginURL,
                  new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                          try {
                              JSONObject jsonResponse = new JSONObject(response);
                              String success = jsonResponse.getString("success");
                              String message = jsonResponse.getString("message");

                              if (success.equals("1")) {
                                  Toast.makeText(getContext(),  message.toString(), Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(getActivity(),Student.class);
                                  startActivity(intent);
                                  // Handle success, e.g., show a success message
                              } else {
                                  // Data insertion failed
                                  Toast.makeText(getContext(), message.toString(), Toast.LENGTH_LONG).show();
                                  // Handle failure, e.g., show an error message
                              }
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                  },
                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          // Handle Volley error
                          error.printStackTrace();
                          Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                          // Handle error, e.g., show an error message
                      }
                  }) {
              @Override
              protected Map<String, String> getParams() {
                  // Add parameters to the POST request
                  Map<String, String> params = new HashMap<>();
                  params.put("EMAIL", email);
                  params.put("PASSWORD", passcode);

                  return params;
              }

    };
          requestQueue = Volley.newRequestQueue(getContext());
          requestQueue.add(stringRequest);
}

}