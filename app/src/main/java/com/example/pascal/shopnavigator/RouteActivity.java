package com.example.pascal.shopnavigator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RouteActivity extends SceneParent {

    ArrayList<String> shoppingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);


        shoppingList = (ArrayList<String>) getIntent().getSerializableExtra("shoppingList");

        //Adapter for List
        ListView testList = (ListView) findViewById(R.id.testList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, shoppingList);
        testList.setAdapter(adapter);


    }
}
