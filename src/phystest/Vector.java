/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * 
 */
public class Vector {
    
    public Vector(){
        
    }
    
    public Vector(double mag, double dir, int t){
        this.magnitude = mag;
        this.direction = dir;
        this.time =t;
        this.updateXYcomponent();
    }
    
    private double magnitude;
    private double direction;
    private double time;

    private double x_comp;
    private double y_comp;
    
    public double getMagnitude(){
        return magnitude;
    }
    
    public double getDirection(){
        return direction;
    }
    
    public double getTime(){
        return time;
    }

    
    public void setMagnitude(double d){
        this.magnitude = d;
        this.updateXYcomponent();
    }
    
    public void setDirection(double d){
        this.direction = d;
        this.updateXYcomponent();
    }
    
    public void setTime(double d){
        this.time = d;
    }
    
    public void updateXYcomponent(){
        x_comp = this.calc_x_compnent();
        y_comp = this.calc_y_component();
    }
    
    
    public double calc_x_compnent(){
        return magnitude*Math.cos(direction);
    }
    
    public double calc_y_component(){
        return magnitude*Math.sin(direction);
    }

    public double get_x_comp(){
        return x_comp;
    }
    
    public double get_y_comp(){
        return y_comp;
    }
    
    
    public void setXY_comp(double x, double y){
        x_comp = x;
        y_comp = y;
        
        magnitude = Math.sqrt(Math.pow(x, 2) + (Math.pow(y, 2)));
        direction = Math.atan(y / x);
    
        if (x < 0 && y > 0) {
            direction += Math.PI; //Math.toRadians(180 + dir);

        } else if (x < 0 && y < 0) {
            direction += Math.PI;
        }
        
    }
    
    public void add_x_comp(double x){
        x_comp += x;
        this.setXY_comp(x_comp, y_comp);
    }
    
    public void add_y_comp(double y){
        y_comp += y;
        this.setXY_comp(x_comp, y_comp);
    }
    
    
    /**
     * adds v1 to v2 and returns a new vector
     * @param v1
     * @param v2
     * @return 
     */
    public void add(Vector v1){
        
        this.setXY_comp(v1.get_x_comp()+this.get_x_comp(), v1.get_y_comp()+this.get_y_comp());

    }
    
    /**
     * given a list of vectors this method will add them all up and return one vector
     * 
     * @param v list of vectors to be added
     * @param removeDead it true and if time<0 then the v[i] will be removed from the list... after being added to!
     * @return 
     */
    public void addList(ArrayList<Vector> v, boolean removeDead){
        Vector r = new Vector(0,0,0);
       
        for (Iterator<Vector> iterator = v.iterator(); iterator.hasNext();) {
            
           Vector a = iterator.next();
            this.add(a);
            a.time--;
            if(removeDead && a.time<=0){
               iterator.remove(); 
            }     
        }
       
    }
    
    
    
}
