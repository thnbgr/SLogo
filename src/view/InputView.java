package view;

import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;



/**
 * 
 * @author Natalia Carvalho and Eric Wu
 *
 */
public class InputView extends JFrame implements IView {

    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private JFileChooser myFileChooser;
    private int myLastKeyPressed;
    
    public static final int NO_KEY_PRESSED = -1;
    public static final Point NO_MOUSE_PRESSED = null;
    
    private Point myLastMousePosition;



    public InputView () {
    }
    
    public void setInputListeners () {
        
     // initialize input state
            myLastKeyPressed = NO_KEY_PRESSED;
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed (KeyEvent e) {
                    
                }

                @Override
                public void keyReleased (KeyEvent e) {
                    myLastKeyPressed = NO_KEY_PRESSED;
                }
            });
            
            myLastMousePosition = NO_MOUSE_PRESSED;
            
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged (MouseEvent e) {
                    myLastMousePosition = e.getPoint();
                }
            });
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed (MouseEvent e) {
                    myLastMousePosition = e.getPoint();
                }

                @Override
                public void mouseReleased (MouseEvent e) {
                    myLastMousePosition = NO_MOUSE_PRESSED;
                }
            });
        
    }

    @Override
    public void paint () {
        // TODO Auto-generated method stub
        
    }

}
