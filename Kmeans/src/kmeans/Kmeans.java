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
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;


public class Kmeans extends JFrame{
    
static Color [] color = {Color.red, Color.green, Color.yellow, Color.pink, Color.magenta, Color.blue, Color.orange, Color.green, Color.white};
public static final int C_WIDTH = 640;
public static final int C_HEIGTH = 480;

public static int runs = 50;
public static final int points = 20;
public static final int groups = 4;
public static Cluster [] test = new Cluster[groups];

private final DrawClusters canvas;

public Kmeans(){
    canvas = new DrawClusters();
    canvas.setPreferredSize(new Dimension(C_WIDTH, C_HEIGTH));
    
    Container cp = getContentPane();
    cp.add(canvas);
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setTitle("Clusters");
    setVisible(true);
    
}
public static void setUp (){
    //set some random init points
    Point [][] set = null;
    //generate some points around these clusters
    for (int i = 0; i < groups; i++) {
        test[i] = new Cluster(ThreadLocalRandom.current().nextInt(10,C_WIDTH-10),ThreadLocalRandom.current().nextInt(10,C_HEIGTH-10));
        test[i].setGroup(generate_Points(points,30,test[i]));
    }
    //change the coords for these cluster to see if they converge to previous ones.
    for (int i = 0; i < groups; i++) {
        test[i].setX(ThreadLocalRandom.current().nextInt(10,C_WIDTH-10));
        test[i].setY(ThreadLocalRandom.current().nextInt(10,C_HEIGTH-10));
        System.out.println("Old coords : " + test[i].toString());
    }
            
    //now recalculate
    for (int i = 0; i < groups; i++) {
        test[i].newClusterCenter();
        System.out.println("New coords : " + test[i].toString());
    }
    }
    
public static void setTest () {
    Point [] set = new Point [groups*points];
    
    for (int i = 0; i < groups; i++) {
        test[i] = new Cluster(ThreadLocalRandom.current().nextInt(10,C_WIDTH-10),ThreadLocalRandom.current().nextInt(10,C_HEIGTH-10));
        System.arraycopy(generate_Points(points,30,test[i]), 0, set, i*points, points);
    } 
    //new coords for set of clusters
    for (int i = 0; i < groups; i++) {
        test[i].setX(ThreadLocalRandom.current().nextInt(10,C_WIDTH-10));
        test[i].setY(ThreadLocalRandom.current().nextInt(10,C_HEIGTH-10));
        System.out.println("Old cluster coords : " + test[i].toString());
    }
    
    while(runs>0){        
        for (kmeans.Point point: set) {
            //calculates, for each point in set, the closest cluster in the set of clusters
            point.closestCluster(test).addPoint(point);
        }
        for (kmeans.Cluster cluster: test){
            cluster.newClusterCenter2(C_WIDTH,C_HEIGTH);
            cluster.getGroup2().clear();
       }
        runs--;
    }
    
    for (kmeans.Point point: set) {
        //calculates, for each point in set, the closest cluster in the set of clusters
        point.closestCluster(test).addPoint(point);
    }
    for (kmeans.Cluster cluster: test){
        cluster.newClusterCenter2(C_WIDTH,C_HEIGTH);
        System.out.println("New cluster coords : " + cluster.toString());
    }
   }

public static Point [] generate_Points(int count, int radius, Point sample){
    Point [] set = new Point [count];
    int sX = sample.getX();
    int sY = sample.getY();
    for (int i = 0; i < count; i++) {
        set[i] = new Point(sX+ThreadLocalRandom.current().nextInt(-radius/2,radius/2),sY+ThreadLocalRandom.current().nextInt(-radius/2,radius/2));
    }
    return set;
    }
    
    //private class just to draw on JPanel
    private class DrawClusters extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.BLACK);

            int width = 5;
            int height = 5;
            for (int i = 0; i < groups; i++) {
                g.setColor(Color.WHITE);
                g.fillOval(test[i].getX(), test[i].getY(), width*2, height*2);            
                g.setColor(color[i]);
                for (kmeans.Point group2 : test[i].getGroup2()) {
                    g.drawOval(group2.getX(), group2.getY(), width, height);
                }
            }
            /*
            for(int i = 0; i < groups; i++){
                g.setColor(Color.WHITE);
                g.fillOval(test[i].getX(), test[i].getY(), width*2, height*2);            
                g.setColor(color[i]);
                
                for (int j = 0; j < points; j++) {
                    g.drawOval(test[i].getGroup()[j].getX(), test[i].getGroup()[j].getY(), width, height);
                }   
            }*/  
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        setTest();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Kmeans kmeans = new Kmeans();
            }
        });
    }         
}
