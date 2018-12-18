package com.example.pascal.shopnavigator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class RouteActivity extends SceneParent {

    ArrayList<String> shoppingList;

    String [][] products;
    int[][] coordinatesOfProducts;

    //Test Canvas
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;




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

        //Test for Canvas
        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorAccent, null)
        );
        mPaintText.setTextSize(70);
        mImageView = (ImageView) findViewById(R.id.imageView2);


        //Convert Products from String to int and sort them.
        coordinatesOfProducts = new int[products.length][2];
        for (int i = 0; i < products.length; i++) {
            coordinatesOfProducts[i][0] = Integer.valueOf(products[i][0]);
            coordinatesOfProducts[i][1] = Integer.valueOf(products[i][1]);
        }
        coordinatesOfProducts = Routing.calculateDistance(coordinatesOfProducts);


        //Get view and draw on it
        //View shopView;

        //DrawOnScreen drawRoutre = new DrawOnScreen();
        //drawRoutre.startDrawing(coordinatesOfProducts, mImageView);
    }



    public void drawSomething(View view) {
       // int vWidth = view.getWidth();
       // int vHeight = view.getHeight();

        DrawOnScreen drawRoutre = new DrawOnScreen();
        drawRoutre.startDrawing(coordinatesOfProducts, view, mImageView);

     //   mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
     //   mImageView.setImageBitmap(mBitmap);
     //   mCanvas = new Canvas(mBitmap);
     //   mPaint.setStrokeWidth(20);
     //   mPaint.setColor(
     //           ResourcesCompat.getColor(getResources(),
     //                   R.color.red, null)
     //   );
     //   mCanvas.drawText("Text", 100, 300, mPaintText);
     //   mCanvas.drawLine(190, 1070, 190, 990, mPaint);

     //   view.invalidate();
    }

}
