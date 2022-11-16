package com.example.workoutfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText name,password;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton=(Button) findViewById(R.id.RegisterUserButton);
        name=(EditText) findViewById(R.id.RegisterNameEditText);
        password=(EditText) findViewById(R.id.RegisterPasswordEditText);

        preferences=getSharedPreferences("Userinfo",0);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s=name.getText().toString();
                String pass_s=password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userName", name_s);
                editor.putString("password", pass_s);

                editor.apply();

                Intent intent_login= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent_login);
            }
        });
    }
}