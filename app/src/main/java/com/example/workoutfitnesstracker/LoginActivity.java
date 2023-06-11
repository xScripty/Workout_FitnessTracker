package com.example.workoutfitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button LoginButton,RegisterButton;
    EditText nameEditText,passEditText;
    FirebaseAuth mAuth;
    //SharedPreferences preferences;
    private static final String TAG = "NAMIR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //references=getSharedPreferences("Userinfo",0);

        nameEditText=(EditText)findViewById(R.id.NameEditText);
        passEditText=(EditText)findViewById(R.id.PasswordEditText);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton=(Button)findViewById(R.id.RegisterButton);
        mAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=nameEditText.getText().toString();
                String pass=passEditText.getText().toString();

                signIn(name,pass);
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_register= new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent_register);
            }
        });
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i_main = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i_main);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"Wrong Data!",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


}


/*package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "firebasetest";
    private EditText etMail, etPassword;
    private Button btnLogin, btnSignup;
    private TextView tvSignup,tvWelcome;
    private FirebaseAuth mAuth;
    private final String valid_mail="admin";
    private final String valid_password="1";
    SharedPreferences preferences;





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
        mAuth = FirebaseAuth.getInstance();
        etMail=findViewById(R.id.editTextTextEmailAddress);
        etPassword=findViewById(R.id.editTextTextPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnRegister);
        preferences=getSharedPreferences("Userinfo",0);


        Intent notification = new Intent(this,Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast (this, 1, notification, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 3000, pendingIntent);



    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Back Button was pressed!");
        dialog.setMessage("Are you sure you want ");
        dialog.setNegativeButton("No",null);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }
    public void register(View view){
        Intent i_register=new Intent(this, RegisterActivity.class);
        startActivity(i_register);
    }
    public void camera(View view){
        Intent i_camera=new Intent(this, Camera.class);
        startActivity(i_camera);
    }

    public void login(View view) {
        String input_mail = etMail.getText().toString();
        String input_password = etPassword.getText().toString();
        String registeredMail = preferences.getString("username", "");
        String registeredPassword = preferences.getString("password", "");
        if(!input_mail.equals("") && ! input_password.equals(""))
        {
            signIn(input_mail,input_password);
        } else {
            Toast.makeText(this, "Empty Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(MainActivity.this, MainmainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }




    }
    */