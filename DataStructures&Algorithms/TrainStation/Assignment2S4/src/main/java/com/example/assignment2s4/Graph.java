package com.example.assignment2s4;

import java.util.ArrayList;

public class Graph {
    int[][] matrix;
    ArrayList<Station> stations;

    Graph(int size){
        stations = new ArrayList<>();
        matrix = new int[size][size];
    }

    public void addStation(Station station){
    stations.add(station);
    }

    public void addConnection(int row, int column, int line){
        matrix[row][column] = line;
        matrix[column][row] = line;
    }

    public void printConnection(int row, int column){
        System.out.println(matrix[row][column]);
    }

    public void printStations(){
        for(int i = 0; i < stations.size(); i++){
        System.out.println(stations.get(i).toString());
        }
    }
}
