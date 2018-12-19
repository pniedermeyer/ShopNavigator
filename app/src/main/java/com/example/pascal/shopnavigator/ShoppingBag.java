package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingBag extends SceneParent implements View.OnClickListener, AdapterView.OnItemClickListener {

    // Array of strings...
    ListView simpleList;

    //List of the Items in the shopping Cart
    List<String> shoppingList = new ArrayList<String>();

    //Items out of the database
    List<String> products;

    private int position;



        //_________________________________________________________

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shopping_bag);

            //Toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bar);
            setSupportActionBar(toolbar);



            //Database connection
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            products = databaseAccess.getProducts();
            databaseAccess.close();

            //Adapter for List
            this.simpleList = (ListView) findViewById(R.id.simpleListView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, shoppingList);
            this.simpleList.setAdapter(adapter);
            simpleList.setOnItemClickListener(this);


            //AutoCompleteItems
            ArrayAdapter<String> AutoOmpleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, products);
            AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
            textView.setAdapter(AutoOmpleteAdapter);

    }


    public void goMap(View v) {
            if(!shoppingList.isEmpty()){
                Intent intent = new Intent(ShoppingBag.this, RouteActivity.class);
                intent.putExtra("shoppingList", (Serializable) shoppingList);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),R.string.no_item_selected , Toast.LENGTH_SHORT).show();
            }


    }

    public void addItem(View v){
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        if (products.contains(source.getText().toString())){
            if(shoppingList.contains(source.getText().toString())){
                Toast.makeText(getApplicationContext(),R.string.item_not_available, Toast.LENGTH_SHORT).show();
            }else {
                shoppingList.add((String) source.getText().toString());
                simpleList.invalidateViews();
            }
        }else{
            Toast.makeText(getApplicationContext(),R.string.item_not_available, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position=position;

        TextView temp=(TextView) view;
        String Value = shoppingList.get(position);

        Toast.makeText(getApplicationContext(), Value, Toast.LENGTH_SHORT).show();
        showPopup();
    }

    private PopupWindow pw;
    public void showPopup() {
        try {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.popup,(ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, (ViewGroup.LayoutParams.WRAP_CONTENT), (ViewGroup.LayoutParams.WRAP_CONTENT), true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete_button (View view){
        shoppingList.remove(position);
        simpleList.invalidateViews();
        cancel_button(view);
    }

    public void cancel_button (View view){
        pw.dismiss();
    };





    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Yasd", Toast.LENGTH_SHORT).show();
    }
}
