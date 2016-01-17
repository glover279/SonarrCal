package com.example.robert.myapplication3;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Robert on 2016/01/16.
 */
public class JSONparse extends AsyncTask<String, Void, JSONArray>  {


    String result;
    String[] titles=new String[500];
    JSONArray response1 = new JSONArray();
    String title;
    JSONObject finalobject;
    JSONArray jarr;
//    public JSONparse(){doInBackground();}



    @Override

    public JSONArray doInBackground(String... params) {

        URL url;
        HttpURLConnection urlConnection = null;


        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if(1==1){
                String responseString = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", responseString);
                response1 = new JSONArray(responseString);
            }else{
                Log.v("CatalogClient", "Response code:"+ responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Jsonshit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(urlConnection != null)
                urlConnection.disconnect();
        }
System.out.println("response!");


        return response1;
    }
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("YESS "+response.toString());

        return response.toString();
    }

public void Jsonshit() throws JSONException {

    for (int i = 0; i < response1.length(); i++) {
        JSONObject jsonobject = response1.getJSONObject(i);
        String title = jsonobject.getString("title");
        //String url = jsonobject.getString("url");
        titles[i]=title;
        System.out.println("I VAL: "+i+titles[i]);
        System.out.println("TIIIIIITTTTTLLLLEEEE: "+title);
        System.out.println("11112111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111: "+titles[i]);
//MainActivity ma=new MainActivity();
        MainActivity.getData();
    }

}

public JSONArray getJSON() throws JSONException {return jarr; }

    public String getTitle() {return title;}
    public String[] getTitles() {
        return titles;
    }
    public String getTitle1(){return titles[0];}
    public JSONArray getResponse1() {
        return response1;
    }
}





