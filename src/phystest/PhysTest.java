/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * 
 */
public class PhysTest extends JFrame {
 PhysPanel pp;
    public PhysTest(){
        
        pp = new PhysPanel();
        
        this.add(pp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000); 
        
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                pp.key = ke.getKeyCode();
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                pp.key = 0;
            }
        });
        
        
        this.setVisible(true);
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        PhysTest p = new PhysTest();
        p.repaint();
    }
    
}
