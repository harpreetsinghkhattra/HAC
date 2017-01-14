package com.example.happy.hac;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Happy on 10/16/2016.
 */

public class hac_json_parser {

    public static JSONObject getPathLocation(String method, String url){
        InputStream inputStream;
        JSONObject object = null;
        if(method.equalsIgnoreCase("post")){
            try{

                DefaultHttpClient http = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
               // post.setEntity(new UrlEncodedFormEntity(method));

                HttpResponse response = http.execute(post);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String str = null;
                while((str = reader.readLine())!=null){
                    builder.append(str);
                }
                str = builder.toString();

                object = new JSONObject(str);
                System.out.println("the url of output is : "+str);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return object;
    }

    public static JSONObject getHosptialAdress(String method, String url){
        InputStream inputStream;
        JSONObject object = null;
        if(method.equalsIgnoreCase("post")){
            try{

                DefaultHttpClient http = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                // post.setEntity(new UrlEncodedFormEntity(method));

                HttpResponse response = http.execute(post);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String str = null;
                while((str = reader.readLine())!=null){
                    builder.append(str);
                }
                str = builder.toString();

                object = new JSONObject(str);
                System.out.println("the url of output is : "+str);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return object;
    }

    public static JSONObject adminLogin(String url, String method, List<NameValuePair> params){
            JSONObject jobj = null;
            InputStream is = null;
            String line = null;


        try {
                if(method == "post") {
                    DefaultHttpClient http = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);
                    post.setEntity(new UrlEncodedFormEntity(params));

                    HttpResponse response = http.execute(post);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-8"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine())!= null){
                    sb.append(line +"\n");
                }
                line = sb.toString();
                System.out.println("the value of st is = "+line);
            }
            catch (Exception e){
                System.out.println("the value of error is"+e);
                e.printStackTrace();
            }
            try {
                jobj = new JSONObject(line);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return jobj;
    }
}
