package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingBag extends SceneParent implements View.OnClickListener, AdapterView.OnItemClickListener {

    // Array of strings...
    ListView simpleList;
    String shoppingArray[] = {};
    List<String> shoppingList = new ArrayList<String>();



        //_________________________________________________________

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shopping_bag);

            //Toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bar);
            setSupportActionBar(toolbar);

            this.simpleList = (ListView) findViewById(R.id.simpleListView);

            //Database connection
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            List<String> products = databaseAccess.getProducts();
            databaseAccess.close();

            //Adapter for List
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, products);
            this.simpleList.setAdapter(adapter);

            //AutoCompleteItems
            ArrayAdapter<String> AutoOmpleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, shoppingArray);
            AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
            textView.setAdapter(AutoOmpleteAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp=(TextView) view;
        String Value = simpleList.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), Value, Toast.LENGTH_SHORT).show();
    }

    public void goMap(View v) {
        Intent intent = new Intent(ShoppingBag.this, RouteActivity.class);
        startActivity(intent);


    }

    public void addItem(View v){
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        shoppingList.add((String) source.getText().toString());
        simpleList.invalidateViews();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Yasd", Toast.LENGTH_SHORT).show();
    }
}
