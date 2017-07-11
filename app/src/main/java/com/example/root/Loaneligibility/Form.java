package com.example.root.Loaneligibility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.root.loaneligible.R;

public class Form extends AppCompatActivity {
    Button ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        FirstFragment firstFragment=new FirstFragment();
        if (firstFragment!=null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.framecontainer, firstFragment)
                    .commit();
        }
    }
}
