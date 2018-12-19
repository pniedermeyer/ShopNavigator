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
    private int width = 0, height = 0;


    //SetupCheckpoints;
    public void setPoints() {
        int [] xCoordinates = new int [8];
        int [] yCoordinates = new int [6];

        //Initialize arrays
        xCoordinates [0] = 11;

        for (int i = 1; i < 8; i++) {
            xCoordinates [i] = 24 + 12*(i-1);
        }

        yCoordinates [0] = 84;
        yCoordinates [1] = 78;
        yCoordinates [2] = 42;
        yCoordinates [3] = 12;
        yCoordinates [4] = 7;

        //Write checkpoints
        int temp = 0;
        rowOfPoints(yCoordinates[0], temp, xCoordinates[0]);
        temp++;
        for (int i = 0; i < 8; i++) {
            rowOfPoints(yCoordinates[1], temp, xCoordinates[i]);
            temp++;
            rowOfPoints(yCoordinates[2], temp, xCoordinates[i]);
            temp++;
        }
        for (int i = 0; i < 4; i++) {
            rowOfPoints(yCoordinates[3], temp, xCoordinates[i]);
            temp++;
        }
        for (int i = 3; i < 8; i++) {
            rowOfPoints(yCoordinates[4], temp, xCoordinates[i]);
            temp++;
        }

        //Convert to screen Value
        checkPoints = percentValueToScreenValue(checkPoints);

        //Draw Test line
        //for (int i = 0; i < checkPoints.length-1; i++) {
        //    DrawLine(checkPoints[i][0], checkPoints[i][1], checkPoints[i + 1][0], checkPoints[i + 1][1]);
        //}
    }


    //Symatically build checkpoints
    public void rowOfPoints(int y, int i, int x) {
            checkPoints[i][0] = x;
            checkPoints[i][1] = y;
    }


    public int [][] percentValueToScreenValue(int points [][]) {
        int temp = 0;

        for (int i = 0; i < points.length; i++) {
            temp = ((width / 100) * points[i][0]);
            points[i][0] = (int) temp;

            temp = ((height / 100) * points[i][1]);
            points[i][1] = (int) temp;
        }
        return points;
    }


    //Initalizing the Drawing
    public void startDrawing(int [][] shortestPath, View shopView, ImageView mImageView, int width, int height) {
        this.width = width;
        this.height = height;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mImageView.setImageBitmap(mBitmap);
        canvas = new Canvas(mBitmap);
        paint.setStrokeWidth(17);
        paint.setColor(Color.RED);

        setPoints();
        PrepareRoute(shortestPath);

        shopView.invalidate();
    }

    //Calculating and drawing the route
    public void PrepareRoute (int [][] shortestPath) {
        double temp = 0;
        int [][] destinationCheckpoint = new int [1][2];
        int nextX = 0, nextY = 0, stopX = 0, stopY = 0, startX = 0, startY = 0;

        //Draw start-line
        DrawLine(checkPoints[0][0], checkPoints[0][1], checkPoints[1][0], checkPoints[1][1]);

        //Set startposition to first checkpoint
        startX = checkPoints[1][0];
        startY = checkPoints[1][1];;


        //Convert percent values to screenvalue
        shortestPath = percentValueToScreenValue(shortestPath);

        //Drawing the lines between the checkpoints closest to the next product
        for (int i = 1; i < shortestPath.length-1; i++) {
            //draw points of produkts on map
            canvas.drawCircle(shortestPath[i][0], shortestPath[i][1], 18, paint);

            //select next product x, y
            nextX = shortestPath[i][0];
            nextY = shortestPath[i][1];

         //   System.out.println(nextX + "next" + nextY);
            //Find closest checkpoint to next product
            destinationCheckpoint = findClosestCheckpoint(nextX, nextY);

            //Draw line from current checkpoint to closest checkpoint of new product.
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