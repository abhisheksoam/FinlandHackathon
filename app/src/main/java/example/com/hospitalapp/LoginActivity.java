package example.com.hospitalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nitin Khurana on 10-Jun-17.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;

    Button register,google,facebook,login;
    EditText email,password;

    public static final int DOC_FRAG = 1;
    public static final int PERSON_FRAG = 2;

    // doc credentials : username = doc, password = admin
    // person credentials : username = admin, password = admin

    private String USER_DOC = "doc";
    private String USER_PERSON = "admin";
    private String PASS = "admin";

    int which = DOC_FRAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        register = (Button) findViewById(R.id.register);
        google = (Button) findViewById(R.id.google);
        facebook = (Button) findViewById(R.id.facebook);
        login = (Button) findViewById(R.id.login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(this);
        google.setOnClickListener(this);
        facebook.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    void openHome(){
        Intent intent =  new Intent(this,HomeScreen.class);
        intent.putExtra("WHICH",which);
        startActivity(intent);
        finish();
    }
    void login(){

        String n = email.getText().toString();
        String pass = password.getText().toString();

        Log.e("VIAS","N " +n + " " + pass);
        if(n.length() >0 && pass.length() > 0){
            if(n.equals(USER_DOC) && pass.equals(PASS)){
                which = DOC_FRAG;
                new Loading().execute();
            }
            else if(n.equals(USER_PERSON) && pass.equals(PASS)){
                which = PERSON_FRAG;
                new Loading().execute();
            }
            else
                Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
    }
    void freeLogin(){
        new Loading().execute();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login: login(); break;
            case R.id.google : freeLogin(); break;
            case R.id.facebook : freeLogin(); break;
        }
    }

    private class Loading extends AsyncTask<Void,Void,Void>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Logging in");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
            openHome();
        }
    }
}
