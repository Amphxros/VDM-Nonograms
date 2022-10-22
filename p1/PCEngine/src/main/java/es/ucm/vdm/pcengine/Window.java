package es.ucm.vdm.pcengine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import es.ucm.vdm.engine.Engine;

public class Window extends JFrame {
    Graphics mGraphics_;
    Engine mEngine_;
    BufferStrategy mBuffer_;

    public Window(String title, int width, int height, Engine eng){
        super(title);
        this.mEngine_= eng;
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setIgnoreRepaint(true);
        setVisible(true);

        int tries= 10;
        do{
            try {
                createBufferStrategy(2);
                break;
            }
            catch (Exception e){

            }

        }while((tries--)>0);

        if(tries==0){
            return;
        }
        synchronized (this){
            mBuffer_=getBufferStrategy();
        }
    }

    public synchronized void setGraphics(){
        synchronized (this){
            mGraphics_=mBuffer_.getDrawGraphics();
        } //sincronizado
    }
    public Graphics getGraphics(){
        return mGraphics_;
    }

}
