package com.example.pascal.shopnavigator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawOnScreen extends View {
    int[][] checkPoints = new int[26][2];

    //SetupCheckpoints;
    public void setPoints() {
        // Shelf points;
        rowOfPoints(0,0,0,1);
        rowOfPoints(10,1,8,1);
        rowOfPoints(30,9,16,1);
        rowOfPoints(40,17,20,1);
        rowOfPoints(42,21,25,4);
    }

    public void rowOfPoints(int y, int start, int stop, int multiplier) {
        for (int i = start; i <= stop; i++) {
            checkPoints[i-1][0] = 8*multiplier;
            checkPoints[i-1][1] = y;
            multiplier++;
        }
    }


    Paint paint = new Paint();

    public DrawOnScreen(Context context) {
        super(context);
        paint.setColor(Color.RED);
    }

    public void PrepareRoute(int [][] shortestPath) {
        Canvas canvas = new Canvas();
        int startX = 0, startY = 0, stopX = 0, stopY = 0;

        for (int i = 0; i < shortestPath.length-1; i++) {
            startX = shortestPath[i][0];
            startY = shortestPath[i][1];

            stopX  = shortestPath[i+1][0];
            stopY  = shortestPath[i+1][0];


            DrawRoute(canvas, startX, startY, stopX, stopY);
        }
    }

    public void DrawRoute(Canvas canvas, int startX, int startY, int stopX, int stopY) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

}