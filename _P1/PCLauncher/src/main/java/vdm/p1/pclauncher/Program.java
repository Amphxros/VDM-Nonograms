package vdm.p1.pclauncher;


import vdm.p1.logic.Logic;
import vdm.p1.pcengine.DesktopEngine;

public class Program {
	public static void main(String[] args) {
		DesktopEngine engine = new DesktopEngine();
		Logic logic = new Logic(engine);
		engine.setLogic(logic);
		engine.run();
	}
}
