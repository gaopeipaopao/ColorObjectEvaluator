package com.example.gaope.colorobjectevaluator;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String color = "#0000FF";
    private ColorView colorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorView = (ColorView)findViewById(R.id.color_view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(colorView,"color",
                new ColorEvaluator(),"#0000FF","#FF0000");
        objectAnimator.setDuration(8000);
        objectAnimator.start();

    }
}
