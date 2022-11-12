package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {

    ProgressBar progressBar;
    TextView minimum, maximum, current;
    ImageView image;
    Spinner spinner;

    String [] cities = {"toronto, ottawa, vancouver"};
    String selectedCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        minimum = findViewById(R.id.textView2);
        maximum = findViewById(R.id.textView3);
        current = findViewById(R.id.textView4);

        image = findViewById(R.id.imageView3);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    public void onItemSelected(AdapterView <?> parent, View view, int pos, long id){
        selectedCity = cities[pos];
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String>{
        String min, max, curr;
        Bitmap weather;


        @Override
        protected String doInBackground(String... args)  {
            try {
                URL url = new URL("api.openweathermap.org/data/2.5/weather?q="+selectedCity+",ca&APPID=3d4c7e0c8ce52793d093c2369cfc062d&mode=xml&units=metric");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000); //in ms
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(conn.getInputStream(), null);

                while(parser.next() != XmlPullParser.END_TAG){
                    if(parser.getEventType() != XmlPullParser.START_TAG){
                        continue;
                    }

                    String name = parser.getName();

                    if(name.equals("temperature")){
                        curr = parser.getAttributeValue(null, "value");
                        publishProgress(25);
                        min = parser.getAttributeValue(null, "min");
                        publishProgress(50);
                        max = parser.getAttributeValue(null, "max");
                        publishProgress(75);
                    }
                    else if(name.equals("weather")){
                        String iconName = parser.getAttributeValue(null, "icon");
                        String ACTIVITY_NAME = "WeatherForecast";
                        if(!fileExistence(iconName)) {
                            Log.i(ACTIVITY_NAME, "Downloading file "+iconName+ "from url");
                            url = new URL("http://openweathermap.org/img/w/" + iconName + ".png");
                            conn = (HttpURLConnection) url.openConnection();
                            conn.connect();
                            int responseCode = conn.getResponseCode();
                            if (responseCode == 200) {
                                weather = BitmapFactory.decodeStream(conn.getInputStream());
                            }
                            FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                            weather.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                            outputStream.flush();
                            outputStream.close();
                        }
                        else{
                            FileInputStream fis = null;
                            try{
                                Log.i(ACTIVITY_NAME, "Looking for file "+iconName+ "locally");
                                fis = openFileInput(iconName);
                            } catch(FileNotFoundException e){
                                e.printStackTrace();
                            }
                            weather = BitmapFactory.decodeStream(fis);
                        }
                        publishProgress(100);
                    }
                }
            } catch (IOException | XmlPullParserException e){
                e.printStackTrace();
            }

            return null;
        }
        public boolean fileExistence(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            minimum.setText(R.string.minTemp+" "+min);

            maximum.setText(R.string.maxTemp+" "+max);
            current.setText(R.string.currTemp+" "+curr);

            image.setImageBitmap(weather);

            progressBar.setVisibility(View.INVISIBLE);

        }
    }
}