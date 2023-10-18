package org.example;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Main extends JFrame implements KeyListener {
    public Main(){
        setSize(1, 1);
        setVisible(true);
        addKeyListener(this);
    }

    public static void main(String[] args) {
        Main f = new Main();
        R r = new R();
        r.start();
    }

    boolean ctrl = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_CONTROL){
            ctrl = true;
        }
        if(ctrl && e.getKeyCode()==KeyEvent.VK_C){
            System.out.println();
            System.out.println("stopping...");
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_CONTROL){
            ctrl = false;
        }
    }
}

class R extends Thread{

    public void start(){
        Y y = new Y();
        System.out.println("Red");
        try {
            Thread.sleep(2000);
            y.start(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Y extends Thread{
    public void start(boolean onStart){
        G g = new G();
        R r = new R();
        System.out.println("Yellow");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
        if(onStart) {r.start();}
        else {g.start();}
    }
}

class G extends Thread{
    public void start(){
        System.out.println("Green");
        try {
            Thread.sleep(2000);
            Y y = new Y();
            y.start(true);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
    }
}