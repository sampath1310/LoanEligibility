package com.example.root.Loaneligibility;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.root.loaneligible.R;

import java.io.InputStream;

import evaluation.ModelEvaluator;


public class Final extends AppCompatActivity {
    Intent i;
    String t;
    String processedResult;
    TextView tv;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        tv=(TextView)findViewById(R.id.textview);
        i = getIntent();
        new MultiTask().execute(i);
        Log.d("Processed","yes");
        tv.setText("Under Process Please Wait");
    }

    private class MultiTask extends AsyncTask<Intent, String, String> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(Intent... params) {
            AssetManager assetManager = getApplicationContext().getAssets();
            t = i.getStringExtra("Aplic");
            try {
                InputStream inputStream=assetManager.open("rf.pmml.ser");
                ModelEvaluator modelEvaluator = new ModelEvaluator();
               processedResult= modelEvaluator.modelEvaluationProcess(inputStream,t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return processedResult;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String sss=new String();
            for (int i=69;i<74;i++){
            sss=sss+result.charAt(i);
            }
            if(Double.valueOf(sss)>0.500) {

                tv.setText("Your Are Eligible For Loan with probiblity of "+sss);
            }else {
                tv.setText("You are not eligible for loan");
            }
        }


    }
}

