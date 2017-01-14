package com.example.happy.hac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/24/2016.
 */

public class hacListOfCustomers extends AppCompatActivity {
    String username = null;
    List<String> name = new ArrayList<>();
    ListView view;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haclistofcustomers);

        username = getIntent().getStringExtra("username");
        Toolbar toolbar = (Toolbar) findViewById(R.id.hac_list_of_customers_tollbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
       // bar.setDefaultDisplayHomeAsUpEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);

        new getListofCustomers().execute();

        view = (ListView) findViewById(R.id.hac_list_of_customers_list_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.haclistofcustomersarrayadapter, R.id.hac_list_of_customers_adapter_text_view, name);
        view.setAdapter(arrayAdapter);
    }

    public class getListofCustomers extends AsyncTask<String, String, List<String>>{

        @Override
        protected void onPreExecute() {
            //dialog = new ProgressDialog(getApplicationContext());
            //dialog.setProgress(0);
            //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //dialog.setMessage("Loading, Please Wait");
            //dialog.show();
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String url = "http://harpreetsingh.s156.eatj.com/HAC/hac_official_map_track.jsp";
            String string = null;
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("user", username));
            JSONObject jsonObject = hac_json_parser.adminLogin(url, "post", nameValuePairs);
            try {
               name.add(jsonObject.getString("customer_name"));
                Toast.makeText(hacListOfCustomers.this, "size is :"+name.size(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return name;
        }

        @Override
        protected void onPostExecute(List<String> list) {
            name = list;
            dialog.hide();
        }
    }
}
