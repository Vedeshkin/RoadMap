package com.github.vedeshkin.RoadMap;


import java.io.Serializable;

public class Road implements Serializable {

    private final String roadName;
    private int distance;
    private City head;
    private City tail;

    public Road(String roadName, int distance, City head, City tail) {
        this.roadName = roadName;
        this.distance = distance;
        this.head = head;
        this.tail = tail;
    }


    public String getRoadName() {
        return roadName;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public City getHead() {
        return head;
    }

    public void setHead(City head) {
        this.head = head;
    }

    public City getTail() {
        return tail;
    }

    public void setTail(City tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "Road["+roadName+ "]" +  "distance["+ distance+ "]";
    }
}
