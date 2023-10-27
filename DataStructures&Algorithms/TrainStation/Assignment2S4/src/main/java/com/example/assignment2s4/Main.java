package com.example.assignment2s4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Main implements Initializable {
    Graph graph;
    String Lines = "Lines.csv";
    String Routes = "Routes.csv";
    String Stations = "Stations.csv";
    Scanner scanner;
    public TextField start;
    public ListView<String> stationsList;
    public ChoiceBox<String> startPoint;
    public ChoiceBox<String> destination;
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int size = 0;
        int rowLength = 0;
        String line = null;
        try {
            scanner = new Scanner(new FileReader(Stations));
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                size = size + row.length;
                rowLength = row.length;
            }
            graph = new Graph((size / rowLength) + 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            addStations();
            connectStations();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < graph.stations.size();i++){
            if(graph.stations != null) {
                startPoint.getItems().add("" + graph.stations.get(i).getId() + ": " + graph.stations.get(i).getName());
                destination.getItems().add("" + graph.stations.get(i).getId() + ": " + graph.stations.get(i).getName());
            }
        }
    }

    public void routes(){
    imageView.setBlendMode(BlendMode.SCREEN);
    }
    public void picture(){
        imageView.setBlendMode(BlendMode.SRC_OVER);
    }

    public void addStations() throws IOException {
        int index = 0;
        scanner = new Scanner(new FileReader(Stations));
        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split(",");
            if (index != 0) {
                Station station = new Station(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], Double.parseDouble(row[5]), Double.parseDouble(row[6]), Double.parseDouble(row[7]));
                graph.addStation(station);
                stationsList.getItems().add(station.toString());
            }
            index = index + 1;
        }
        for (int i = 0; i < graph.stations.size(); i++) {
            System.out.println(graph.stations.get(i).toString());
        }
    }

    public void allStations(){
        stationsList.getItems().clear();
        for (int i = 0; i < graph.stations.size() - 1; i++) {
            stationsList.getItems().add(graph.stations.get(i).toString());
        }
    }

    public void PrintRight() throws FileNotFoundException {
        for (int i = 0; i < 309; i++) {
            int index = 0;
            Station station = null;
            scanner = new Scanner(new FileReader(Stations));
            while (scanner.hasNextLine()) {
                System.out.println(i);
                String[] row = scanner.nextLine().split(",");
                if (index != 0) {
                    if (Integer.parseInt(row[0]) == i) {
                        station = new Station(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], Double.parseDouble(row[5]), Double.parseDouble(row[6]), Double.parseDouble(row[7]));
                        System.out.println(station.getId());
                        graph.addStation(station);
                    }
                }
                index = index + 1;
            }
        }
        for (int i = 0; i < graph.stations.size(); i++) {
            System.out.println(graph.stations.get(i).getId() + "," + graph.stations.get(i).getLatitude() + "," + graph.stations.get(i).getLongitude() + "," + graph.stations.get(i).getName() + "," +
                    graph.stations.get(i).getDisplay_name() + "," + graph.stations.get(i).getZone() + "," + graph.stations.get(i).getTotal_lines() + "," + graph.stations.get(i).getId() + "," +
                    graph.stations.get(i).getRail());
        }
    }

    public void connectStations() throws IOException {
        int index = 0;
        scanner = new Scanner(new FileReader(Lines));
        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split(",");
            if (index > 0) {
                System.out.println(index);
                graph.addConnection(Integer.parseInt(row[0]) - 1, Integer.parseInt(row[1]) - 1, Integer.parseInt(row[2]));
            }
            index = index + 1;
        }
    }

    public void printGraph() {
        for (int i = -1; i < graph.matrix.length; i++) {
            for (int j = 0; j < graph.matrix.length; j++) {
                if (i == -1) {
                    System.out.print(j + "  ");
                } else {
                    if (graph.matrix[i][j] > 0) {
                        System.out.print(graph.matrix[i][j] + "   ");
                    } else {
                        System.out.print("O ");
                    }
                }
            }
            System.out.println(); // move to the next line after each row
        }
    }

    public void printSize() {
        System.out.println(graph.matrix.length);
        graph.printConnection(48, 126);
    }

    public void depthFirstSearch() {
        boolean[] visited = new boolean[graph.matrix.length];
        int[] route = new int[graph.stations.size()];
        DFS(Integer.parseInt(startPoint.getValue().substring(0, 2)) - 1, visited, route);
    }

    public void DFS(int start, boolean[] visited, int[] stations){
        if (visited[start]) {
            System.out.println("end");
            return;
        } else {
            visited[start] = true;
            if(start < 308) {
                System.out.println(graph.stations.get(start) + " = visited");
                int index = 0;
                int num = 0;
                while(index == 0) {
                    if (num < 308) {
                        if (stations[num] == 0) {
                            stations[num] = start;
                            index = index + 1;
                        }
                        num = num + 1;
                    } else {
                        index = 1;
                    }
                }
                stations[start] = start;
                if(graph.stations.get(start).getId() == Integer.parseInt(destination.getValue().substring(0, 2))) {
                    System.out.println("yep");
                    stationsList.getItems().clear();
                    outerLoop:
                    for (int i = 0; i < graph.stations.size() - 1; i++) {
                        stationsList.getItems().add("Station " +graph.stations.get(stations[i]).getId() +": "  + graph.stations.get(stations[i]).getName());
                        if(stations[i] == 0){
                            break outerLoop;
                        }
                    }
                }
                }
            }
            for (int i = 0; i < graph.matrix.length; i++) {
                if (graph.matrix[start][i] > 0) {
                    DFS(i, visited, stations);
                }
            }
        return;
    }



    public void test() throws FileNotFoundException {
        int lastNum = 0;
        for(int i = 0;i < graph.stations.size();i++){
            if(lastNum + 1 != graph.stations.get(i).getId()){
                System.out.println("here");
            }
            System.out.println(graph.stations.get(i).getId());
            lastNum = graph.stations.get(i).getId();
        }
    }
}