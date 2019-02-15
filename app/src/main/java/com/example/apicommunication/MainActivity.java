package com.example.apicommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvResults;
    EditText etSymbol;
    Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResults= findViewById(R.id.tvResults);
        etSymbol = findViewById(R.id.etSymbol);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSearch:
                handleSearchClick();
                break;
        }
    }

    private void handleSearchClick(){
        String userSymbol = etSymbol.getText().toString();
        String url = "https://api.iextrading.com/1.0/stock/"+userSymbol+"/quote";
        try {
            new APIWorker().execute(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class APIWorker extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder result = new StringBuilder();
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urls[0].openConnection();
                InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = bufferedReader.readLine())!= null){
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Gson gson = new Gson();
            SymbolProfile symbolProfile = gson.fromJson(results, SymbolProfile.class);

           // List<SymbolProfile> apiResults = Collections.singletonList(symbolProfile);
            String name = symbolProfile.getCompanyName();
            String primEx = symbolProfile.getPrimaryExchange();
            String open = Double.toString(symbolProfile.getOpen());
            String close = Double.toString(symbolProfile.getClose());
            String combo = "Company Name: " + name + "\n" +
                            "Primary Echange: " + primEx + "\n" +
                            "Open: " + open + "\n" +
                            "Close: " + close;
            tvResults.setText(combo);


        }


    }
}


