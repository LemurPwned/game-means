/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author Jakub
 */
public class Gentetic{
    static Color [] color = {Color.red, Color.green, Color.yellow, Color.white, Color.black, Color.pink, Color.magenta, Color.blue, Color.orange, Color.green};
    static int solution_x = 150;
    static int solution_y = 60;
    static final int x_size = 300;
    static final int y_size = 300;
    static int pop_size = 10;
    public static BufferedImage img =new BufferedImage (x_size + 100, y_size + 100,BufferedImage.TYPE_INT_RGB);
    /*TODO:
    Tuple <lista> = new Tuple {10};
       lista(1) = (x,y);
    double [] dist = new double[10];
    sort(dist) return lista indexów
    
    */
    
    public static void geneticAlgorithm(){
        double [][] population = new double [x_size][y_size];
        population = populate(population);
        int [] top_ten_x = new int [pop_size];
        int [] top_ten_y = new int [pop_size];
        double [] top_ten = new double [pop_size];
        int c = 0;
        for (int i = 0; i < population.length; i++) {
            for (int j = 0; j < population.length; j++) {
                if(population[i][j]!=-1){
                    top_ten[c] = population[i][j];
                    c++;
                }
            }            
        }
       Arrays.sort(top_ten);
       int x;
        for (int i = 0; i < top_ten.length; i++) {
         
            
        }
    }
    
    public static String [] split(String l){
        int length = l.length();
        String [] parts = {l.substring(0, length/2), l.substring(length/2 , length)};
        return parts;
    }
    
    public static void linearSort(double [] array){
        double max;
        int t;
        for (int i = array.length-1; i >= 0 ; i--) {
            max = array[i];
            t = i;
            for (int j = i-1; j >= 0; j--) {
                if(max < array[j]){
                    max = array[j];
                    t = j;
                }
            }
            array[t] = array[i];
            array[i] = max;            
        }
    }
    
    public static double distance(int x, int y){
        double dist = Math.pow(x - solution_x, 2) + Math.pow(y - solution_y, 2);
        return Math.sqrt(dist);
    }
    
    public static String int2string(int x){
        if (x == 0){
            return "";
        }
        else{
            return int2string(x/2)+ x%2;
        }
    }
        /**
         * Returns the string a with addtional zeros to make up for the 
         * longer string b, if a is longer than b, it returns b compensated
         * with extra zeros, so that two match in length
     * @param a is the shorter string
     * @param b is the longer string
     */
    public static String compare(String a, String b){
        if(a.length() < b.length()){
            while(a.length()!=b.length()){
                a="0"+b;
            }
            return a;
        }
        else{
            while(a.length()!=b.length()){
                b="0"+b;
            } 
            return b;
        }
    }
    public static String mutate(String x){
        int p = ThreadLocalRandom.current().nextInt(0, x.length());
        if(x.charAt(p)=='1'){
            x = x.substring(0,p)+'0'+x.substring(p+1,x.length());
        } 
        else{
            x = x.substring(0,p)+'1'+x.substring(p+1,x.length());
        }
        return x;
    }
    public static double [][] populate(double[][] population){
        for (int i = 0; i < population.length; i++) {
            for (int j = 0; j < population.length; j++) {
                population[i][j] = -1.0;                
            }            
        }
        for (int i = 0; i < pop_size; i++) {
            int num = ThreadLocalRandom.current().nextInt(0, x_size);
            int num2 = ThreadLocalRandom.current().nextInt(0, y_size);
            population[num][num2] = distance(num, num2);          
        }
        return population;
    }
   public static void print(double [][] array){
       for (int i = 0; i < array.length; i++) {
           System.out.print("\n");
           for (int j = 0; j < array.length; j++) {
               System.out.printf("%.2f"+ " ", array[i][j]);
           }           
       }
   }
   public static void binaryGenetic(String parent) {
         String[] child = new String[5];
         String[] halfChild = new String[10];

         int k=0;
         for (int i=0; i<5; i++) {
             child[i]=parent.substring(k,k+6);
             k+=6;
         }

         k=0;
         int j=0;
         for (int i=0; i<10; i++) {
             halfChild[i]=child[j].substring(k,k+3);
             if(i%2==1) j++;
             k+=3;
             if(k==6) k=0;
         }

         int theChosenOne = ThreadLocalRandom.current().nextInt(0, 2);
         String temp = halfChild[theChosenOne+2];
         halfChild[theChosenOne+2] = halfChild[theChosenOne];
         halfChild[theChosenOne] = temp;

         Random random = new Random();
         for (int i=0; i<6; i+=2) {
             theChosenOne = ThreadLocalRandom.current().nextInt(i+4, i+6);
             if (theChosenOne%2==0) halfChild[theChosenOne] = halfChild[random.nextBoolean() ? 0 : 2];
             else halfChild[theChosenOne] = halfChild[random.nextBoolean() ? 1 : 3];
         }

         j=0;
         for (int i=0; i<10; i+=2) {
             child[j] = halfChild[i].concat(halfChild[i+1]);
             j++;
         }
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      /* BufferedImage img = new BufferedImage (x_size, y_size, BufferedImage.TYPE_INT_RGB);
       double [][] init_pop = new double[x_size][y_size];
       init_pop = populate(init_pop);
       //print(init_pop);
       for (int i = 0; i < init_pop.length; i++) {
            for (int j = 0; j < init_pop.length; j++) {
                int max = color.length;
                if (init_pop[i][j] != -1){
                    Color t_color = Color.white;
                    img.setRGB(i, j, t_color.getRGB());
                }
            }
       }
       Color sol = Color.red;
       for (int i = 0; i < 10; i++) {
            img.setRGB(solution_x + i, solution_y + i, sol.getRGB());
        }
                
       File f = new File("C:\\Users\\Jakub\\Desktop\\Genetic.jpg");
       ImageIO.write(img, "JPEG", f);
        */
        double [] top_ten = {0, 12, 334134, 1421 ,125,3,1,2, 5423421, 7, 8, 41, 99, 132, 14142 ,41312, -1};
        linearSort(top_ten);
        for (int i = 0; i < top_ten.length; i++) {
            //System.out.println(top_ten[i]);
        }
        
    }
    
}
