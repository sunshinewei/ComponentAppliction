package com.example.infoapplication.view.utils;

public class PointEntity {


    private int x;

    private int y;

    private boolean isPoint;


    public PointEntity(int x, int y,boolean isPoint) {
        this.x = x;
        this.y = y;
        this.isPoint=isPoint;
    }

    PointEntity(){

    }

    public boolean isPoint() {
        return isPoint;
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
