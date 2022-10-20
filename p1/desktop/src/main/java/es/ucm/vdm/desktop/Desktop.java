package es.ucm.vdm.desktop;

import es.ucm.vdm.pcengine.PCEngine;
import es.ucm.vdm.pcengine.PCGraphics;
import es.ucm.vdm.pcengine.PCInput;

public class Desktop {

    public static void main(String[] args){
        PCGraphics graphics= new PCGraphics();
        PCInput input= new PCInput();
        PCEngine eng= new PCEngine(graphics,null,input);

    }
}