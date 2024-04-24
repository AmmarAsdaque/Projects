/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoproject;

/**
 *
 * @author k213923
 */
public class Point {
    int x,y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }
}
class PointSet{

    private Point p1,p2;
    public PointSet(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public Point getFirst() {
		return this.p1;
    }
    public Point getSecond() {
		return this.p2;
    }
}