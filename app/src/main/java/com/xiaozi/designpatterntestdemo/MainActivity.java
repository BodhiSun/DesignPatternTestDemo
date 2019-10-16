package com.xiaozi.designpatterntestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DesignPatternProfile designPatternProfile = new DesignPatternProfile();
//        designPatternProfile.principleTest();
        designPatternProfile.patternTest();

    }
}
