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
import static java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;


public class Kmeans extends JFrame{
    
static Color [] color = {Color.red, Color.green, Color.yellow, Color.pink, Color.magenta, Color.blue, Color.orange, Color.green, Color.white};
public static final int C_WIDTH = 640;
public static final int C_HEIGTH = 480;

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
    public static Point [] generate_Points(int count, int radius, Point sample){
        Point [] set = new Point [count];
        int sX = sample.getX();
        int sY = sample.getY();
        for (int i = 0; i < count; i++) {
            set[i] = new Point(sX+ThreadLocalRandom.current().nextInt(-radius/2,radius/2),sY+ThreadLocalRandom.current().nextInt(-radius/2,radius/2));
        }
        return set;
    }
    
    public double distance(Point a, Point b){
        return sqrt(pow(a.getX()-b.getX(),2) + pow(a.getY()-b.getY(),2));
    }
    
    //private class just to draw on JPanel
    private class DrawClusters extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.BLACK);

            Cluster a,b,c,d;
            a=b=c=d = null;
            Cluster [] test = {a,b,c,d};
            int points = 20;
            int groups = 4;
            
            for (int i = 0; i < groups; i++) {
                test[i] = new Cluster(ThreadLocalRandom.current().nextInt(10,C_WIDTH),ThreadLocalRandom.current().nextInt(10,C_HEIGTH));
                test[i].setGroup(generate_Points(points,30,test[i]));
            }
            int width = 5;
            int height = 5;
            
            for(int i = 0; i < groups; i++){
                g.setColor(Color.WHITE);
                g.fillOval(test[i].getX(), test[i].getY(), width*2, height*2);
                System.out.println("Old coords : " + test[i].toString());
                test[i].newClusterCenter();
                System.out.println("New coords : " + test[i].toString());
                g.setColor(Color.BLUE);
                g.fillOval(test[i].getX(), test[i].getY(), width*2, height*2);
                g.setColor(color[i]);
                //System.out.println("DRAWING CLUSTER " + i );
                
                for (int j = 0; j < points; j++) {
                    g.drawOval(test[i].getGroup()[j].getX(), test[i].getGroup()[j].getY(), width, height);
                }   
            }  
            g.setColor(Color.WHITE);
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Kmeans kmeans = new Kmeans();
            }
        });
    }         
}
