/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kneighs;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Jakub
 */
public class Point {
    private int x;
    private int y;
    private Class c;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Point (int x, int y, Class c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
    
    public Point [] findKNearest(int k, Point [] points){
        Map<Double, Point> distances = new TreeMap<> ();
        for(kneighs.Point point : points){
            distances.put(distance(this, point), point);
        }
        Set set = distances.entrySet();
        Iterator j = set.iterator();
        
        Point [] nearest = new Point [k];
        for (int i = 0; i < k; i++) {
            if(j.hasNext()){
             Entry<Double, Point> entry = (Entry)j.next();
             nearest[i] = entry.getValue();
            }
            
        }
        return nearest;
    }
    
    public double distance(Point a, Point b){
        return sqrt(pow(a.getX()-b.getX(),2) + pow(a.getY()-b.getY(),2));
    }
    
    public static Class vote (Point [] nearest){
        Map<Class,Integer> election = new HashMap<>();
        for (Point member : nearest) {
            if (election.containsKey(member.getC())){
                election.put(member.getC(), election.get(member.getC())+1);
            }
            else{
                election.put(member.getC(), 1);
            }
        }
        
        Class c = null;
        Entry <Class, Integer> max = null;
        Integer maxVal = Collections.max(election.values());
        for (Entry<Class, Integer> entry : election.entrySet()) {
            Integer val = entry.getValue();
            if (Objects.equals(maxVal, val)){
                max = entry;
            }
        }
        return max.getKey();
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

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }  
}
