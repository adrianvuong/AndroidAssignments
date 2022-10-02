package com.example.androidassignments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;


public final class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "MainActivity";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_main);

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
