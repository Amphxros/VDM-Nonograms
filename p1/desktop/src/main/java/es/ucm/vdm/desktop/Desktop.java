package es.ucm.vdm.desktop;

import es.ucm.vdm.logic.NonogramLogic;
import es.ucm.vdm.pcengine.PCEngine;


public class Desktop {
    static final int FPS=60; //frame rate
    public static void main(String[] args){
        //create engine
        PCEngine eng= new PCEngine("Nonograms",800,800);
        NonogramLogic logic= new NonogramLogic(eng);


        eng.run();

        //eng.setLogic(logic);


    }
}