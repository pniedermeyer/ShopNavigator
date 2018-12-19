package com.example.pascal.shopnavigator;

public class Routing {

    public static int [][] calculateDistance (int [][] gridPosition) {

        // Variables
        int [][] shortestPath = new int [gridPosition.length+2][2];
        int totalDistance = 1000;
        int temp = 10000;
        int current = 0;
        int marker = 0;
        final int entranceX = 10;
        final int entranceY = 80;
        final int cashiersX = 55;
        final int cashiersY = 80;

        for (int i = 0; i < shortestPath.length; i++) {
            shortestPath[i][0] = 1000;
            shortestPath[i][1] = 2000;
        }

        //Start and End value (Entrance and Cashiers) are static and will be added automatically;
        shortestPath[0][0] = entranceX;
        shortestPath[0][1] = entranceY;
        shortestPath[shortestPath.length-1][0] = cashiersX;
        shortestPath[shortestPath.length-1][1] = entranceY;

        for (int i = 0; i < gridPosition.length; i++) {
            for (int e = 0; e < gridPosition.length; e++) {
                if(current != i) {
                    temp = (Math.abs(shortestPath[e][0] - gridPosition[current][0]) + Math.abs(shortestPath[e][1] - gridPosition[current][1]));
                } else {

                }

                if (temp <= totalDistance) {
                    marker = e;
                    totalDistance = temp;
                }
            }
                    shortestPath[i + 1][0] = gridPosition[marker][0];
                    shortestPath[i + 1][1] = gridPosition[marker][1];

                    if(i > 0) {
                        gridPosition[current][0] = 1000;
                        gridPosition[current][1] = 1000;
                    }

                current = marker;

            }
            totalDistance = 1000;
        return shortestPath;
    }
}
