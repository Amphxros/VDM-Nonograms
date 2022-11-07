package vdm.p1.pclauncher;

import javax.swing.JFrame;

import vdm.p1.logic.Logic;
import vdm.p1.pcengine.DesktopEngine;

public class MyClass {

    public static void main(String[] args) {
        JFrame renderView = new JFrame("Nonogramas");

        renderView.setSize(1000, 1000);
        renderView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderView.setIgnoreRepaint(true);
        renderView.setVisible(true);

        DesktopEngine engine = new DesktopEngine(renderView);
        Logic logic = new Logic(engine);

        logic.initLogic();

        // TODO: Replace it with a thread or whatever cool kids do nowadays
        // Ultimately, move this loop inside DesktopEngine.
        while (true) {
            double delta = 0;
            logic.handleEvents();
            logic.update(delta);
            logic.render();
            engine.getGraphics().present();
        }
    }
}
