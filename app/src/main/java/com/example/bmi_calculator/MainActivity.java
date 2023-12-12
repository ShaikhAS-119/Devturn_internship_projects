package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    float bmi;
    String BMIResult;
    TextView textView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextInputEditText weight=findViewById(R.id.weight);
        TextInputEditText height=findViewById(R.id.height);
        textView=findViewById(R.id.result);

        Button button=findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=weight.getText().toString();
                String value2=height.getEditableText().toString();

                calculateBMI(value,value2);
                float weightValue = Float.parseFloat(value);

               float heightValue = Float.parseFloat(value2) / 100;

               float bmi = weightValue / (heightValue * heightValue);

                textView.setText(new DecimalFormat("##.##").format(bmi)+" "+"kg/"+"m\u00b2"+" "+BMIResult);
            }
        });


    }
    public void  calculateBMI(String heightvalue,String weightvalue){

        float weightValue = Float.parseFloat(weightvalue);
        float heightValue = Float.parseFloat(heightvalue)/100;
        Log.i("check", "calculateBMIs: "+heightvalue);

        bmi = weightValue / (heightValue * heightValue);

        if (bmi < 18.5){
            BMIResult = "Underweight";
            //white
            textView.setTextColor(Color.parseColor("#A3A3A3"));
        }
        else if (bmi >= 18.5 && bmi < 24.9) {
            BMIResult = "Healthy Weight";
            //green
            textView.setTextColor(Color.parseColor("#00FF00"));
        }
        else if (bmi >= 25 && bmi >= 29.9){
            BMIResult = "Overweight";
            //orange
            textView.setTextColor(Color.parseColor("#FFA500"));
        }
        else if (bmi > 30){
            BMIResult = "Obese";
            //red
            textView.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}