package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.lab5.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> currencyList;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        InitializeCurrencyList();
        binding.btnData.setOnClickListener(view -> new DataLoader().start());
        new DataLoader().start();
    }

    private void InitializeCurrencyList() {

        currencyList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currencyList);
        binding.currencyList.setAdapter(listAdapter);
    }

    class DataLoader extends Thread {

        String data = "";

        @Override
        public void run() {

            try {
                URL url = new URL("http://www.floatrates.com/daily/usd.json");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) data = data + line;

                if (!data.isEmpty()) {

                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray currency = jsonObject.getJSONArray("currency");
                    currencyList.clear();


                    for (int i = 0; i <currency.length();i++) {
                        JSONObject names = currency.getJSONObject(i);
                        String name = names.getString("targetName");
                        currencyList.add(name);

                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}