package com.example.pascal.shopnavigator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class Test extends Activity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(adapter);

        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView, groceries);
        simpleList.setAdapter(arrayAdapter);

        groceries.add("Salt");
        groceries.add("Apples");
        groceries.add("Sugar");
        groceries.add("Bread");
        groceries.add("Milk");
        Toast.makeText(getApplicationContext(), "Yasd", Toast.LENGTH_SHORT).show();

        //ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,groceries);
        //list.setAdapter(adapter3);
        simpleList.setOnItemClickListener(this);
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    // Array of strings...
    ListView simpleList;
    String countryList[] = {"Salt", "Apples", "Sugar", "Bread", "Milk"};

    ArrayList<String> groceries = new ArrayList<String>();



    public void setInput(View v){
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        TextView asdf= (TextView) findViewById(R.id.textView3);
        asdf.setText(source.getText());

        TextView andere = findViewById(R.id.textView);
        asdf.setText(andere.getText());

        groceries.add((String) source.getText().toString());
        simpleList.invalidateViews();

        //System.out.println("aspidjapd");
        //System.out.println(source);
        //System.out.println(source.getText());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp=(TextView) view;
        String Value = simpleList.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), Value, Toast.LENGTH_SHORT).show();
    }

    public void click (){
        TextView asdf= (TextView) findViewById(R.id.textView3);
        asdf.setText("asfas");
    }
}
