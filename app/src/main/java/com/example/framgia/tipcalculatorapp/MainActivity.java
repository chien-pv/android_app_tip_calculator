package com.example.framgia.tipcalculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.view.ContextMenu;
import android.content.Intent;

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
        
        final TextView tvContextMenu = (TextView) findViewById(R.id.tv_context_menu);
        tvContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(tvContextMenu);
            }
        });

        final TextView tvPopupMenu = (TextView) findViewById(R.id.tv_popup_menu);

        tvPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Khởi tạo đối tượng
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, tvPopupMenu);
                // inflate layout
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                // Đăng ký sự kiện click cho item menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.android:
                                Toast.makeText(MainActivity.this, getString(R.string.android_popup_menu), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ios:
                                Toast.makeText(MainActivity.this, getString(R.string.ios_popup_menu), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.php:
                                Toast.makeText(MainActivity.this, getString(R.string.php_popup_menu), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                // Hiển thị menu
                popupMenu.show();
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copy:
                Toast.makeText(MainActivity.this, getString(R.string.copy_context_menu), Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(MainActivity.this, getString(R.string.delete_context_menu), Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(MainActivity.this, getString(R.string.share_context_menu), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(MainActivity.this, getString(R.string.search_option_menu), Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, getString(R.string.about_option_menu), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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
