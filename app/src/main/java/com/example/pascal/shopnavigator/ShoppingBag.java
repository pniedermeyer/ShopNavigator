package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ShoppingBag extends SceneParent implements View.OnClickListener{

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

    }

    public void onClick(View v) {
        Intent intent = new Intent(ShoppingBag.this, RouteActivity.class);
        startActivity(intent);
    }

}
