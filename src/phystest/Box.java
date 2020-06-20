/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * 
 */
public class Box {

    Physics p;
    static Physics WALL;
    
    {
        WALL = new Physics();
        WALL.setFixed(true);
        WALL.setMass(10000);
        
    }
    
    
    double w = 10, h =10;
  
Random r;
    public Box(){
        r = new Random();
        p = new Physics();
        p.setMass(10);
        p.setXY(100,400);
        p.setGravity(new Vector(90,Math.toRadians(90),0));
    }
    
   
    
    public void nextFrame(Graphics g, double fps){        
        p.updateLocation(fps); 
        
    
            //check for walls
            
            
            
            if((p.get_x()+w)>990){
                p.setXY(980, p.get_y());
                Physics.Collision(WALL, p, true);
            }
        
            if((p.get_x())<10){
                 p.setXY(25, p.get_y());
                Physics.Collision(WALL, p, true);
            }
            
            if((p.get_y()+h)>990){
                 p.setXY(p.get_x(), 975);
                Physics.Collision(WALL, p, false);
            }
            
            if((p.get_y())<10){
                 p.setXY(p.get_x(), 25);
                Physics.Collision(WALL, p, false);
            }
        
        
        
           int red = (int)(p.get_velocity()/10);
           if(red>254){
               red = 254;
           }else if(red<0){
               red = 0;
           }
           
           
           
          
           g.setColor(new Color(red,0,0));
        
        g.drawRect((int)p.get_x(), (int) p.get_y(), (int)w, (int)h);
        
        
        for(int i =0; i<0; i++){
            g.drawLine((int)p.get_x(),  (int) p.get_y(), r.nextInt(10) +(int)p.get_x() - (int) (1*p.get_velocity()*Math.cos(p.get_direction())), r.nextInt(10) +(int)p.get_y() - (int) (1*p.get_velocity()*Math.sin(p.get_direction())));
        }
        
        
        
    }
    
    
    public void kick(double force, double direction, int time){
        p.addVector(new Vector(force,Math.toRadians(direction),time));
    }
    
    
    
}
