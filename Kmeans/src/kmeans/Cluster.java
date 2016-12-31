/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jakub
 */
public class Cluster extends Point {
    private Point[] group; //this is a group of points that belong to given cluster.
    private ArrayList<Point> group2;
    public Cluster(int x, int y){
        super(x,y);
        this.group = null;
        this.group2 = new ArrayList<> ();
    }
    
    public Cluster(int x, int y, Point[] group){
        super(x,y);
        this.group2 = (ArrayList<Point>) Arrays.asList(group);
        this.group = group;
    }
    
    public void newClusterCenter(){
        int sumx, sumy;
        sumx = sumy = 0;
        for (Point group1 : this.group) {
            sumx+=group1.getX();
            sumy+=group1.getY();
        }
        this.setX((int)(sumx/this.group.length));
        this.setY((int)(sumy/this.group.length));
    }
    
    public void newClusterCenter2(int width, int heigth){
        int sumx, sumy;
        sumx = sumy = 0;
        for (Point group1 : this.group2) {
            sumx+=group1.getX();
            sumy+=group1.getY();
        }
        if (this.group2.size()>1){
            this.setX((int)(sumx/this.group2.size()));
            this.setY((int)(sumy/this.group2.size()));
        }
        else{
            this.setX(ThreadLocalRandom.current().nextInt(10,width-10));
            this.setY(ThreadLocalRandom.current().nextInt(10,heigth-10));
        }
    }
    
    public void addPoint(Point a){
        group2.add(a);
    }
    
    public Point[] getGroup() {
        return group;
    }

    public void setGroup(Point[] group) {
        this.group = group;
    }

    public ArrayList<Point> getGroup2() {
        return group2;
    }

    public void setGroup2(ArrayList<Point> group2) {
        this.group2 = group2;
    }
    
}
