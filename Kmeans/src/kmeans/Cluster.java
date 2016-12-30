/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

/**
 *
 * @author Jakub
 */
public class Cluster extends Point {
    private int x;  //this is now coordinate of a center
    private int y;
    private Point[] group; //this is a group of points that belong to given cluster.
    
    public Cluster(int x, int y){
        super(x,y);
        this.group = null;
    }
    
    public Cluster(int x, int y, Point[] group){
        super(x,y);
        this.group = group;
    }
    
    public void newClusterCenter(){
        int sumx, sumy;
        sumx = sumy = 0;
        for (Point group1 : this.group) {
            sumx+=group1.getX();
            sumy+=group1.getY();
        }
        this.x = (int)(sumx/this.group.length);
        this.y = (int)(sumy/this.group.length);
    }
    
    public Point[] getGroup() {
        return group;
    }

    public void setGroup(Point[] group) {
        this.group = group;
    }
    
}
