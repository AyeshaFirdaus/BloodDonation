package com.example.ayesha.blood_donation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Ayesha on 28-02-2016.
 */
public class Signup extends Activity {
    private EditText Ename,Eusername,Eemail,Epassword,Ephone,Estate,Ecity,Earea,Eblood_group;
    private String REGISTER_URL = "http://192.168.43.47/blood/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Ename = (EditText) findViewById(R.id.TFname);
        Eusername = (EditText) findViewById(R.id.username);
        Epassword = (EditText) findViewById(R.id.pass1);
        Eemail = (EditText) findViewById(R.id.email);
        Ephone = (EditText) findViewById(R.id.TFphone);
        Estate = (EditText) findViewById(R.id.TFcity);
        Ecity = (EditText) findViewById(R.id.city);
        Earea = (EditText) findViewById(R.id.TFarea);
        Eblood_group = (EditText) findViewById(R.id.TFblood);

    }


    public void signUp(View v) {
        if (v.getId() == R.id.signUp) {
            registerUser();
            //Toast.makeText(this,"dsa",Toast.LENGTH_SHORT).show();
        }

    }
    private void registerUser() {
        String name = Ename.getText().toString().trim().toLowerCase();
        String username = Eusername.getText().toString().trim().toLowerCase();
        String password = Epassword.getText().toString().trim().toLowerCase();
        String email = Eemail.getText().toString().trim().toLowerCase();
        String state = Estate.getText().toString().trim().toLowerCase();
        String city = Ecity.getText().toString().trim().toLowerCase();
        String area = Earea.getText().toString().trim().toLowerCase();
        String phone = Ephone.getText().toString().trim().toLowerCase();
        String bg = Eblood_group.getText().toString().trim().toLowerCase();

        register(name,username,password,email,phone,state,city,area,bg);
    }

    private void register(String name, String username, String password, String email,String phone, String state, String city, String area, String bg) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Signup.this, "we are connecting to database", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);
                data.put("state",params[4]);
                data.put("city",params[5]);
                data.put("area",params[6]);
                data.put("phone",params[7]);
                data.put("blood_group",params[8]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name, username, password, email,phone,state,city,area,bg);
    }
}
