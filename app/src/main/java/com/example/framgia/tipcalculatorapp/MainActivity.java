package com.example.framgia.tipcalculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText billAmountEditText;
    private EditText percentEditText;
    private EditText tipEditText;
    private EditText totalEditText;
    private Button addButton;
    private Button subButton;
    public float tip;
    public float total;
    public float a = 0, b = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentEditText = (EditText) findViewById(R.id.percentEditText);
        tipEditText = (EditText) findViewById(R.id.tipEditText);
        totalEditText = (EditText) findViewById(R.id.totalEditText);

        billAmountEditText.addTextChangedListener(textWatcher);
        percentEditText.addTextChangedListener(textWatcher);

        addButton = (Button) findViewById(R.id.addButton);
        subButton = (Button) findViewById(R.id.subButton);

        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        load_value_edit_text();
        percent_tip();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
                b = b+1;
                percent_tip();
                break;
            case R.id.subButton:
                b = b-1;
                percent_tip();
                break;
        }
        percentEditText.setText(""+b);
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
            load_value_edit_text();
            percent_tip();
        }
    };

    public void percent_tip(){
        tip = a*(b/100);
        total = tip*2;
        tipEditText.setText(""+tip);
        totalEditText.setText(""+total);
    }

    public void load_value_edit_text(){
        try {
            a = Float.parseFloat(billAmountEditText.getText().toString());
        }
        catch(NumberFormatException ex) {
            a = 0;
        }
        try {
            b = Float.parseFloat(percentEditText.getText().toString());
        }
        catch(NumberFormatException ex) {
            b = 0;
        }
    }

}
