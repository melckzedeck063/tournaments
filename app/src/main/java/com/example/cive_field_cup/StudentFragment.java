package com.example.cive_field_cup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class StudentFragment extends Fragment {

    private TextInputLayout studentIdLayout, firstNameLayout, middleNameLayout, lastNameLayout, emailLayout, passwordLayout, adminIdLayout;
    private RequestQueue requestQueue;
    Button register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        // Initialize TextInputLayouts
        studentIdLayout = view.findViewById(R.id.student_id);
        firstNameLayout = view.findViewById(R.id.student_fname);
        middleNameLayout = view.findViewById(R.id.student_m_name);
        lastNameLayout = view.findViewById(R.id.student_l_name);
        emailLayout = view.findViewById(R.id.student_email);
        passwordLayout = view.findViewById(R.id.student_password);
        adminIdLayout = view.findViewById(R.id.admin_id);
        register = view.findViewById(R.id.student_submit);


        // Initialize RequestQueue for Volley
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataIntoDatabase();
            }
        });


        return view;
    }

    private void insertDataIntoDatabase() {
        String studentId = studentIdLayout.getEditText().getText().toString();
        String firstName = firstNameLayout.getEditText().getText().toString();
        String middleName = middleNameLayout.getEditText().getText().toString();
        String lastName = lastNameLayout.getEditText().getText().toString();
        String email = emailLayout.getEditText().getText().toString();
        String password = passwordLayout.getEditText().getText().toString();
        String adminId = adminIdLayout.getEditText().getText().toString();
        // Define your PHP script URL
        String phpScriptUrl = "http://192.168.43.33/field/student.php";

        // Create a StringRequest to make a POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, phpScriptUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");
                            String message = jsonResponse.getString("message");

                            if (success.equals("1")) {
                                studentIdLayout.getEditText().setText("");
                                firstNameLayout.getEditText().setText("");
                                middleNameLayout.getEditText().setText("");
                                lastNameLayout.getEditText().setText("");
                                emailLayout.getEditText().setText("");
                                passwordLayout.getEditText().setText("");
                                adminIdLayout.getEditText().setText("");

                                Toast.makeText(getContext(), message.toString(), Toast.LENGTH_SHORT).show();
                                // Data insertion was successful
                                // Handle success, e.g., show a success message
                            } else {
                                // Data insertion failed
                                Toast.makeText(getContext(),message.toString(),Toast.LENGTH_LONG).show();
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
                params.put("STUDENTS_ID", studentId);
                params.put("FIRST_NAME", firstName);
                params.put("MIDDLE_NAME", middleName);
                params.put("LAST_NAME", lastName);
                params.put("EMAIL", email);
                params.put("PASSWORD", password);
                params.put("ADMIN_ID", adminId);
                return params;
            }



        };

        // Add the request to the RequestQueue
        requestQueue  =  Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
