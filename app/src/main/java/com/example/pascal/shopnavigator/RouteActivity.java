package com.example.pascal.shopnavigator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class RouteActivity extends SceneParent {

    ArrayList<String> shoppingList;

    String [][] products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);


        shoppingList = (ArrayList<String>) getIntent().getSerializableExtra("shoppingList");

        //Database connection
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        products = databaseAccess.getCoordinates(shoppingList);
        databaseAccess.close();


        //Text View
        TextView helloTextView = (TextView) findViewById(R.id.textView4);
        helloTextView.setText(Arrays.deepToString(products));


        //Convert Products from String to and sort them.
        int [][] coordinatesOfProducts = new int [products.length][2];
        for (int i = 0; i < products.length; i++) {
            coordinatesOfProducts[i][0] = Integer.valueOf(products[i][0]);
            coordinatesOfProducts[i][1] = Integer.valueOf(products[i][1]);
        }
        coordinatesOfProducts = Routing.calculateDistance(coordinatesOfProducts);

        MyCanvas myCanvas;
        myCanvas = new MyCanvas(this);
        myCanvas.setBackgroundColor(Color.TRANSPARENT);
        setContentView(myCanvas);

        //DrawOnScreen drawRoute;
        //drawRoute = new DrawOnScreen(this, coordinatesOfProducts);
        //drawRoute.setBackgroundColor(Color.TRANSPARENT);
        //setContentView(drawRoute);


    }
}
