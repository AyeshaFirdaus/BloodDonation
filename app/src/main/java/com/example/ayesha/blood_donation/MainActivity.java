package com.example.ayesha.blood_donation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonClick (View v) {
        if (v.getId() == R.id.blogin) {
            EditText a = (EditText) findViewById(R.id.TFun);
            String username = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String password = b.getText().toString();


            login(username,password);

//            Intent j = new Intent(this, com.example.ayesha.blood_donation.Display.class);
//            j.putExtra("Username", username);
//            startActivity(j);
        }
    }

    private void login(final String username, String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.47/blood/login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
//                try {
//                String link="192.168.43.47/blood/connection.php";
//                String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8");
//                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
//
//                URL url = new URL(link);
//                URLConnection conn = url.openConnection();
//
//                conn.setDoOutput(true);
//                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//
//                wr.write( data );
//                wr.flush();
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//
//                // Read Server Response
//                while((line = reader.readLine()) != null)
//                {
//                    sb.append(line);
//                    break;
//                }
//                return sb.toString();
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
                //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                return result;
//            }
            }
            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                if(s.equalsIgnoreCase("success")){
//                    Intent intent = new Intent(MainActivity.this, UserProfile.class);
//                    intent.putExtra(USER_NAME, username);
//                    finish();
//                    startActivity(intent);
                    user = username;
                    Intent j = new Intent(MainActivity.this, com.example.ayesha.blood_donation.Display.class);
                    j.putExtra("Username", username);
                    finish();
                    startActivity(j);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }

    public void onButtonsignupClick(View v) {
        if(v.getId() == R.id.bsignup)
        {
            Intent i = new Intent(MainActivity.this,Signup.class);
            startActivity(i);
        }

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
