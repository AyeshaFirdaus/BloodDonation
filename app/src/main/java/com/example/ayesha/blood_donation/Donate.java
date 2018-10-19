package com.example.ayesha.blood_donation;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Donate extends AppCompatActivity {
    public ArrayAdapter adapter;
    //private String REGISTER_URL = "http://192.168.43.47/blood/list.php";
    public String a[]=new String[1000];
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate);
        lv = (ListView)findViewById(R.id.listView);
        lv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);


        //String a[]=new String[]{"Arun-PC / 1.0.0.1","Abhishek-PC / 172.0.0.2","255.255.255.255","10.255.52.45","192.192.56.45"};
        Toast.makeText(this,MainActivity.user,Toast.LENGTH_SHORT).show();
        //adapter = new ArrayAdapter(Donate.this, R.layout.listview_style, a);

        listuser(MainActivity.user);
        lv.setAdapter(adapter);

    }

    private void listuser(final String username) {

        class ListAsync extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;
            int c = 0;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loadingDialog = ProgressDialog.show(Donate.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                //Toast.makeText(Donate.this,"dsadsa",Toast.LENGTH_SHORT).show();
                Log.d("dsa", "dsa");
                String uname = params[0];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));

                String result = null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.47/blood/list.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                        a[c] = line;
                        //Toast.makeText(Donate.this,sb,Toast.LENGTH_SHORT).show();

                        c++;
                    }

                    adapter = new ArrayAdapter(Donate.this, R.layout.listview_style, a);
                    result = sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
                Log.d("dsa", String.valueOf(a[0]));
                //adapter = new ArrayAdapter(Donate.this, R.layout.listview_style, a);

                return result;
            }
            @Override
            protected void onPostExecute(String result){

            }
        }
        ListAsync la = new ListAsync();
        la.execute(username);

    }
            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate, menu);
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
}