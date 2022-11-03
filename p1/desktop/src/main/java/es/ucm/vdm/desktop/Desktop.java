package es.ucm.vdm.desktop;

import es.ucm.vdm.logic.NonogramLogic;
import es.ucm.vdm.pcengine.PCEngine;

public class Desktop {
private static PCEngine eng;
    static final int FPS=60; //frame rate
    public static void main(String[] args){
        //create engine
        NonogramLogic logic= new NonogramLogic(eng);
        eng= new PCEngine("Nonograms",800,800);
        logic.setEngine(eng);
        eng.setLogic(logic);
        eng.run();


    }
}