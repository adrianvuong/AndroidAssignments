package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class LoginActivity extends AppCompatActivity {
    EditText name, password;
    Button button;
    SharedPreferences sp;
    String nameStr, passStr;

    private static final String ACTIVITY_NAME = "LoginActivity";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.editText);
        password =findViewById(R.id.editText2);
        button = findViewById(R.id.button);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        button.setOnClickListener(view -> {
            nameStr = name.getText().toString();
            passStr = password.getText().toString();

            SharedPreferences.Editor editor = sp.edit();

            editor.putString("name", nameStr);
            editor.putString("password", passStr);
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
