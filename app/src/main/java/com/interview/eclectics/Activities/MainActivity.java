package com.interview.eclectics.Activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.interview.eclectics.Adapters.ArticleAdapter;
import com.interview.eclectics.Models.ArticleModel;
import com.interview.eclectics.Services.NetworkService;
import com.interview.eclectics.SharedPrefs.PrefsManager;
import com.interview.eclectics.R;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hossamscott.com.github.backgroundservice.RunService;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    private ArticleAdapter adapter;
    public static List<ArticleModel> model;
    ListView listView;
    ProgressDialog progressDialog;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);


        PrefsManager prefManager = new PrefsManager(getApplicationContext());
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
        }


        listView = (ListView) findViewById(R.id.list);

        model = new ArrayList<ArticleModel>();
        adapter = new ArticleAdapter(this, model);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Do on click activity here
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGithubRepos();
            }
        });


    }

    BroadcastReceiver alarm_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // make iterating request her
            Log.i("alarm_received", "Service is a success");
            System.out.println("Service is a success alaaa ");

            NetworkService networkService = new NetworkService();
            networkService.makeRequest(getApplicationContext());

        }
    };

    BroadcastReceiver responseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // make iterating request her
            Log.i("response_received", "Response is a success");
            System.out.println("Receiving service is working " );


        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(alarm_receiver); // to stop the broadcast when the app is killed
    }


    @Override
    protected void onStart() {
        super.onStart();

        getGithubRepos();

        IntentFilter intentFilter = new IntentFilter("alaram_received");
        registerReceiver(alarm_receiver, intentFilter);


//        responseReceiver


        runService();
    }

    public void getGithubRepos() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Repositories...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://api.github.com/users/TheAlphamerc/repos";
        //replace Gordo2 with your github username to fetch your repositories


        // Fetching my github repositories
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Print String response
                        System.out.println("String request response " + response);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                        try {
                            JSONArray arr = new JSONArray(response);
                            System.out.println("Response here " + arr);

                            parseResponse(arr);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requstQueue.add(stringRequest);

    }


    public void parseResponse(JSONArray response) {

        System.out.println("JSON object called");

        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject objJson = response.getJSONObject(i);



                ArticleModel mod = new ArticleModel();
                mod.setId(objJson.getString("id"));
                mod.setName(objJson.getString("name"));
                mod.setNodeId("node_id");
                mod.setFullName(objJson.getString("full_name"));
                mod.setDateCreated(objJson.getString("created_at"));
                mod.setForks(objJson.getString("forks"));
                mod.setWatchers(objJson.getString("watchers"));
                model.add(mod);
                adapter.notifyDataSetChanged();

            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    //my method to run backgound service
    public void runService() {

        //Set iteration intervals in seconds
        RunService repeat = new RunService(this);
        repeat.call(120, true);
    }

}