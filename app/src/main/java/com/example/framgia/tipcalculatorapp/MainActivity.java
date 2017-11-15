package com.example.framgia.tipcalculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;



public class MainActivity extends AppCompatActivity {
    private EditText billAmountEditText;
    private EditText percentEditText;
    private EditText tipEditText;
    private EditText totalEditText;
    public float tip;
    public float total;
    public float a;
    public float b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentEditText = (EditText) findViewById(R.id.percentEditText);
        tipEditText = (EditText) findViewById(R.id.tipEditText);
        totalEditText = (EditText) findViewById(R.id.totalEditText);

        billAmountEditText.addTextChangedListener(textWatcher);


        a = Float.valueOf(billAmountEditText.getText().toString());
        b = Float.valueOf(percentEditText.getText().toString());

        tip = percent_tip(a,b);
        total = tip*2;
        tipEditText.setText(""+tip);
        totalEditText.setText(""+total);


    }

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            a = Float.valueOf(billAmountEditText.getText().toString());
            b = Float.valueOf(percentEditText.getText().toString());

            tip = percent_tip(a,b);
            total = tip*2;
            tipEditText.setText(""+tip);
            totalEditText.setText(""+total);

        }
    };

    public float percent_tip(float a, float b){
        return a*(b/100);
    }

}
