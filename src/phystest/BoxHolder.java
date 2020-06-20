/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * 
 */
public class BoxHolder {
    
    ArrayList<Box> b = new ArrayList();
    int numBoxes = 70;
    Random r;
    
    public BoxHolder(){
        r = new Random();
        for(int i=0; i<numBoxes; i++){
            Box box = new Box();
            box.p.setXY(r.nextInt(1000), r.nextInt(1000));
            box.kick(r.nextInt(50000), r.nextInt(360), r.nextInt(10));
            b.add(box);
        }
        
    }
    
    public void nextFrame(Graphics g, long delta){
        for(int i =0; i<b.size();i++){
            
            for(int i2=0; i2<b.size();i2++){
                if(b.get(i)!=b.get(i2)){
                    Box b1= b.get(i);
                    Box b2= b.get(i2);
                    if(b1.p.get_x()> b2.p.get_x() && b1.p.get_x()< b2.p.get_x()+b2.w &&
                            b1.p.get_y()>b2.p.get_y() && b1.p.get_y()<b2.p.get_y()+b2.h ){
                        //Physics.Collision(b1.p, b2.p);
                    }
                    
                    
                }
            }
            
            b.get(i).nextFrame(g, delta);
        }
    }
    
    
    
    
    
}
