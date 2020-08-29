package com.bl.webimp;

public class PixelData {
    private int x;
    private int y;
    private int r;
    private int g;
    private int b;

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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "PixelData{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
