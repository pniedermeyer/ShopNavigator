package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ShoppingBag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bar);
        setSupportActionBar(toolbar);
    }

    //Menu uppper left corner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Items of Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                //settings
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.contact:
                //contact
                Intent contact = new Intent(this, ContactActivity.class);
                startActivity(contact);
                break;
            default:
                //unknown error
        }
        return super.onOptionsItemSelected(item);
    }
}
