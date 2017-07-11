package com.example.root.Loaneligibility;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.loaneligible.R;


public class FirstFragment extends Fragment {
    private Button btn;
    private TextView tv;
    String t = new String();

    public FirstFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        btn = (Button) view.findViewById(R.id.button);
        tv = (TextView) view.findViewById(R.id.input_name);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Final.class);
                intent.putExtra("Aplic",tv.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }



}

