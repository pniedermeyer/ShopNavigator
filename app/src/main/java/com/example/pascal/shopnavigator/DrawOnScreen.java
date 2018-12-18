package com.example.pascal.shopnavigator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawOnScreen extends View {
    Paint paint = new Paint();
    int[][] checkPoints = new int[26][2];
    Canvas canvas = new Canvas();


    public DrawOnScreen(Context context) {
        super(context);
        paint.setColor(Color.RED);
    }


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
            checkPoints[i][0] = 8*multiplier;
            checkPoints[i][1] = y;
            multiplier++;
        }
    }


    public void PrepareRoute(int [][] shortestPath) {
        setPoints();
        int [][] destinationCheckpoint = new int [1][2];
        int nextX = 0, nextY = 0, stopX = 0, stopY = 0, startX = 0, startY = 0;

        DrawLine(checkPoints[0][0], checkPoints[0][1], checkPoints[1][0], checkPoints[1][1]);

        startX = checkPoints[1][0];
        startY = checkPoints[1][1];

        for (int i = 1; i < shortestPath.length-1; i++) {
            nextX = shortestPath[i][0];
            nextY = shortestPath[i][1];
            destinationCheckpoint = findClosestCheckpoint(nextX, nextY);
            DrawLine(startX, startY, destinationCheckpoint[0][0], startY);
            DrawLine(destinationCheckpoint[0][0], startY, destinationCheckpoint[0][0], destinationCheckpoint[0][1]);
            startX = destinationCheckpoint[0][0];
            startY = destinationCheckpoint[0][1];
        }
    }

    public void DrawLine(int startX, int startY, int stopX, int stopY) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    public int [][] findClosestCheckpoint(int startX, int startY){
        int [][] destinationPoint = new int [1][2];
        int distanceX = 100;
        int distanceY = 100;
        int holdX = 0;
        int holdY = 0;

        for (int i = 1; i < 26; i++) {
            if (Math.abs(startX - checkPoints[i][0]) < distanceX) {
                distanceX = Math.abs(startX - checkPoints[i][0]);
                holdX = checkPoints[i][0];
            }

            if (Math.abs(startY - checkPoints[i][1]) < distanceY) {
                distanceY = Math.abs(startX - checkPoints[i][1]);
                holdY = checkPoints[i][1];
            }
        }
        destinationPoint[0][0] = holdX;
        destinationPoint[0][1] = holdY;
        //destinationPoint = holdX;

        return destinationPoint;
    }

}