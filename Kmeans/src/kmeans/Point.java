/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import static java.lang.Math.*;

/**
 *
 * @author Jakub
 */
public class Point {
    private int x;
    private int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cluster closestCluster(Cluster[] set){
        Cluster best = set[0];
        double best_distance = distance(this, (Point) set[0]);
        double temp_dist = 0.0;
        for (int i = 1; i < set.length; i++) {
            temp_dist = distance(this, (Point)set[i]);
            if (temp_dist < best_distance){
                best_distance = temp_dist;
                best = set[i];
        }
        }
        return best;
    }
    
    public double distance(Point a, Point b){
        return sqrt(pow(a.getX()-b.getX(),2) + pow(a.getY()-b.getY(),2));
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

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
}
