/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kneighs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
/**
 *
 * @author Jakub
 */
public class Kneighs extends JFrame{
    public static Point [] C = new Point[20];
    public static Point test = new Point(160, 168);
    
    public static final int C_WIDTH = 640;
    public static final int C_HEIGTH = 480;
    
    private Draw canvas;
    
    public Kneighs(){
        canvas = new Draw();
        canvas.setPreferredSize(new Dimension(C_WIDTH, C_HEIGTH));
    
        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("KNN");
        setVisible(true);
    }

    /**
     * @param count
     * @param c
     * @param width
     * @param height
     * @param radius
     * @return 
     */
    public static Point [] generatePoints(int count, Class c, int width, int height, int radius){
        Point [] set = new Point [count];
        for (int i = 0; i < count; i++) {
            set[i] = new Point(ThreadLocalRandom.current().nextInt(width-radius, width+radius),ThreadLocalRandom.current().nextInt(height-radius, height+radius), c);
        }
        return set;
    }
    
    public static void setUp(){
        Class a = new Class("A", 0, Color.GREEN);
        Class b = new Class("B", 1, Color.RED);
        Point [] A = generatePoints(10, a, 70, 80, 30);
        Point [] B = generatePoints(10, b, 160, 210, 30);
        
        System.arraycopy(A,0,C,0,10);
        System.arraycopy(B,0,C,10,10);
       
        int k = 12;
        
        Point [] nearest = test.findKNearest(k, C);
        test.setC(kneighs.Point.vote(nearest));
    }
    
    private class Draw extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.BLACK);
            int h = 5;
            int w = 5;
            for (kneighs.Point point : C){
                g.setColor(point.getC().getColor());
                g.drawOval(point.getX(), point.getY(),w, h);
            }
            g.setColor(test.getC().getColor());
            g.fillOval(test.getX(), test.getY(), h*2, w*2);
            }
    }
    public static void main(String[] args) {
        setUp();
        new Kneighs();
        // TODO code application logic here
    }
    
}
