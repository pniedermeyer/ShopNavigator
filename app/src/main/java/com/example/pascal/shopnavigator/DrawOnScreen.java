package com.example.pascal.shopnavigator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.support.v4.content.res.ResourcesCompat;

public class DrawOnScreen extends SceneParent{
    //Variables
    private Paint paint = new Paint();
    private int[][] checkPoints = new int[26][2];
    private Canvas canvas = new Canvas();
    private Bitmap mBitmap;
    private ImageView mImageView;
    int width = 0, height = 0;
    private int[][] shortestPath;


    //SetupCheckpoints;
    public void setPoints() {
        int temp = 0;
        // Shelf points;
        rowOfPoints(84,0,0,1);
        rowOfPoints(78,1,8,1);
        rowOfPoints(25,9,16,1);
        rowOfPoints(20,17,20,1);
        rowOfPoints(15,21,25,4);

        for (int i = 0; i < checkPoints.length; i++) {
            // System.out.println(shortestPath[i][0] + " Befire "+ shortestPath[i][1]);
            temp = ((width / 100) * checkPoints[i][0]);
            checkPoints[i][0] = (int) temp;

            temp = ((height / 100) * checkPoints[i][1]);
            checkPoints[i][1] = (int) temp;
        }
    }

    //Symatically build checkpoints
    public void rowOfPoints(int y, int start, int stop, int multiplier) {
        for (int i = start; i <= stop; i++) {
            checkPoints[i][0] = 11*multiplier;
            checkPoints[i][1] = y;
            multiplier++;
        }
    }


    //Initalizing the Drawing
    public void startDrawing(int [][] shortestPath, View shopView, ImageView mImageView, int width, int height) {
        this.width = width;
        this.height = height;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mImageView.setImageBitmap(mBitmap);
        canvas = new Canvas(mBitmap);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
       // DrawLine(0,0,2,2);
        this.shortestPath = shortestPath;
        setPoints();
        PrepareRoute();

        shopView.invalidate();
    }

    //Calculating and drawing the route
    public void PrepareRoute() {
        double temp = 0;
        int [][] destinationCheckpoint = new int [1][2];
        int nextX = 0, nextY = 0, stopX = 0, stopY = 0, startX = 0, startY = 0;

        //Draw start-line
        DrawLine(checkPoints[0][0], checkPoints[0][1], checkPoints[1][0], checkPoints[1][1]);

        //Set startposition to first checkpoint
        startX = checkPoints[1][0];
        startY = checkPoints[1][1];
        //System.out.println(width +" scheipsj "+ height);


        //Convert percent values to screenvalue
        for (int i = 0; i < shortestPath.length; i++){
           // System.out.println(shortestPath[i][0] + " Befire "+ shortestPath[i][1]);
            temp = ((width / 100) * shortestPath[i][0]);
            shortestPath[i][0] = (int)temp;

            temp = ((height / 100) * shortestPath[i][1]);
            shortestPath[i][1] = (int)temp;

          //  System.out.println(shortestPath[i][0] + " "+ shortestPath[i][1]);
        }

        //Drawing the lines between the checkpoints
        for (int i = 1; i < shortestPath.length-1; i++) {
            nextX = shortestPath[i][0];
            nextY = shortestPath[i][1];
         //   System.out.println(nextX + "next" + nextY);
            destinationCheckpoint = findClosestCheckpoint(nextX, nextY);
            DrawLine(startX, startY, destinationCheckpoint[0][0], startY);
            DrawLine(destinationCheckpoint[0][0], startY, destinationCheckpoint[0][0], destinationCheckpoint[0][1]);
            startX = destinationCheckpoint[0][0];
            startY = destinationCheckpoint[0][1];
        }
    }

    //Draws line
    public void DrawLine(int startX, int startY, int stopX, int stopY) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    //Find the checkpoint closest to the given points
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