package com.example.assignment2s4;

public class Station {
    private int id;
    private String latitude;
    private String longitude;
    private String name;
    private String display_name;
    private double zone;
    private double total_lines;
    private double rail;

    public Station(int id, String latitude, String longitude, String name, String display_name, double zone, double total_lines, double rail) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.display_name = display_name;
        this.zone = zone;
        this.total_lines = total_lines;
        this.rail = rail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public double getZone() {
        return zone;
    }

    public void setZone(double zone) {
        this.zone = zone;
    }

    public double getTotal_lines() {
        return total_lines;
    }

    public void setTotal_lines(double total_lines) {
        this.total_lines = total_lines;
    }

    public double getRail() {
        return rail;
    }

    public void setRail(double rail) {
        this.rail = rail;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", name='" + name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", zone=" + zone +
                ", total_lines=" + total_lines +
                ", rail=" + rail +
                '}';
    }
}
