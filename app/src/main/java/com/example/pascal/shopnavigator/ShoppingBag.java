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
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShoppingBag extends SceneParent implements View.OnClickListener{

    // Array of strings...
    ListView simpleList;
    String countryList[] = {"Salt", "Apples", "Sugar", "Bread", "Milk"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bar);
        setSupportActionBar(toolbar);


        //Button to next View
        Button button1 = (Button) findViewById(R.id.go_shop_btn);
        button1.setOnClickListener((View.OnClickListener) this);

        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);

    }

    public void onClick(View v) {
        Intent intent = new Intent(ShoppingBag.this, RouteActivity.class);
        startActivity(intent);
    }

}
