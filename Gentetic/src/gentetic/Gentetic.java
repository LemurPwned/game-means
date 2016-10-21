/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gentetic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 *
 * @author Jakub
 */
public class Gentetic{
    public static BufferedImage img =new BufferedImage (6000, 6000,BufferedImage.TYPE_INT_RGB);
    static Color [] color = {Color.red, Color.green, Color.yellow, Color.white, Color.black, Color.pink, Color.magenta, Color.blue, Color.orange, Color.green};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedImage img = new BufferedImage (300, 300, BufferedImage.TYPE_INT_RGB);
        int [][] grid = new int[300][300];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int max = color.length;
                int num = ThreadLocalRandom.current().nextInt(0, max);
                Color t_col = color[num];
                grid[i][j] = num;
                img.setRGB(i, j, t_col.getRGB());
            }
        }
       File f = new File("C:\\Users\\Jakub\\Desktop\\Genetic.jpg");
       ImageIO.write(img, "JPEG", f);

        
    }
    
}
