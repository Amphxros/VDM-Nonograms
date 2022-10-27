package es.ucm.vdm.desktop;

import es.ucm.vdm.logic.Logic;
import es.ucm.vdm.pcengine.PCEngine;
import es.ucm.vdm.pcengine.PCGraphics;
import es.ucm.vdm.pcengine.PCInput;


public class Desktop {
    static final int FPS=60; //frame rate
    public static void main(String[] args){
        //create engine
        PCEngine eng= new PCEngine("Nonograms",1920,1080);
        Logic logic= new Logic(eng);

        /** Falta enlazar la logica al engine
         * eng.setLogic(logic);
         *
         * if(eng.openGame)
         * eng.resume();
         * eng.run();
         *
         */


    }
}