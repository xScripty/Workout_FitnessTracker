package com.example.workoutfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button LoginButton,RegisterButton;
    EditText nameEditText,passEditText;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences=getSharedPreferences("Userinfo",0);

        nameEditText=(EditText)findViewById(R.id.NameEditText);
        passEditText=(EditText)findViewById(R.id.PasswordEditText);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton=(Button)findViewById(R.id.RegisterButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registeredName = preferences.getString("userName","");
                String registeredPassword= preferences.getString("password","");

                String name=nameEditText.getText().toString();
                String pass=passEditText.getText().toString();

                if(registeredName.equals((name))&&registeredPassword.equals(pass)) {

                    Intent i_main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i_main);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong Data!",Toast.LENGTH_SHORT).show();
                }
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


}