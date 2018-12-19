package com.example.pascal.shopnavigator;

public class Routing {

    public static int [][] calculateDistance (int [][] gridPosition) {

        // Variables
        int [][] shortestPath = new int [gridPosition.length+2][2];
        int totalDistance = 1000;
        int temp = 0;
        int current = 0;
        int marker = 0;
        final int entranceX = 100;
        final int entranceY = 100;
        final int cashiersX = 110;
        final int cashiersY = 5;

        //Start and End value (Entrance and Cashiers) are static and will be added automatically;
        shortestPath[0][0] = entranceX;
        shortestPath[0][1] = entranceY;
        shortestPath[shortestPath.length-1][0] = cashiersX;
        shortestPath[shortestPath.length-1][1] = entranceY;


        for (int e = 1; e < gridPosition.length-1; e++) {
            for (int i = 1; i < gridPosition.length-1; i++) {

                if (i != current) {
                    temp = (Math.abs(gridPosition[i][0] - gridPosition[current][0])) + Math.abs((gridPosition[i][1] - gridPosition[current][1]));

                }

                if (temp < totalDistance) {
                    marker = i;
                    totalDistance = temp;
                }
            }
            shortestPath[e][0] = gridPosition[marker][0];
            shortestPath[e][1] = gridPosition[marker][1];

            gridPosition[current][0] = 1000;
            gridPosition[current][1] = 1000;

            current = marker;
            totalDistance = 1000;
        }

        return shortestPath;
    }
}
