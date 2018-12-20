package com.example.pascal.shopnavigator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import android.view.*;

public class RouteActivity extends SceneParent {
    DrawOnScreen drawRoute = new DrawOnScreen();
    ArrayList<String> shoppingList;

    String [][] products;
    int[][] coordinatesOfProducts;

    private float[] lastTouchDownXY = new float[2];

    //Test Canvas
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    int width = 0, height = 0;

    private int once = 0;


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

        View img = (View) findViewById( R.id.imageView);
        ImageView img2 = (ImageView) findViewById( R.id.imageView2);



        //Test for Canvas
        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorAccent, null)
        );
        mPaintText.setTextSize(70);
        mImageView = (ImageView) findViewById( R.id.imageView2);


        //Convert Products from String to int and sort them.
        coordinatesOfProducts = new int[products.length][2];
        for (int i = 0; i < products.length; i++) {
            coordinatesOfProducts[i][0] = Integer.valueOf(products[i][0]);
            coordinatesOfProducts[i][1] = Integer.valueOf(products[i][1]);
        }
        coordinatesOfProducts = Routing.calculateDistance(coordinatesOfProducts);

        mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalListenerClass());


        mImageView.setOnTouchListener(touchListener);
        mImageView.setOnClickListener(clickListener);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            // save the X,Y coordinates
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                lastTouchDownXY[0] = event.getX();
                lastTouchDownXY[1] = event.getY();

            }

            // let the touch event pass on to whoever needs it
            return false;
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // retrieve the stored coordinates
            float x = lastTouchDownXY[0];
            float y = lastTouchDownXY[1];
            drawRoute.touchEvent(x, y, coordinatesOfProducts);
        }
    };



    //Declare the layout listener
    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            if(once == 0) {
                once = 1;
                View view = (View) findViewById(R.id.imageView2);
                width = view.getWidth();
                height = view.getHeight();


                drawRoute.startDrawing(coordinatesOfProducts, view, mImageView, width, height);
            }
        }
    }

    public void goHome(View v) {
            Intent intent = new Intent(RouteActivity.this, MainActivity.class);
            startActivity(intent);
    }
}
