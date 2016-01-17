package com.example.robert.myapplication3;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;
    public static String[] temp1=new String[100];
    private RecyclerView recy;
    private Adapter adapter;

    JSONparse jp=new JSONparse();

    EditText editText_url, editText_api;
    Button add_button,button_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            jp.Jsonshit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

          myDb=new DatabaseHelper(this);

        editText_api=(EditText)findViewById(R.id.editText_api);
        editText_url=(EditText)findViewById(R.id.editText_url);
        add_button=(Button)findViewById(R.id.add_button);
        recy=(RecyclerView)findViewById(R.id.recyclelist);
        jp.execute("http://ded.gloversmedia.com:8989/api/calendar/?apikey=0f5efeae4bb54e44a1533af283b9b101");
        try {
            jp.Jsonshit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getData();
        //TextView textView = (TextView)findViewById(R.id.textView);
        //String test=
        //textView.setText(jp.getTitle());
        addData();
        adapter=new Adapter(this,getData());
        recy.setAdapter(adapter);
        recy.setLayoutManager(new LinearLayoutManager(this));
        temp1=jp.getTitles();
        getData();
        jp=new JSONparse();


        //  recy.isShown()=true;

    }
    public static List<Information> getData(){
        List<Information> data=new ArrayList<>();
        String[] temp=new String[100];


        System.out.println("HEERREEE"+temp1[0]);
        if(temp1[0]!=null){
        String[] titles2={temp1[0],"test1","test2"};
        for(int i=0; i<titles2.length; i++) {
            Information current = new Information();
            current.title = titles2[i];
            data.add(current);
        }

        }

   return data; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addData()
    {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted= myDb.insertDataConn(editText_url.getText().toString(),editText_api.getText().toString());
                if(isInserted=true){
                   Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();}
                else{Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();}
                Toast.makeText(MainActivity.this,jp.getTitle(),Toast.LENGTH_LONG).show();


            }
        });


    }

    public void rmData()
    {
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {myDb.clearDB("connect_table");

            }
        });

    }


}
