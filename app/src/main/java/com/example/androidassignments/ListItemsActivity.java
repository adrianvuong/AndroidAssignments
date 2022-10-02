package com.example.androidassignments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.Nullable;

public final class ListItemsActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "ListItemsActivity";
    ImageButton imgButton;
    Switch mySwitch;
    CheckBox myCheck;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_list_items);
        imgButton = findViewById(R.id.camera);

        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View imageView){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //if(cameraIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(cameraIntent, 10);
                //}
            }

        });
        mySwitch = findViewById(R.id.mySwitch);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    CharSequence text;
                    int duration;
                    if (isChecked) {
                        text = getString(R.string.toast_off);
                        duration = Toast.LENGTH_LONG;
                    } else {
                        text = getString(R.string.toast_on);
                        duration = Toast.LENGTH_SHORT;
                    }
                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
            }
        });
        myCheck = findViewById(R.id.myCheck);
        myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new AlertDialog.Builder(ListItemsActivity.this)
                            .setTitle(R.string.dialog_title)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", "My information to share");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            }
        });
    }

    private void print(String string){
        Toast toast = Toast.makeText(ListItemsActivity.this, string, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == 10 && responseCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton btnImg = findViewById(R.id.camera);
            btnImg.setImageBitmap(imageBitmap);
            try{
                //saveImage(imageBitmap);
            }catch(Exception e){
            }
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }
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
