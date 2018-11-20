package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends SceneParent implements View.OnClickListener {

    Spinner items;
    ArrayList<String> shops = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bar);
        setSupportActionBar(toolbar);


        //Dropdown Menu
        items = (Spinner) findViewById(R.id.items);

        shops.add("Lidl Hervanta");
        shops.add("Lidl Hervanta as well");
        shops.add("still Lidl Hervanta");
        shops.add("We don't have more");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,shops);
        items.setAdapter(adapter);


        //Button to next View
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }



    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ShoppingBag.class);
        startActivity(intent);
    }

    public void go_test_btn(View v) {
        Intent intent = new Intent(MainActivity.this, Test.class);
        startActivity(intent);
    }

}
