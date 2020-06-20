/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * 
 */
public class PhysPanel extends JPanel {

    int key;
    BoxHolder bh;
    Box b;
    Tick_Helper tick;

    public PhysPanel() {
        b = new Box();
        //b.kick(1990, 270, 2);

        tick = new Tick_Helper.Builder().setTargetFPS(500).build();
        bh = new BoxHolder();
    }

    double x = 0;
    int force = 5000;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.scale(.1, .1);

        x += 0.1;

        switch (key) {
            case 38:

                b.kick(force, 270, 0);
                break;
            case 39:
                //->
                b.kick(force, 0, 0);
                break;
            case 37:
                //<-
                b.kick(force, 180, 0);
                break;
            case 40:
                b.kick(force, 90, 0);
                break;

        }

        tick.TickTock();

        if (tick.getFps() != 0) {
            g.clearRect(0, 0, 1000, 1000);
            b.nextFrame(g, tick.getDelta());

            for (int i = 0; i < bh.b.size(); i++) {
                final Box b1 = bh.b.get(i);
                final Box b2 = b;

                if (b1.p.get_x() > b2.p.get_x() && b1.p.get_x() < b2.p.get_x() + b2.w
                        && b1.p.get_y() > b2.p.get_y() && b1.p.get_y() < b2.p.get_y() + b2.h) {
                    
               
                    //Physics.Collision(b1.p, b2.p, true);
                    
                    
                    
                    
                    
                    
                }

            }

            bh.nextFrame(g, tick.getDelta());
            //System.out.println(tick.getFps());
        }

        this.repaint();
    }

}
