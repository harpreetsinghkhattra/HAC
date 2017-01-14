package com.example.happy.hac;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/19/2016.
 */

public class hac_login_hosptial_admin extends Fragment {
    Button button;
    EditText username, password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hac_login_hospital, container, false);

        button = (Button) view.findViewById(R.id.hac_login_fragment_button1);
        username = (EditText) view.findViewById(R.id.hac_login_fragment_username);
        password = (EditText) view.findViewById(R.id.hac_login_fragment_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getLoginAuth().execute();
            }
        });
        return view;
    }

    public class getLoginAuth extends AsyncTask{
        @Override
        protected void onPreExecute() {
            button.setText("Loading...");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("user", username.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("pass", password.getText().toString()));

            JSONObject jsonObject = hac_json_parser.adminLogin("http://harpreetsingh.s156.eatj.com/HAC/hac_login_auth.jsp", "post",  nameValuePairs);

            try {
                String str = jsonObject.getString("auth");
                System.out.println("the value of auth is--------------------------------------------------------------------------------------"+str);
                if(str.equalsIgnoreCase("ok")){
                    Intent intent = new Intent(getContext(), hacListOfCustomers.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                }else{
                    System.out.println("the value of auth is----------------------------------------------------"+str);
                   // Toast.makeText(getContext(), "username and password Incorrect", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            button.setText("LogIn");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        button.setText("LogIn");
    }
}
